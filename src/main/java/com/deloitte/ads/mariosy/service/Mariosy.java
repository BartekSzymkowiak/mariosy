package com.deloitte.ads.mariosy.service;

import com.deloitte.ads.mariosy.CompanyData;
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

    public Mariosy() {
        this.users = new HashSet<User>(CompanyData.getEmployees());
        this.marioses = new HashSet<Marios>();
    }

    public Integer createUser(String firstName, String lastName, String email){
        if (this.users.stream().filter(u -> u.getEmail().equals(email)).count() > 0){
            return -1;
        }
        User user = new User(firstName, lastName, email);
        this.users.add(user);
        return user.getId();
    }

    public Integer createMarios(Integer creatorId, Set<Integer> receiversIds, MariosType type, String comment){

        if (Objects.isNull(creatorId)){
            return -1;
        }
        else if (receiversIds.isEmpty()){
            return -1;
        }
        else if (receiversIds.contains(creatorId)){
            return -1;
        }
        else if (!MariosType.checkIfTypeExists(type)){
            return -1;
        }
        else if (comment!=null && comment.length() > MAX_COMMENT_LENGTH){
            return -1;
        }
        else{
            Marios marios = new Marios(creatorId, receiversIds, type);
            if (comment!=null && comment.length()>0 ){
                marios.setComment(comment);
            }
            this.marioses.add(marios);
            return marios.getId();
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
