package com.deloitte.ads.mariosy.repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Entity()
public class Marios {

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
    private Set<User> receivers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_creator_id")
    private User creator;

    public Marios(){
    }

    public Marios(int creatorId, Set<Integer> receiversIds, MariosType type) {
        // TODO
        this.type = type;
        this.creationDateTime = LocalDateTime.now();
        this.comment = "";
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

    public MariosType getType() {
        return type;
    }

    public void setType(MariosType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
