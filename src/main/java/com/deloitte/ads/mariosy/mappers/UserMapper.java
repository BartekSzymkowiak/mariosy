package com.deloitte.ads.mariosy.mappers;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserMapper() {
    }

    public UserDTO userEntityToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setExternalId(userEntity.getExternalId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        return userDTO;
    }

    public Optional<UserDTO> optionalUserEntityToOptionalUserDTO(Optional<UserEntity> userEntityOptional){
        if (userEntityOptional.isPresent()){
            return  Optional.ofNullable(userEntityToUserDTO(userEntityOptional.get()));
        }
        return Optional.ofNullable(null);
    }

}