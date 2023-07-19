package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.mappers.UserMapper;
import com.deloitte.ads.mariosy.repository.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Set<UserEntity> getAllUsers() {
        return Sets.newHashSet(userRepository.findAll());
    }

    public Set<UserDTO> getAllUsersDTOs() {
        return Sets.newHashSet(userRepository.findAll()).stream().map(u -> userMapper.userEntityToUserDTO(u)).collect(Collectors.toSet());
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

    public Optional<UserDTO> getUserDTOByExternalId(UUID externalId) {
        return userMapper.optionalUserEntityToOptionalUserDTO(userRepository.findUserByExternalId(externalId));
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public Optional<UserDTO> getUserDTOByEmail(String email){
        return userMapper.optionalUserEntityToOptionalUserDTO(userRepository.findUserByEmail(email));
    }

    public Set<UserEntity> searchUsers(String searchKeyword){
     return userRepository.searchUserEntities(searchKeyword);
    }

    public Set<UserDTO> searchUsersDTOs(String searchKeyword){
        return userRepository.searchUserEntities(searchKeyword).stream().map(u -> userMapper.userEntityToUserDTO(u)).collect(Collectors.toSet());
    }



}
