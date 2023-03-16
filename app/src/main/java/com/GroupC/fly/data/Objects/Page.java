package com.GroupC.fly.data.Objects;

public class Page extends Entity{

    /** DATA MEMBERS **/
    private Person[] followers;
    private Person[] admins;

    private Community[] communities;


    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    // Page c'tor
    public Page(int i_id, String i_name){
        setId(i_id);
        setName(i_name);
    }

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
