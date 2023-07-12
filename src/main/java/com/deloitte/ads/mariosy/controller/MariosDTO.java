package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.repository.MariosType;

import java.util.Set;

public class MariosDTO {
    private Integer creatorId;
    private Set<Integer> receiversIds;
    private String comment;
    private MariosType type;

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Set<Integer> getReceiversIds() {
        return receiversIds;
    }

    public void setReceiversIds(Set<Integer> receiversIds) {
        this.receiversIds = receiversIds;
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
}
