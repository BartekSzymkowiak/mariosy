package com.deloitte.ads.mariosy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class App
{
    protected Set<User> users;

    public App(Set<User> users) {
        this.users = users;
        this.marioses = new HashSet<Marios>();
    }

    protected Set<Marios> marioses;
    protected final int maxCommentLength=256;

    public static void main( String[] args )
    {

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



}
