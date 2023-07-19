package com.deloitte.ads.mariosy.mappers;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MariosMapper {
    public MariosMapper() {
    }

    public MariosDTO mariosEntityToMariosDTO(MariosEntity mariosEntity){
        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setExternalId(mariosEntity.getExternalId());
        mariosDTO.setCreatorExternalId(mariosEntity.getCreator().getExternalId());
        mariosDTO.setReceiversExternalIds(mariosEntity.getReceivers().stream().map(r -> r.getExternalId()).collect(Collectors.toSet()));
        mariosDTO.setComment(mariosEntity.getComment());
        mariosDTO.setType(mariosEntity.getType());
        mariosDTO.setCreationInstant(mariosEntity.getCreationInstant());
        return mariosDTO;
    }

    public Optional<MariosDTO> optionalMariosEntityToOptionalMariosDTO(Optional<MariosEntity> mariosEntityOptional){
        if (mariosEntityOptional.isPresent()){
            return  Optional.ofNullable(mariosEntityToMariosDTO(mariosEntityOptional.get()));
        }
        return Optional.ofNullable(null);
    }


}
