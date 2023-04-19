package com.GroupC.fly.data.Objects;

import java.util.Vector;

enum eRelationshipStatus{
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOW,
    IAR //in a relationship
}

public class Person  extends Entity{

    /** DATA MEMBERS **/

    private String email, job, almaMatter, username, firstName, lastName, nickname;
    private int age;
    private eRelationshipStatus relationshipStatus;
    private Vector<Person> friends;

    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    //Person c'tor.

    public Person(){}
    public Person(String email, String name, String username, String job, String almaMatter, int age, Address address,
                  eRelationshipStatus relationshipStatus){
        setName(name);
        setAddress(address);
        email = email;
        username = username;
        job = job;
        almaMatter = almaMatter;
        relationshipStatus = relationshipStatus;
        age = age;
        // TODO: id generator via firebase.
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String get_almaMatter() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vector<Person> getFriends() {
        return friends;
    }

    public void setFriends(Vector<Person> friends) {
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

    public eRelationshipStatus getRelationship_status() {
        return relationshipStatus;
    }

    public void setRelationship_status(eRelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }
}
