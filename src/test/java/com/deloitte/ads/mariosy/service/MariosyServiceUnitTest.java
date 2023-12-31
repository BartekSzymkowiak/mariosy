package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.MariosType;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.mappers.MariosMapper;
import com.deloitte.ads.mariosy.repository.MariosRepository;
import com.deloitte.ads.mariosy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
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
    public void initTestData() {
        this.users = new ArrayList<UserEntity>();
        this.users.add(new UserEntity("Jan", "Janowski", "janek@email.com"));
        this.users.add(new UserEntity("Marta", "Martowska", "marta.marto@ko.com"));
        this.users.add(new UserEntity("Mariusz", "Mariuszewicz", "mariuszek@email.pl"));

        this.marioses = new ArrayList<MariosEntity>();
        this.marioses.add(new MariosEntity(MariosType.MARIOS_T1, "some comment", "good job"));
        this.marioses.add(new MariosEntity(MariosType.MARIOS_T2, "other comment", "great job"));
        this.marioses.add(new MariosEntity(MariosType.MARIOS_T3, "last comment", "nice job"));
    }

    @Test
    public void shouldNotCreateMariosWithInvalidReceiverId() {
        // given
        UserEntity creator = this.users.get(0);
        UUID receiverExternalId = UUID.randomUUID();
        when(userRepository.findUserByExternalId(creator.getExternalId())).thenReturn(Optional.of(creator));
        when(userRepository.findUsersByExternalIdIn(anySet())).thenReturn(new ArrayList<UserEntity>());

        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorExternalId(creator.getExternalId());
        mariosDTO.setTitle("title one");
        mariosDTO.setComment("comment one");
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosDTO.setReceiversExternalIds(new HashSet<UUID>(Arrays.asList(receiverExternalId)));
        // when
        // then
        assertThrows(IllegalMariosFieldValueException.class, () -> mariosyService.createMarios(mariosDTO));
    }

    @Test
    public void shouldNotCreateMariosWithReceiverIdEqualsToSenderId() {
        // given
        UserEntity creator = this.users.get(0);
        UUID creatorExternalId = creator.getExternalId();

        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorExternalId(creatorExternalId);
        mariosDTO.setTitle("title one");
        mariosDTO.setComment("comment one");
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosDTO.setReceiversExternalIds(new HashSet<UUID>(Arrays.asList(creatorExternalId)));
        // when
        // then
        assertThrows(IllegalMariosFieldValueException.class, () -> mariosyService.createMarios(mariosDTO));
    }

    @Test
    public void shouldCreateMarios() {
        // given
        UserEntity creator = this.users.get(0);
        UserEntity receiver = this.users.get(1);

        when(userRepository.findUserByExternalId(creator.getExternalId())).thenReturn(Optional.of(creator));
        when(userRepository.findUsersByExternalIdIn(new HashSet<>(Arrays.asList(receiver.getExternalId())))).thenReturn(Arrays.asList(receiver));

        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setCreatorExternalId(creator.getExternalId());
        mariosDTO.setTitle("title one");
        mariosDTO.setComment("comment one");
        mariosDTO.setType(MariosType.MARIOS_T1);
        mariosDTO.setReceiversExternalIds(new HashSet<UUID>(Arrays.asList(receiver.getExternalId())));
        // when
        try {
            MariosDTO returnedMariosDTO = mariosyService.createMarios(mariosDTO);
            // then
            assertNotNull(returnedMariosDTO.getExternalId());
            assertEquals(returnedMariosDTO.getTitle(), mariosDTO.getTitle());
            assertEquals(returnedMariosDTO.getComment(), mariosDTO.getComment());
            assertEquals(returnedMariosDTO.getCreatorExternalId(), creator.getExternalId());
            assertIterableEquals(returnedMariosDTO.getReceiversExternalIds(), new HashSet<>(Arrays.asList(receiver.getExternalId())));
        } catch (IllegalMariosFieldValueException e) {
            fail(String.format("createMarios should not throw IllegalMariosFieldValueException: %s", e));
        }
    }

}
