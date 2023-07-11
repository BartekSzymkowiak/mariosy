package com.deloitte.ads.mariosy;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Marios {
    public Marios(int creatorId, Set<Integer> receiversIds, MariosType type) {
        this.creatorId = creatorId;
        this.receiversIds = receiversIds;
        this.type = type;
        this.creationDateTime = LocalDateTime.now();
        this.id = seq.incrementAndGet();
        this.comment = "";
    }
    private static AtomicInteger seq = new AtomicInteger();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Set<Integer> getReceiversIds() {
        return receiversIds;
    }

    public void setReceiversIds(Set<Integer> receiversIds) {
        this.receiversIds = receiversIds;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    private Integer id;
    private Integer creatorId;

    private Set<Integer> receiversIds;

    private LocalDateTime creationDateTime;

    public MariosType getType() {
        return type;
    }

    public void setType(MariosType type) {
        this.type = type;
    }

    private MariosType type;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;

}
