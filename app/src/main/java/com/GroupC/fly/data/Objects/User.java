package com.GroupC.fly.data.Objects;

import java.util.Vector;

enum relationshipStatus{
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOW,
    IAR //in a relationship
}

public class User extends Entity{

    /** DATA MEMBERS **/

    private String email, job, almaMatter, username, firstName, lastName, userID;
    private int age;
    private relationshipStatus relationshipStatus;
    private Vector<User> friends;

    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    //Person c'tor.
    public User(String i_email, String i_name, String i_username, String i_job, String i_almaMatter, int i_age, Address i_address,
                relationshipStatus i_relationshipStatus){
        setName(i_name);
        setAddress(i_address);
        email = i_email;
        username = i_username;
        job = i_job;
        almaMatter = i_almaMatter;
        relationshipStatus = i_relationshipStatus;
        age = i_age;
        // TODO: id generator via firebase.
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String i_email) {
        this.email = i_email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String i_job) {
        this.job = i_job;
    }

    public String get_almaMatter() {
        return almaMatter;
    }

    public void set_almaMatter(String i_almaMatter) {
        this.almaMatter = i_almaMatter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String i_username) {
        this.username = i_username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int i_age) {
        this.age = i_age;
    }

    public Vector<User> getFriends() {
        return friends;
    }

    public void setFriends(Vector<User> friends) {
        this.friends = friends;
    }

    //Returns the relationship status of the user as a string
    public String getRelationship_status_string() {
        switch (relationshipStatus){
            case MARRIED:
                return "Married";
            case SINGLE:
                return "Single";
            case DIVORCED:
                return "Divorced";
            case WIDOW:
                return "Widow";
            case IAR:
                return "In a relationship";
            default:
                return null;
        }
    }

    public relationshipStatus getRelationship_status() {
        return relationshipStatus;
    }

    public void setRelationship_status(relationshipStatus i_relationshipStatus) {
        this.relationshipStatus = i_relationshipStatus;
    }
}
