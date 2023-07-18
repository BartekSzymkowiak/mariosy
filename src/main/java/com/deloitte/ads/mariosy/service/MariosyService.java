package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.DTO.MariosDTO;
import com.deloitte.ads.mariosy.entity.MariosEntity;
import com.deloitte.ads.mariosy.entity.MariosType;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.repository.*;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class MariosyService
{
    private final UserRepository userRepository;

    private final MariosRepository mariosRepository;

    @Autowired
    public MariosyService(UserRepository userRepository, MariosRepository mariosRepository) {
        this.userRepository = userRepository;
        this.mariosRepository = mariosRepository;
    }

    public Set<MariosEntity> getMarioses() {
        return Sets.newHashSet(mariosRepository.findAll());
    }


    public void createMarios(MariosDTO mariosDTO) throws IllegalMariosFieldValueException {

        Long creatorId = mariosDTO.getCreatorId();
        Set<Long> receiversIds = mariosDTO.getReceiversIds();
        MariosType mariosType = mariosDTO.getType();
        String comment = mariosDTO.getComment();

        if (Objects.isNull(creatorId)){
            throw new IllegalMariosFieldValueException("creatorId is null");
        }
        else if (receiversIds.isEmpty()){
            throw new IllegalMariosFieldValueException("receiversIds is empty");
        }
        else if (receiversIds.contains(creatorId)){
            throw new IllegalMariosFieldValueException("receiversIds contains creatorId");
        }
        else if (!MariosType.checkIfTypeExists(mariosType)){
            throw new IllegalMariosFieldValueException("MariosType does not exists");
        }
        else if (StringUtils.isBlank(comment)){
            throw new IllegalMariosFieldValueException("Comment is blank");
        }
        else if(comment.length() > MariosEntity.MAX_COMMENT_LENGTH){
            throw new IllegalMariosFieldValueException("Comment is too long");
        }
        else{
            Optional<UserEntity> optionalCreator = userRepository.findUserById(creatorId);
            Set<UserEntity> receivers = userRepository.findUsersByIdIn(receiversIds);

            if (optionalCreator.isEmpty()){
                throw new IllegalMariosFieldValueException("Creator does not exists");
            }
            if (receivers.isEmpty() || receivers.size()!=receiversIds.size()){
                throw new IllegalMariosFieldValueException("Receivers are not correct");
            }
            MariosEntity mariosEntity = new MariosEntity(mariosType, comment);
            mariosEntity.setCreator(optionalCreator.get());
            mariosEntity.setReceivers(receivers);
            mariosRepository.save(mariosEntity);
        }
    }

    public Set<MariosEntity> getMariosesCreatedByUser(Long creatorId){
        Set<MariosEntity> marioses;
        marioses = Sets.newHashSet(mariosRepository.findMariosEntitiesByCreator_Id(creatorId));
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

    public Optional<MariosEntity> getMariosById(Long id){
        return mariosRepository.findById(id);
    }


}
