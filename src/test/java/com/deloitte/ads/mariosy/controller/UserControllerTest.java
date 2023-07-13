package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void shouldFindCreatedUserByEmail() {
        // given
        UserDTO userDTO = createExampleUserDTO();
        String email = userDTO.getEmail();
        // when
        userController.addUser(userDTO);
        ResponseEntity<UserDTO> responseUser = userController.getUserByEmail(email);
        // then
        Assertions.assertEquals(200, responseUser.getStatusCodeValue());
    }

    private UserDTO createExampleUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("john.doe@email.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        return userDTO;
    }

}
