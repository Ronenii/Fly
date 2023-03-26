package com.GroupC.fly.data.Objects;

import java.util.Vector;

enum relationshipStatus{
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOW,
    IAR //in a relationship
}

public class Person extends Entity{

    /* DATA MEMBERS */
    private String email, job, alma_matter, username;
    private int age;
    private relationshipStatus relationship_status;
    private Vector<Person> friends;

    /* IMPLEMENTATION OF ABSTRACT METHODS */
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    /**Person c'tor.**/
    public Person(String i_email, String i_name, String i_username, String i_job, String i_almaMatter, int i_age, Address i_address,
                  relationshipStatus i_relationshipStatus){
        setName(i_name);
        setAddress(i_address);
        email = i_email;
        username = i_username;
        job = i_job;
        alma_matter = i_almaMatter;
        relationship_status = i_relationshipStatus;
        age = i_age;
        // TODO: id generator via firebase.
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
        return alma_matter;
    }

    public void set_almaMatter(String almaMatter) {
        this.alma_matter = almaMatter;
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

    /**Returns the relationship status of the user as a string**/
    public String getRelationship_status_string() {
        switch (relationship_status){
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
        return relationship_status;
    }

    public void setRelationship_status(relationshipStatus relationshipStatus) {
        this.relationship_status = relationshipStatus;
    }
}
