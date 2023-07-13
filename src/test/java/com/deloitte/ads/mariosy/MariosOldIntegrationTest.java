package com.deloitte.ads.mariosy;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.controller.MariosController;
import com.deloitte.ads.mariosy.controller.UserController;
import com.deloitte.ads.mariosy.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class MariosOldIntegrationTest {

    private static final String SHORT_COMMENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    @Autowired
    private MariosController mariosController;

    @Autowired
    private UserController userController;

    @Test
    public void addAndSearchUserByEmail() {
        String email = "johndoe@email.com";

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userController.addUser(userDTO);

        ResponseEntity<UserDTO> responseUser = userController.getUserByEmail(email);
        Assertions.assertEquals(200, responseUser.getStatusCodeValue());
    }

//    @Test
//    public void checkCreatedMarios(){
//        int creatorId = 1;
//
//        List<Marios> createdMarioses = mariosController.getSortedMariosesCreatedByUser(creatorId);
//        int mariosesCount = createdMarioses.size();
//
//        Set<Integer> receivers = Sets.newHashSet(2,3);
//        MariosDTO mariosDTO = new MariosDTO();
//        mariosDTO.setCreatorId(creatorId);
//        mariosDTO.setReceiversIds(receivers);
//        mariosDTO.setComment(SHORT_COMMENT);
//        mariosDTO.setType(MariosType.MARIOS_T1);
//        mariosController.createMarios(mariosDTO);
//
//        List<Marios> createdMariosesAfterCreate = mariosController.getSortedMariosesCreatedByUser(creatorId);
//        int mariosesCountAfterCreate = createdMariosesAfterCreate.size();
//
//        Assertions.assertEquals(mariosesCount+1, mariosesCountAfterCreate);
//    }
//
//    @Test
//    public void checkReceivedMarios(){
//        int creatorId = 1;
//        int receiverId = 2;
//
//        List<Marios> receivedMarioses = mariosController.getSortedMariosesReceivedByUser(receiverId);
//        int mariosesCount = receivedMarioses.size();
//
//        Set<Integer> receivers = Sets.newHashSet(receiverId);
//        MariosDTO mariosDTO = new MariosDTO();
//        mariosDTO.setCreatorId(creatorId);
//        mariosDTO.setReceiversIds(receivers);
//        mariosDTO.setComment(SHORT_COMMENT);
//        mariosDTO.setType(MariosType.MARIOS_T1);
//
//        mariosController.createMarios(mariosDTO);
//
//        mariosDTO.setType(MariosType.MARIOS_T2);
//        mariosController.createMarios(mariosDTO);
//
//        mariosDTO.setType(MariosType.MARIOS_T3);
//        mariosDTO.setReceiversIds(Sets.newHashSet(3));
//        mariosController.createMarios(mariosDTO);
//
//        List<Marios> receivedMariosesAfterCreate = mariosController.getSortedMariosesReceivedByUser(receiverId);
//        int mariosesCountAfterCreate = receivedMariosesAfterCreate.size();
//        Assertions.assertEquals(mariosesCount+2, mariosesCountAfterCreate);
//    }


}
