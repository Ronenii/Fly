package com.GroupC.fly.data.Objects;

public class Community extends Entity{

    // Data Members

    private Person members[];
    private Person admins[];
    private Person moderators[];

    // Methods

    public Community(int i_id, String i_name){
        setId(i_id);
        setName(i_name);
    }
    public void createEntityInDB(){}

    public Person[] getMembers() {
        return members;
    }

    public void setMembers(Person[] members) {
        this.members = members;
    }

    public Person[] getAdmins() {
        return admins;
    }

    public void setAdmins(Person[] admins) {
        this.admins = admins;
    }

    public Person[] getModerators() {
        return moderators;
    }

    public void setModerators(Person[] moderators) {
        this.moderators = moderators;
    }
}