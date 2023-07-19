package com.deloitte.ads.mariosy.DTO;

import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.MariosType;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MariosDTO {


    private UUID externalId;
    private UUID creatorExternalId;
    private Set<UUID> receiversExternalIds;
    private String comment;
    private MariosType type;
    private Instant creationInstant;

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public UUID getCreatorExternalId() {
        return creatorExternalId;
    }

    public void setCreatorExternalId(UUID creatorId) {
        this.creatorExternalId = creatorId;
    }

    public Set<UUID> getReceiversExternalIds() {
        return receiversExternalIds;
    }

    public void setReceiversExternalIds(Set<UUID> receiversIds) {
        this.receiversExternalIds = receiversIds;
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
        mariosDTO.setExternalId(mariosEntity.getExternalId());
        mariosDTO.setCreatorExternalId(mariosEntity.getCreator().getExternalId());
        mariosDTO.setReceiversExternalIds(mariosEntity.getReceivers().stream().map(r -> r.getExternalId()).collect(Collectors.toSet()));
        mariosDTO.setComment(mariosEntity.getComment());
        mariosDTO.setType(mariosEntity.getType());
        mariosDTO.setCreationInstant(mariosEntity.getCreationInstant());
        return mariosDTO;
    }

}
