package com.deloitte.ads.mariosy.oldTests;

import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.service.MariosyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MariosyOldUnitTest {

    protected static final int MAX_COMMENT_LENGTH = 500;

    private static final String SHORT_COMMENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    private List<UserEntity> userEntities;

    private MariosyService app;

    @BeforeEach
    public void init(){
        app = new MariosyService();
    }


//    @Test
//    public void addUser(){
//        //app.createUser("John","Doe","jondoe@email.com");
//
//    }
//
//    @Test
//    void createMariosOneReceiver() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//        receiversIds.add(users.get(1).getId());
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
//        // then
//        Assertions.assertNotEquals(-1, returnedId);
//    }
//
//    @Test
//    void createMariosManyReceivers() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//        receiversIds.add(users.get(1).getId());
//        receiversIds.add(users.get(2).getId());
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
//        // then
//        Assertions.assertNotEquals(-1, returnedId);
//    }
//    @Test
//    void createMariosCreatorIsReceiver() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//        receiversIds.add(users.get(0).getId());
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
//        // then
//        Assertions.assertEquals(-1, returnedId);
//    }
//
//    @Test
//    void createMariosCreatorHasSameNameAsReceiver() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receivers = new HashSet<Integer>();
//        User receiver = new User(creator.getFirstName(), creator.getLastName(),"other.email."+creator.getEmail());
//        receivers.add(receiver.getId());
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receivers, MariosType.MARIOS_T1, SHORT_COMMENT);
//        // then
//        Assertions.assertNotEquals(-1, returnedId);
//    }
//
//    @Test
//    void createMariosNoReceivers() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receivers = new HashSet<Integer>();
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receivers, MariosType.MARIOS_T1, SHORT_COMMENT);
//        // then
//        Assertions.assertEquals(-1 ,returnedId);
//    }
//
//    @Test
//    void createMariosEmptyComment() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//        receiversIds.add(users.get(1).getId());
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, null);
//        // then
//        Assertions.assertNotEquals(-1 ,returnedId);
//    }
//
//    @Test
//    void createMariosCommentTooLong() {
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//        receiversIds.add(users.get(1).getId());
//        String longComment =  new String(new char[MAX_COMMENT_LENGTH +1]).replace('\0', 'x');
//        // when
//        Integer returnedId = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, longComment);
//        // then
//        Assertions.assertEquals(-1, returnedId);
//    }
//
//    @Test
//    void getMariosesCreatedByUser(){
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIDs = new HashSet<Integer>();
//        receiversIDs.add(users.get(1).getId());
//        app.createMarios(creator.getId(), receiversIDs, MariosType.MARIOS_T1, SHORT_COMMENT);
//        app.createMarios(creator.getId(), receiversIDs, MariosType.MARIOS_T2, SHORT_COMMENT);
//        app.createMarios(creator.getId(), receiversIDs, MariosType.MARIOS_T3, SHORT_COMMENT);
//        // when
//        List<Marios> marioses = app.getSortedMariosesCreatedByUser(creator.getId());
//        // then
//        assertThat(marioses).filteredOn(m -> m.getCreatorId().equals(creator.getId())).hasSize(3);
//    }
//
//    @Test
//    void getMariosesCreatedByUserEmpty(){
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//        User notCreator = users.get(1);
//        receiversIds.add(notCreator.getId());
//        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
//        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T2, SHORT_COMMENT);
//        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T3, SHORT_COMMENT);
//        // when
//        List<Marios> marioses = app.getSortedMariosesCreatedByUser(notCreator.getId());
//        // then
//        assertThat(marioses).filteredOn(m -> m.getCreatorId().equals(notCreator.getId())).hasSize(0);
//    }
//
//    @Test
//    void getSortedMariosesReceivedByUser(){
//        // given
//        User creator = users.get(0);
//        Set<Integer> receiversIds = new HashSet<Integer>();
//
//        User receiver = users.get(1);
//        receiversIds.add(receiver.getId());
//
//        Set<Integer> otherReceivers = new HashSet<Integer>();
//        receiversIds.add(users.get(2).getId());
//
//        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
//        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T2, SHORT_COMMENT);
//        app.createMarios(creator.getId(), otherReceivers, MariosType.MARIOS_T3, SHORT_COMMENT);
//        // when
//        List<Marios> marioses = app.getSortedMariosesReceivedByUser(receiver.getId());
//        // then
//        assertThat(marioses).filteredOn(m -> m.getReceiversIds().contains(receiver.getId())).hasSize(2);
//    }

}