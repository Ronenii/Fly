package com.GroupC.fly.data.Objects;

import java.util.Vector;

public class Page extends Entity{

    /** DATA MEMBERS **/
    private Vector<User> followers;
    private Vector<User> admins;

    private Vector<Community> communities;


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

    public Vector<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Vector<User> followers) {
        this.followers = followers;
    }

    public Vector<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Vector<User> admins) {
        this.admins = admins;
    }






}
