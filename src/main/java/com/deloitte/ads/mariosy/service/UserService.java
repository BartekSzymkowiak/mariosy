package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.repository.UserRepository;
import com.google.common.collect.Sets;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<UserEntity> getAllUsers() {
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

    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findUserById(id);
    }

    public Optional<UserEntity> getUserByExternalId(UUID externalId) { return userRepository.findUserByExternalId(externalId); }

    public Optional<UserEntity> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }


    public Set<UserEntity> searchUsers(String searchKeyword){
     return userRepository.searchUserEntities(searchKeyword);
    }

}
