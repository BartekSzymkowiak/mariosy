package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.repository.MariosRepository;
import com.deloitte.ads.mariosy.repository.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public Set<UserEntity> getUsers() {
        return Sets.newHashSet(userRepository.findAll());
    }

    public void createUser(UserDTO userDTO){

        String firstName = userDTO.getFirstName();
        String lastName = userDTO.getLastName();
        String email = userDTO.getEmail();

        Optional<UserEntity> userWithThisEmail;
        userWithThisEmail =  userRepository.findUserByEmail(email);

        if (userWithThisEmail.isPresent()){
            throw new IllegalArgumentException("User with this email already exists");
        }
        UserEntity userEntity = new UserEntity(firstName, lastName, email);
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }


}
