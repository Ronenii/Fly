package com.GroupC.fly.data.Objects;

import java.util.Vector;

enum relationshipStatus{
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOW,
    IAR //in a relationship
}

public class Person  extends Entity{

    /** DATA MEMBERS **/

    private String m_email, m_job, m_alma_matter, m_username, m_first_name, m_last_name, m_nickname;
    private int m_age;
    private relationshipStatus m_relationship_status;
    private Vector<Person> friends;

    /** IMPLEMENTATION OF ABSTRACT METHODS **/
    public void createEntityInDB(){}
    public void updateEntityInDB(){}
    public void deleteEntityInDB(){}

    /** METHODS **/

    //Person c'tor.

    public Person(){}
    public Person(String i_email, String i_name, String i_username, String i_job, String i_almaMatter, int i_age, Address i_address,
                  relationshipStatus i_relationshipStatus){
        setName(i_name);
        setAddress(i_address);
        m_email = i_email;
        m_username = i_username;
        m_job = i_job;
        m_alma_matter = i_almaMatter;
        m_relationship_status = i_relationshipStatus;
        m_age = i_age;
        // TODO: id generator via firebase.
    }

    public String getFirstName() {
        return m_first_name;
    }

    public void setFirstName(String m_first_name) {
        this.m_first_name = m_first_name;
    }

    public String getLastName() {
        return m_last_name;
    }

    public void setLastName(String m_last_name) {
        this.m_last_name = m_last_name;
    }

    public String getNickname() {
        return m_nickname;
    }

    public void setNickname(String m_nickname) {
        this.m_nickname = m_nickname;
    }

    public String getEmail() {
        return m_email;
    }

    public void setEmail(String i_email) {
        this.m_email = i_email;
    }

    public String getJob() {
        return m_job;
    }

    public void setJob(String i_job) {
        this.m_job = i_job;
    }

    public String get_almaMatter() {
        return m_alma_matter;
    }

    public void set_almaMatter(String i_almaMatter) {
        this.m_alma_matter = i_almaMatter;
    }

    public String getUsername() {
        return m_username;
    }

    public void setUsername(String i_username) {
        this.m_username = i_username;
    }

    public int getAge() {
        return m_age;
    }

    public void setAge(int i_age) {
        this.m_age = i_age;
    }

    public Vector<Person> getFriends() {
        return friends;
    }

    public void setFriends(Vector<Person> friends) {
        this.friends = friends;
    }

    //Returns the relationship status of the user as a string
    public String getRelationship_status_string() {
        switch (m_relationship_status){
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
        return m_relationship_status;
    }

    public void setRelationship_status(relationshipStatus i_relationshipStatus) {
        this.m_relationship_status = i_relationshipStatus;
    }
}
