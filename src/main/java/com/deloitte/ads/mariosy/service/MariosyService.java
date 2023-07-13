package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.MariosType;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.repository.*;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MariosyService
{
    protected static final int MAX_COMMENT_LENGTH =256;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MariosRepository mariosRepository;

    public MariosyService() {
    }

    public Set<MariosEntity> getMarioses() {
        return Sets.newHashSet(mariosRepository.findAll());
    }

    public void createMarios(MariosDTO mariosDTO) throws MariosCreationFailedException {

        Long creatorId = mariosDTO.getCreatorId();
        Set<Long> receiversIds = mariosDTO.getReceiversIds();
        MariosType mariosType = mariosDTO.getType();
        String comment = mariosDTO.getComment();

        if (Objects.isNull(creatorId)){
            throw new MariosCreationFailedException("creatorId is null");
        }
        else if (receiversIds.isEmpty()){
            throw new MariosCreationFailedException("receiversIds is empty");
        }
        else if (receiversIds.contains(creatorId)){
            throw new MariosCreationFailedException("receiversIds contains creatorId");
        }
        else if (!MariosType.checkIfTypeExists(mariosType)){
            throw new MariosCreationFailedException("MariosType does not exists");
        }
        else if (StringUtils.isBlank(comment)){
            throw new MariosCreationFailedException("Comment is blank");
        }
        else if(comment.length() > MAX_COMMENT_LENGTH){
            throw new MariosCreationFailedException("Comment is too long");
        }
        else{
            Optional<UserEntity> optionalCreator = userRepository.findUserById(creatorId);
            Set<UserEntity> receivers = userRepository.findUsersByIdIn(receiversIds);

            if (optionalCreator.isEmpty()){
                throw new MariosCreationFailedException("Creator does not exists");
            }
            if (receivers.isEmpty() || receivers.size()!=receiversIds.size()){
                throw new MariosCreationFailedException("Receivers are not correct");
            }
            MariosEntity mariosEntity = new MariosEntity(optionalCreator.get(), receivers, mariosType, comment);
            mariosRepository.save(mariosEntity);
        }
    }

    public Set<MariosEntity> getMariosesCreatedByUser(Long creatorId){
        Set<MariosEntity> marioses;
        marioses = Sets.newHashSet(mariosRepository.findMariosEntitiesByCreatorId(creatorId));
        return marioses;
    }

    public Set<MariosEntity> getMariosesReceivedByUser(Long receiverId){

        Optional<UserEntity> userEntity = userRepository.findUserById(receiverId);
        if (userEntity.isEmpty()){
            throw new IllegalArgumentException("User does not exists");
        }
        Set<MariosEntity> marioses = userEntity.get().getReceived_marioses();
        return marioses;
    }


}
