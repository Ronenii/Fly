package com.GroupC.fly.data.Objects;

import java.util.Vector;

enum relationshipStatus{
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOW,
    IAR //in a relationship
}

public class User extends Entity {

    /**
     * DATA MEMBERS
     **/

    private String email, job, almaMatter, username, firstName, lastName, userID;
    private int age;
    private relationshipStatus relationshipStatus;
    private Vector<User> friends;

    /**
     * IMPLEMENTATION OF ABSTRACT METHODS
     **/
    public void createEntityInDB() {
    }

    public void updateEntityInDB() {
    }

    public void deleteEntityInDB() {
    }

    /**
     * METHODS
     **/

    //Person c'tor.
    public User(String i_email, String i_name, String i_username, String i_job, String i_almaMatter, int i_age, Address i_address,
                relationshipStatus i_relationshipStatus) {
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
}