package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class MariosyServiceTest {
    @Autowired
    MariosyService mariosyService;

    @Test
    public void shouldReturnAllMarioses(){

    }


}
