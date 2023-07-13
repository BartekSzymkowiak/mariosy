package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.repository.*;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Mariosy
{
    protected static final int MAX_COMMENT_LENGTH =256;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MariosRepository mariosRepository;

    public Mariosy() {
    }

    public Set<User> getUsers() {
        return Sets.newHashSet(userRepository.findAll());
    }

    public Set<Marios> getMarioses() {
        return Sets.newHashSet(mariosRepository.findAll());
    }

    public void createUser(String firstName, String lastName, String email){
        Optional <User> userWithThisEmail = userRepository.findByEmail(email);
        if (userWithThisEmail.isPresent()){
           throw new IllegalArgumentException("User with this email already exists");
        }
        User user = new User(firstName, lastName, email);
        userRepository.save(user);
    }

    public void createMarios(Integer creatorId, Set<Integer> receiversIds, MariosType type, String comment){

        if (Objects.isNull(creatorId)){
            throw new IllegalArgumentException("creatorId is null");
        }
        else if (receiversIds.isEmpty()){
            throw new IllegalArgumentException("receiversIds is empty");
        }
        else if (receiversIds.contains(creatorId)){
            throw new IllegalArgumentException("receiversIds contains creatorId");
        }
        else if (!MariosType.checkIfTypeExists(type)){
            throw new IllegalArgumentException("MariosType does not exists");
        }
        else if (comment!=null && comment.length() > MAX_COMMENT_LENGTH){
            throw new IllegalArgumentException("Comment is too long");
        }
        else{
            Marios marios = new Marios(creatorId, receiversIds, type);
            if (comment!=null && comment.length()>0 ){
                marios.setComment(comment);
            }
            mariosRepository.save(marios);
        }
    }

    public Set<Marios> getMariosesCreatedByUser(Integer creatorId){
        Set<Marios> marioses;
        marioses = mariosRepository.findByCreatorId(creatorId);
        return marioses;
    }


//    public Set<Marios> getMariosesReceivedByUser(Integer receiverId){
//        Set<Marios> marioses;
//        marioses =
//        return marioses;
//    }


//
//    // TODO remove this
//    List<User> employees =   Arrays.asList(new User("John","Doe", "john.d@email.com"),
//            new User("Johny","Doe","johny.d@email.com"),
//            new User("Johan","Doe","johan.d@email.com"),
//            new User("Jan","Doe","jan.d@email.com"));
//        userRepository.saveAll(employees);


}
