package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.repository.Marios;
import com.deloitte.ads.mariosy.repository.MariosType;
import com.deloitte.ads.mariosy.repository.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Mariosy
{
    protected static final int MAX_COMMENT_LENGTH =256;

    public Set<User> getUsers() {
        return users;
    }

    public Set<Marios> getMarioses() {
        return marioses;
    }

    protected Set<User> users;

    protected Set<Marios> marioses;

    public Mariosy(Set<User> users) {
        this.users = users;
        this.marioses = new HashSet<Marios>();
    }

    public boolean createUser(String firstName, String lastName){
        User user = new User(firstName, lastName);
        this.users.add(user);
        return true;
    }

    public boolean createMarios(Integer creatorId, Set<Integer> receiversIds, MariosType type, String comment){

        if (Objects.isNull(creatorId)){
            return false;
        }
        else if (receiversIds.isEmpty()){
            return false;
        }
        else if (receiversIds.contains(creatorId)){
            return false;
        }
        else if (!MariosType.checkIfTypeExists(type)){
            return false;
        }
        else if (comment!=null && comment.length() > MAX_COMMENT_LENGTH){
            return false;
        }
        else{
            Marios marios = new Marios(creatorId, receiversIds, type);
            if (comment!=null && comment.length()>0 ){
                marios.setComment(comment);
            }
            this.marioses.add(marios);
            return true;
        }
    }

    public List<Marios> getSortedMariosesCreatedByUser(Integer creatorId){
        List<Marios> marioses;
        marioses = this.marioses.stream().filter(m -> m.getCreatorId().equals(creatorId)).sorted(Comparator.comparing(Marios::getCreationDateTime)).collect(Collectors.toList());
        return marioses;
    }


    public List<Marios> getSortedMariosesReceivedByUser(Integer receiverId){
        List<Marios> marioses;
        marioses = this.marioses.stream().filter(m -> m.getReceiversIds().contains(receiverId)).sorted(Comparator.comparing(Marios::getCreationDateTime)).collect(Collectors.toList());
        return marioses;
    }


}
