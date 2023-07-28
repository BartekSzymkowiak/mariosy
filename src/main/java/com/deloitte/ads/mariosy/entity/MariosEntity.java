package com.deloitte.ads.mariosy.entity;

import com.deloitte.ads.mariosy.service.UserService;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity()
public class MariosEntity {

    public static final int MAX_COMMENT_LENGTH = 255;
    public static final int MAX_TITLE_LENGTH = 127;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "external_id", unique = true)
    private UUID externalId;

    @Column(name = "creation_timestamp")
    private Instant creationInstant;

    @Column(name = "title", length = MAX_TITLE_LENGTH)
    private String title;

    @Column(name = "comment", length = MAX_COMMENT_LENGTH)
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
        this.externalId = UUID.randomUUID();
    }

    public MariosEntity(MariosType type, String comment, String title) {
        this.type = type;
        this.comment = comment;
        this.creationInstant = Instant.now();
        this.externalId = UUID.randomUUID();
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public Instant getCreationInstant() {
        return creationInstant;
    }

    public void setCreationInstant(Instant creationInstant) {
        this.creationInstant = creationInstant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        for(UserEntity userEntity : receivers){
            userEntity.getCreated_marioses().add(this);
        }
    }

    public boolean removeReceiver(UserEntity userEntityToRemove){
        return this.receivers.remove(userEntityToRemove);
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
        creator.getCreated_marioses().add(this);
    }

}
