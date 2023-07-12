package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.repository.Marios;
import com.deloitte.ads.mariosy.repository.MariosType;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class MariosTest {

    private static final String SHORT_COMMENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    @Autowired
    private MariosController mariosController;

    @Autowired
    private UserController userController;

    @Test
    public void checkCreatedMarios(){
        int creatorId = 1;

        List<Marios> createdMarioses = mariosController.getSortedMariosesCreatedByUser(creatorId);
        int mariosesCount = createdMarioses.size();

        Set<Integer> receivers = Sets.newHashSet(2,3);
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorId(creatorId);
        mariosDTO.setReceiversIds(receivers);
        mariosDTO.setComment(SHORT_COMMENT);
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosController.createMarios(mariosDTO);

        List<Marios> createdMariosesAfterCreate = mariosController.getSortedMariosesCreatedByUser(creatorId);
        int mariosesCountAfterCreate = createdMariosesAfterCreate.size();

        Assertions.assertEquals(mariosesCount+1, mariosesCountAfterCreate);
    }

    @Test
    public void checkReceivedMarios(){
        int creatorId = 1;
        int receiverId = 2;

        List<Marios> receivedMarioses = mariosController.getSortedMariosesReceivedByUser(receiverId);
        int mariosesCount = receivedMarioses.size();

        Set<Integer> receivers = Sets.newHashSet(receiverId);
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorId(creatorId);
        mariosDTO.setReceiversIds(receivers);
        mariosDTO.setComment(SHORT_COMMENT);
        mariosDTO.setType(MariosType.MARIOS_T1);

        mariosController.createMarios(mariosDTO);

        mariosDTO.setType(MariosType.MARIOS_T2);
        mariosController.createMarios(mariosDTO);

        mariosDTO.setType(MariosType.MARIOS_T3);
        mariosDTO.setReceiversIds(Sets.newHashSet(3));
        mariosController.createMarios(mariosDTO);

        List<Marios> receivedMariosesAfterCreate = mariosController.getSortedMariosesReceivedByUser(receiverId);
        int mariosesCountAfterCreate = receivedMariosesAfterCreate.size();
        Assertions.assertEquals(mariosesCount+2, mariosesCountAfterCreate);
    }


}
