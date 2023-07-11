package com.deloitte.ads.mariosy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class AppTest {

    private final List<User> users = Arrays.asList(new User("John","Doe"),
                                             new User("Johny","Doe"),
                                             new User("Johan","Doe"),
                                             new User("Jan","Doe"));
    private final String shortComment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    private App app;

    @BeforeEach
    public void init(){
        app = new App(new HashSet<User>(users));
    }

    @Test
    void createMariosOneReceiver() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        receivers.add(users.get(1));
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosManyReceivers() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        receivers.add(users.get(1));
        receivers.add(users.get(2));
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        // then
        Assertions.assertTrue(result);
    }
    @Test
    void createMariosCreatorIsReceiver() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        receivers.add(users.get(0));
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void createMariosCreatorHasSameNameAsReceiver() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        User receiver = new User(creator.getFirstName(), creator.getLastName());
        receivers.add(receiver);
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosNoReceivers() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void createMariosEmptyComment() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        receivers.add(users.get(1));
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, null);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosCommentTooLong() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        receivers.add(users.get(1));
        String longComment =  new String(new char[app.maxCommentLength+1]).replace('\0', 'x');
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.MARIOS_T1, longComment);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void getMariosesCreatedByUser(){
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        receivers.add(users.get(1));
        app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        app.createMarios(creator, receivers, MariosType.MARIOS_T2, shortComment);
        app.createMarios(creator, receivers, MariosType.MARIOS_T3, shortComment);
        // when
        List<Marios> marioses = app.getSortedMariosesCreatedByUser(creator);
        // then
        assertThat(marioses).filteredOn(m -> m.getCreator().equals(creator)).hasSize(3);
    }

    @Test
    void getMariosesCreatedByUserEmpty(){
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        User notCreator = users.get(1);
        receivers.add(notCreator);
        app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        app.createMarios(creator, receivers, MariosType.MARIOS_T2, shortComment);
        app.createMarios(creator, receivers, MariosType.MARIOS_T3, shortComment);
        // when
        List<Marios> marioses = app.getSortedMariosesCreatedByUser(notCreator);
        // then
        assertThat(marioses).filteredOn(m -> m.getCreator().equals(notCreator)).hasSize(0);
    }

    @Test
    void getSortedMarosesReceivedByUser(){
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();

        User receiver = users.get(1);
        receivers.add(receiver);

        Set<User> otherReceivers = new HashSet<User>();
        receivers.add(users.get(2));

        app.createMarios(creator, receivers, MariosType.MARIOS_T1, shortComment);
        app.createMarios(creator, receivers, MariosType.MARIOS_T2, shortComment);
        app.createMarios(creator, otherReceivers, MariosType.MARIOS_T3, shortComment);
        // when
        List<Marios> marioses = app.getSortedMariosesReceivedByUser(receiver);
        // then
        assertThat(marioses).filteredOn(m -> m.getReceivers().contains(receiver)).hasSize(2);
    }



}