package com.deloitte.ads.mariosy.repository;

import org.springframework.context.annotation.Conditional;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Entity(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "receivers")
    private Set<Marios> received_marioses;

    @OneToMany(
            mappedBy = "creator",
            cascade = {CascadeType.ALL})
    private Set<Marios> created_marioses;

    public User(){
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Marios> getReceived_marioses() {
        return received_marioses;
    }

    public void setReceived_marioses(Set<Marios> received_marioses) {
        this.received_marioses = received_marioses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
