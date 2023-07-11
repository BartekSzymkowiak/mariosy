package com.deloitte.ads.mariosy.repository;

import java.util.concurrent.atomic.AtomicInteger;

public class User {

    public Integer getId() {
        return id;
    }

    private static AtomicInteger seq = new AtomicInteger();
    public void setId(Integer id) {
        this.id = id;
    }

    private int id;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = seq.incrementAndGet();
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    @Override
    public String toString() {
        return String.format("First name: %s, Last name: %s, UUID: %s",this.firstName, this.lastName,this.id);
    }
}
