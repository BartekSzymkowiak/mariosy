package com.deloitte.ads.mariosy.mappers;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MariosMapper {
    public MariosMapper() {
    }

    public MariosDTO mariosEntityToMariosDTO(MariosEntity mariosEntity){
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setExternalId(mariosEntity.getExternalId());
        mariosDTO.setCreatorExternalId(mariosEntity.getCreator().getExternalId());
        List<UserEntity> receivers =  Lists.newArrayList(mariosEntity.getReceivers());
        mariosDTO.setReceiversExternalIds(receivers.stream().map(r -> r.getExternalId()).collect(Collectors.toSet()));
        mariosDTO.setTitle(mariosEntity.getTitle());
        mariosDTO.setComment(mariosEntity.getComment());
        mariosDTO.setType(mariosEntity.getType());
        mariosDTO.setCreationInstant(mariosEntity.getCreationInstant());
        UserEntity creatorEntity = mariosEntity.getCreator();
        mariosDTO.setCreatorFirstName(creatorEntity.getFirstName());
        mariosDTO.setCreatorLastName(creatorEntity.getLastName());
        mariosDTO.setReceiversNames(receivers.stream().map(r -> String.format("%s %s", r.getFirstName(), r.getLastName())).collect(Collectors.toList()));
        return mariosDTO;
    }

    public Optional<MariosDTO> optionalMariosEntityToOptionalMariosDTO(Optional<MariosEntity> mariosEntityOptional){
        if (mariosEntityOptional.isPresent()){
            return  Optional.ofNullable(mariosEntityToMariosDTO(mariosEntityOptional.get()));
        }
        return Optional.ofNullable(null);
    }


}
