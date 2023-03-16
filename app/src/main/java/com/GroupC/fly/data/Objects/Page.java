package com.GroupC.fly.data.Objects;

public class Page extends Entity{
    // Data Members

    private Person followers[];
    private Person admins[];

    // Methods

    // Page c'tor
    public Page(int i_id, String i_name){
        setId(i_id);
        setName(i_name);
    }

    public void createEntityInDB(){}

    public Person[] getFollowers() {
        return followers;
    }

    public void setFollowers(Person[] followers) {
        this.followers = followers;
    }

    public Person[] getAdmins() {
        return admins;
    }

    public void setAdmins(Person[] admins) {
        this.admins = admins;
    }






}
