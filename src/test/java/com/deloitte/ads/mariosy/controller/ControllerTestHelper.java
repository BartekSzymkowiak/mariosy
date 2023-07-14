package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.MariosType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public class ControllerTestHelper {

    public static UserDTO createExampleUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("john.doe@email.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        return userDTO;
    }

    private static UserDTO createUserDTO(Integer num){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(String.format("john.doe%d@email.com",num));
        userDTO.setFirstName(String.format("John%d",num));
        userDTO.setLastName(String.format("Doe%d",num));
        return userDTO;
    }

    public static MariosDTO createMariosDTO(Long creatorId, Set<Long> receiversId){
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorId(creatorId);
        mariosDTO.setReceiversIds(receiversId);
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosDTO.setComment("Lorem ipsum");
        return mariosDTO;
    }

    public static void createUsersToGivenId(Integer lastUserId, UserController userController){
        for(int i=1; i<=lastUserId; i++){
            addUser(lastUserId, userController);
        }
    }

    private static void addUser(Integer lastUserId, UserController userController){
        ResponseEntity<UserDTO> responseEntityCheckUserExists = userController.getUserById(lastUserId.longValue());
        if (responseEntityCheckUserExists.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            UserDTO userDTO= createUserDTO(lastUserId);
            userController.addUser(userDTO);
        }
    }

}
