package com.GroupC.fly.data.Objects;

import java.util.Vector;

public class Page extends Entity{

    /** DATA MEMBERS **/
    private Vector<Person> followers;
    private Vector<Person> admins;

    private Vector<Community> communities;


    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    // Page c'tor
    public Page(int id, String name){
        setId(id);
        setName(name);
    }

    public Vector<Person> getFollowers() {
        return followers;
    }

    public void setFollowers(Vector<Person> followers) {
        this.followers = followers;
    }

    public Vector<Person> getAdmins() {
        return admins;
    }

    public void setAdmins(Vector<Person> admins) {
        this.admins = admins;
    }






}
