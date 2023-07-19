package com.deloitte.ads.mariosy.DTO;


import com.deloitte.ads.mariosy.entity.UserEntity;

import java.util.UUID;

public class UserDTO {


    private UUID externalId;
    private String firstName;
    private String lastName;
    private String email;

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserDTO mapUserEntityToUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setExternalId(userEntity.getExternalId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        return userDTO;
    }
}
