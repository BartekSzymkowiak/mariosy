package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.MariosType;
import com.google.common.collect.Sets;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class MariosControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private MariosController mariosController;

    @Test
    public void shouldBeMoreMarioses(){
        //given
        UserDTO creator = addUser(1);
        UserDTO receiverDTO = addUser(2);

      //  int mariosesCount = mariosController.getAllMarioses().size();
//        Set<Long> receiversIds = new HashSet<Long>(Sets.newHashSet(receiverDTO.getId()));
//        MariosDTO mariosDTO = createMariosDTO(creator.getId(), receiversIds);
//        //when
//        mariosController.createMarios(mariosDTO);
       // int newMariosesCount = mariosController.getAllMarioses().size();
        //then
       // Assertions.assertEquals(mariosesCount+1, newMariosesCount);
    }

    private UserDTO addUser(Integer num){
        UserDTO creatorDTO= createUserDTO(num);
        userController.addUser(creatorDTO);
        ResponseEntity<UserDTO> responseEntity = userController.getUserByEmail(creatorDTO.getEmail());
        return responseEntity.getBody();
    }

    private UserDTO createUserDTO(Integer num){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(String.format("john.doe%d@email.com",num));
        userDTO.setFirstName(String.format("John%d",num));
        userDTO.setLastName(String.format("Doe%d",num));
        return userDTO;
    }

    private MariosDTO createMariosDTO(Long creatorId, Set<Long> receiversId){
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorId(creatorId);
        mariosDTO.setReceiversIds(receiversId);
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosDTO.setComment("Lorem ipsum");
        return mariosDTO;
    }




}
