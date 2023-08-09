package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.MariosType;
import com.google.common.collect.Sets;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class MariosControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private MariosController mariosController;



}



//    @Test
//    @Transactional
//    public void shouldBeMoreMarioses(){
//        //given
//        ControllerTestHelper.createUsersToGivenId(2, userController);
//
//        Long creatorId = 1L;
//        Long receiverId = 2L;
//
//        int mariosesCount = mariosController.getAllMarioses().size();
//        Set<Long> receiversIds = new HashSet<Long>(Sets.newHashSet(receiverId));
//        MariosDTO mariosDTO = ControllerTestHelper.createMariosDTO(creatorId, receiversIds);
//        //when
//        ResponseEntity<String> responseEntity =  mariosController.createMarios(mariosDTO);
//        int newMariosesCount = mariosController.getAllMarioses().size();
//        //then
//        Assertions.assertEquals(mariosesCount+1, newMariosesCount);
//    }

