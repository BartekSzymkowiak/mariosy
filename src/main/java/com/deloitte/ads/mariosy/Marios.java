package com.deloitte.ads.mariosy;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Marios {
    public Marios(User creator, Set<User> receivers, MariosType type) {
        this.creator = creator;
        this.receivers = receivers;
        this.type = type;
        this.creationDateTime = LocalDateTime.now();
        this.id = UUID.randomUUID();
        this.comment = "";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<User> receivers) {
        this.receivers = receivers;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    private UUID id;
    private User creator;

    private Set<User> receivers;

    private LocalDateTime creationDateTime;

    public MariosType getType() {
        return type;
    }

    public void setType(MariosType type) {
        this.type = type;
    }

    private MariosType type;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;

    @Override
    public String toString() {
        String start = "Marios\n";
        String creator = this.creator.getFirstName() + " " + this.creator.getLastName();

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(start).append(String.format("creator: %s",creator))
                .append("\n")
                .append(String.format("type: %s",this.type.toString()))
                .append("\n")
                .append(String.format("Comment: %s",this.comment))
                .append("\nReceivers:\n");
        String receiver;
        for(User u : this.receivers){
            sBuilder.append(String.format("\t %s %s",u.getFirstName(),u.getLastName()));
        }

        return sBuilder.toString();
    }
}
