package com.deloitte.ads.mariosy.mappers;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserMapper {

    public UserMapper() {
    }

    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setExternalId(userEntity.getExternalId());
        userDTO.setUsername(userEntity.getUsername());
        return userDTO;
    }

    public Optional<UserDTO> optionalUserEntityToOptionalUserDTO(Optional<UserEntity> userEntityOptional) {
        if (userEntityOptional.isPresent()) {
            return Optional.ofNullable(userEntityToUserDTO(userEntityOptional.get()));
        }
        return Optional.ofNullable(null);
    }



}