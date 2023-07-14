package com.deloitte.ads.mariosy.DTO;

import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.MariosType;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class MariosDTO {

    private Long id;
    private Long creatorId;
    private Set<Long> receiversIds;
    private String comment;
    private MariosType type;
    private Instant creationInstant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Set<Long> getReceiversIds() {
        return receiversIds;
    }

    public void setReceiversIds(Set<Long> receiversIds) {
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

    public Instant getCreationInstant() {
        return creationInstant;
    }
    public void setCreationInstant(Instant creationInstant) {
        this.creationInstant = creationInstant;
    }

    public static MariosDTO mapMariosEntityToMariosDTO(MariosEntity mariosEntity){
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setId(mariosEntity.getId());
        mariosDTO.setCreatorId(mariosEntity.getCreator().getId());
        mariosDTO.setReceiversIds(mariosEntity.getReceivers().stream().map(r -> r.getId()).collect(Collectors.toSet()));
        mariosDTO.setComment(mariosEntity.getComment());
        mariosDTO.setType(mariosEntity.getType());
        mariosDTO.setCreationInstant(mariosEntity.getCreationInstant());
        return mariosDTO;
    }

}
