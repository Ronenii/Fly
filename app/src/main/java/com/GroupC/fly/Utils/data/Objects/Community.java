package com.GroupC.fly.Utils.data.Objects;

import java.util.Vector;

public class Community extends Entity{

    /** DATA MEMBERS **/
    private String name;  // Should be a unique key (no two communities with the same name).
    private Vector<User> members;
    private Vector<User> admins;
    private Vector<User> moderators;


    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    public Community(String name){
        this.name = name;
    }

    public Vector<User> getMembers() {
        return members;
    }

    public void setMembers(Vector<User> members) {
        this.members = members;
    }

    public Vector<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Vector<User> admins) {
        this.admins = admins;
    }

    public Vector<User> getModerators() {
        return moderators;
    }

    public void setModerators(Vector<User> moderators) {
        this.moderators = moderators;
    }
}