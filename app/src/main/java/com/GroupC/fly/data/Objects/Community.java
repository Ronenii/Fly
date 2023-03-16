package com.GroupC.fly.data.Objects;

import java.util.Vector;

public class Community extends Entity{

    /** DATA MEMBERS **/

    private Vector<Person> members;
    private Vector<Person> admins;
    private Vector<Person> moderators;


    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    public Community(int i_id, String i_name){
        setId(i_id);
        setName(i_name);
    }

    public Vector<Person> getMembers() {
        return members;
    }

    public void setMembers(Vector<Person> members) {
        this.members = members;
    }

    public Vector<Person> getAdmins() {
        return admins;
    }

    public void setAdmins(Vector<Person> admins) {
        this.admins = admins;
    }

    public Vector<Person> getModerators() {
        return moderators;
    }

    public void setModerators(Vector<Person> moderators) {
        this.moderators = moderators;
    }
}