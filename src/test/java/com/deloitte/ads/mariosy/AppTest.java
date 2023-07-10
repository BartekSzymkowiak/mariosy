package com.deloitte.ads.mariosy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private final List<User> users = Arrays.asList(new User("John","Doe"),
                                             new User("Johny","Doe"),
                                             new User("Johan","Doe"));
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
        boolean result = app.createMarios(creator, receivers, MariosType.T1, shortComment);
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
        boolean result = app.createMarios(creator, receivers, MariosType.T1, shortComment);
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
        boolean result = app.createMarios(creator, receivers, MariosType.T1, shortComment);
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
        boolean result = app.createMarios(creator, receivers, MariosType.T1, shortComment);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void createMariosNoReceivers() {
        // given
        User creator = users.get(0);
        Set<User> receivers = new HashSet<User>();
        // when
        boolean result = app.createMarios(creator, receivers, MariosType.T1, shortComment);
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
        boolean result = app.createMarios(creator, receivers, MariosType.T1, null);
        // then
        Assertions.assertTrue(result);
    }


}