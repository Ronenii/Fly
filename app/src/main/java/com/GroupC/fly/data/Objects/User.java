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

    private String email, job, almaMatter, username, firstName, lastName, userID, nickname; // TODO: remove ID, set email as key for user.
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

    public User() {};
    //Person c'tor.
    public User(String email, String name, String username, String job, String almaMatter, int age, Address address,
                relationshipStatus relationshipStatus) {
        setName(name);
        setAddress(address);
        this.email = email;
        this.username = username;
        this.job = job;
        this.almaMatter = almaMatter;
        this.relationshipStatus = relationshipStatus;
        this.age = age;
    }


    //Returns the relationship status of the user as a string
    public String getRelationshipStatusString() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAlmaMatter() {
        return almaMatter;
    }

    public void setAlmaMatter(String almaMatter) {
        this.almaMatter = almaMatter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}