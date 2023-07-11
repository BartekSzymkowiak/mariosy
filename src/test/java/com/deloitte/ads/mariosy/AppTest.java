package com.deloitte.ads.mariosy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class AppTest {

    private List<User> users = CompanyData.getEmployees();
    private static final String SHORT_COMMENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    private App app;

    @BeforeEach
    public void init(){
        app = new App(new HashSet<User>(this.users));
    }

    @Test
    void createMariosOneReceiver() {
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();
        receiversIds.add(users.get(1).getId());
        // when
        boolean result = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosManyReceivers() {
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();
        receiversIds.add(users.get(1).getId());
        receiversIds.add(users.get(2).getId());
        // when
        boolean result = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
        // then
        Assertions.assertTrue(result);
    }
    @Test
    void createMariosCreatorIsReceiver() {
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();
        receiversIds.add(users.get(0).getId());
        // when
        boolean result = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void createMariosCreatorHasSameNameAsReceiver() {
        // given
        User creator = users.get(0);
        Set<Integer> receivers = new HashSet<Integer>();
        User receiver = new User(creator.getFirstName(), creator.getLastName());
        receivers.add(receiver.getId());
        // when
        boolean result = app.createMarios(creator.getId(), receivers, MariosType.MARIOS_T1, SHORT_COMMENT);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosNoReceivers() {
        // given
        User creator = users.get(0);
        Set<Integer> receivers = new HashSet<Integer>();
        // when
        boolean result = app.createMarios(creator.getId(), receivers, MariosType.MARIOS_T1, SHORT_COMMENT);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void createMariosEmptyComment() {
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();
        receiversIds.add(users.get(1).getId());
        // when
        boolean result = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, null);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosCommentTooLong() {
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();
        receiversIds.add(users.get(1).getId());
        String longComment =  new String(new char[app.MAX_COMMENT_LENGTH +1]).replace('\0', 'x');
        // when
        boolean result = app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, longComment);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void getMariosesCreatedByUser(){
        // given
        User creator = users.get(0);
        Set<Integer> receiversIDs = new HashSet<Integer>();
        receiversIDs.add(users.get(1).getId());
        app.createMarios(creator.getId(), receiversIDs, MariosType.MARIOS_T1, SHORT_COMMENT);
        app.createMarios(creator.getId(), receiversIDs, MariosType.MARIOS_T2, SHORT_COMMENT);
        app.createMarios(creator.getId(), receiversIDs, MariosType.MARIOS_T3, SHORT_COMMENT);
        // when
        List<Marios> marioses = app.getSortedMariosesCreatedByUser(creator.getId());
        // then
        assertThat(marioses).filteredOn(m -> m.getCreatorId().equals(creator.getId())).hasSize(3);
    }

    @Test
    void getMariosesCreatedByUserEmpty(){
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();
        User notCreator = users.get(1);
        receiversIds.add(notCreator.getId());
        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T2, SHORT_COMMENT);
        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T3, SHORT_COMMENT);
        // when
        List<Marios> marioses = app.getSortedMariosesCreatedByUser(notCreator.getId());
        // then
        assertThat(marioses).filteredOn(m -> m.getCreatorId().equals(notCreator.getId())).hasSize(0);
    }

    @Test
    void getSortedMarosesReceivedByUser(){
        // given
        User creator = users.get(0);
        Set<Integer> receiversIds = new HashSet<Integer>();

        User receiver = users.get(1);
        receiversIds.add(receiver.getId());

        Set<Integer> otherReceivers = new HashSet<Integer>();
        receiversIds.add(users.get(2).getId());

        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T1, SHORT_COMMENT);
        app.createMarios(creator.getId(), receiversIds, MariosType.MARIOS_T2, SHORT_COMMENT);
        app.createMarios(creator.getId(), otherReceivers, MariosType.MARIOS_T3, SHORT_COMMENT);
        // when
        List<Marios> marioses = app.getSortedMariosesReceivedByUser(receiver.getId());
        // then
        assertThat(marioses).filteredOn(m -> m.getReceiversIds().contains(receiver.getId())).hasSize(2);
    }



}