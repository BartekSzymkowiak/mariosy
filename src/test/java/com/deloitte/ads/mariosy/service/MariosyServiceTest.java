package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class MariosyServiceTest {
    @Autowired
    MariosyService mariosyService;

//    @Test
//    public void shouldReturnMarioses(){
//        Set<MariosEntity> mariosEntities= mariosyService.getMariosesCreatedByUser(1L);
//        Assertions.assertEquals(1, mariosEntities.size());
//
//    }
}
