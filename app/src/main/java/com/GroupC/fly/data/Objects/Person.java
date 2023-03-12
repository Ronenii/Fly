package com.GroupC.fly.data.Objects;

enum relationshipStatus{
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOW,
    IAR //in a relationship
}

public class Person  extends Entity{


    private String email, job, alma_matter, username;
    private int age;
    private relationshipStatus relationship_status;
    private Person friends[];


    public Person(){}

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

    public String getAlma_matter() {
        return alma_matter;
    }

    public void setAlma_matter(String alma_matter) {
        this.alma_matter = alma_matter;
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

    public Person[] getFriends() {
        return friends;
    }

    public void setFriends(Person[] friends) {
        this.friends = friends;
    }

    //Returns the relationship status of the user as a string
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

    public void setRelationship_status(relationshipStatus relationship_status) {
        this.relationship_status = relationship_status;
    }
}
