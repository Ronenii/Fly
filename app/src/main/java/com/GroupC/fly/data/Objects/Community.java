package com.GroupC.fly.data.Objects;

import java.util.Vector;

public class Community extends Entity{

    /** DATA MEMBERS **/

    private Vector<User> members;
    private Vector<User> admins;
    private Vector<User> moderators;


    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    public Community(int id, String name){
        setId(id);
        setName(name);
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