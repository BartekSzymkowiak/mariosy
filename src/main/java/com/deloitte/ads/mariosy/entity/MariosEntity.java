package com.deloitte.ads.mariosy.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity()
public class MariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "type")
    private MariosType type;

    @ManyToMany
    @JoinTable(
            name = "marios_receiver",
            joinColumns = @JoinColumn(name = "marios_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> receivers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_creator_id")
    private UserEntity creator;

    public MariosEntity(){
    }

    public MariosEntity(UserEntity creator, Set<UserEntity> receivers, MariosType type, String comment) {
        this.creator = creator;
        this.receivers = receivers;
        this.type = type;
        this.comment = comment;
        this.creationDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MariosType getType() {
        return type;
    }

    public void setType(MariosType type) {
        this.type = type;
    }

    public Set<UserEntity> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<UserEntity> receivers) {
        this.receivers = receivers;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

}
