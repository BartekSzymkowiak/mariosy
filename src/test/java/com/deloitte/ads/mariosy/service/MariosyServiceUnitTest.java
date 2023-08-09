package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.MariosType;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.mappers.MariosMapper;
import com.deloitte.ads.mariosy.repository.MariosRepository;
import com.deloitte.ads.mariosy.repository.UserRepository;
import org.apache.catalina.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MariosyServiceUnitTest {

    @Mock
    MariosRepository mariosRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    MariosMapper mariosMapper;

    @InjectMocks
    MariosyService mariosyService;

    public List<UserEntity> users;
    public List<MariosEntity> marioses;

    @BeforeEach
    public void initTestData(){
        this.users = new ArrayList<UserEntity>();
       this.users.add(new UserEntity("Jan","Janowski","janek@email.com"));
       this.users.add(new UserEntity("Marta","Martowska","marta.marto@ko.com"));
       this.users.add(new UserEntity("Mariusz","Mariuszewicz","mariuszek@email.pl"));

       this.marioses = new ArrayList<MariosEntity>();
       this.marioses.add(new MariosEntity(MariosType.MARIOS_T1, "some comment", "good job"));
       this.marioses.add(new MariosEntity(MariosType.MARIOS_T2, "other comment", "great job"));
       this.marioses.add(new MariosEntity(MariosType.MARIOS_T3, "last comment", "nice job"));
    }

    @Test
    public void shouldNotCreateMariosWithInvalidReceiverId(){
        // given
        UserEntity creator = this.users.get(0);
        UUID receiverExternalId = UUID.randomUUID();
        // creator.getExternalId()) ??? ???
        when(userRepository.findUserByExternalId(any(UUID.class))).thenReturn(Optional.of(creator));
        when(userRepository.findUsersByExternalIdIn(anySet())).thenReturn(new ArrayList<UserEntity>());

        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorExternalId(creator.getExternalId());
        mariosDTO.setTitle("title one");
        mariosDTO.setComment("comment one");
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosDTO.setReceiversExternalIds(new HashSet<UUID>(Arrays.asList(receiverExternalId)));
        // when
        mariosyService.createMarios(mariosDTO);

        // then


    }




}
