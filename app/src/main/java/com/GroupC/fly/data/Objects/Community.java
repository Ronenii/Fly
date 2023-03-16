package com.GroupC.fly.data.Objects;

public class Community extends Entity{

    /** DATA MEMBERS **/

    private Person[] members;
    private Person[] admins;
    private Person[] moderators;


    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    public Community(int i_id, String i_name){
        setId(i_id);
        setName(i_name);
    }

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