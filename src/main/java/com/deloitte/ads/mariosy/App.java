package com.deloitte.ads.mariosy;

import java.util.*;
import java.util.stream.Collectors;

public class App
{
    public Set<User> getUsers() {
        return users;
    }

    public Set<Marios> getMarioses() {
        return marioses;
    }

    protected Set<User> users;

    public App(Set<User> users) {
        this.users = users;
        this.marioses = new HashSet<Marios>();
    }

    protected Set<Marios> marioses;

    protected final int maxCommentLength=256;


    public boolean createUser(String firstName, String lastName){
        User user = new User(firstName, lastName);
        this.users.add(user);
        return true;
    }

    public boolean createMarios(User creator, Set<User> receivers, MariosType type, String comment){

        if (Objects.isNull(creator)){
            return false;
        }
        else if (receivers.isEmpty()){
            return false;
        }
        else if (receivers.contains(creator)){
            return false;
        }
        else if (!MariosType.checkIfTypeExists(type)){
            return false;
        }
        else if (comment!=null && comment.length() > maxCommentLength){
            return false;
        }
        else{
            Marios marios = new Marios(creator, receivers, type);
            if (comment!=null && comment.length()>0 ){
                marios.setComment(comment);
            }
            this.marioses.add(marios);
            return true;
        }
    }


    public List<Marios> getSortedMariosesCreatedByUser(User creator){
        List<Marios> marioses;
        marioses = this.marioses.stream().filter(m -> m.getCreator().equals(creator)).sorted(Comparator.comparing(Marios::getCreationDateTime)).collect(Collectors.toList());
        return marioses;
    }


    public List<Marios> getSortedMariosesReceivedByUser(User receiver){
        List<Marios> marioses;
        marioses = this.marioses.stream().filter(m -> m.getReceivers().contains(receiver)).sorted(Comparator.comparing(Marios::getCreationDateTime)).collect(Collectors.toList());
        return marioses;
    }


}
