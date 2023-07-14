package com.deloitte.ads.mariosy.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user_account")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @ManyToMany(mappedBy = "receivers")
    private Set<MariosEntity> received_marioses;
    @OneToMany(
            mappedBy = "creator",
            cascade = {CascadeType.ALL})
    private Set<MariosEntity> created_marioses;

    public UserEntity(){
    }

    public UserEntity(String firstName, String lastName, String email) {
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

    public Set<MariosEntity> getReceived_marioses() {
        return received_marioses;
    }

    public Set<MariosEntity> getCreated_marioses() {
        return created_marioses;
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
