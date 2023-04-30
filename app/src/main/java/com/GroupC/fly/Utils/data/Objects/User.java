package com.GroupC.fly.Utils.data.Objects;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.GroupC.fly.ActivityLogic.values;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
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

    private String email, password, job, almaMatter, username,
            firstName, lastName, nickname;
    private int age; // TODO: save date of birth instead of age. write getAge method that will calculate the age.
    private relationshipStatus relationshipStatus;
    private Vector<User> friends;

    private Calendar dateOfBirth;

    /**
     * METHODS
     **/

    public User() {} // empty c'tor.
    public User(String email, String firstName, String lastName, String username, String job, String almaMatter, int age, Address address,
                relationshipStatus relationshipStatus) {
        setAddress(address);
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Function for setting the date of birth using Java's Calendar class.
     *
     * @param dateOfBirth The date of birth.
     * @return True on success, otherwise false.
     * */
    public boolean setDateOfBirth(Calendar dateOfBirth) {
        if (dateOfBirth.after(Calendar.getInstance())) {
            Log.v(TAG, "Invalid birthdate.");
            return false;
        }

        this.dateOfBirth = dateOfBirth;
        return true;
    }

    /**
     * Function for setting the date of birth using a string as an argument.
     *
     * @param dateOfBirth The date of birth.
     * @return True on success, otherwise false.
     * */
    public boolean setDateOfBirth(String dateOfBirth) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(values.DOB_PARSING_FORMAT, Locale.getDefault());
        try {
            cal.setTime(Objects.requireNonNull(sdf.parse(dateOfBirth)));
            return setDateOfBirth(cal);
        } catch (ParseException exception) {
            Log.d(TAG, exception.getMessage());
            return false;
        }
    }

    /**
     * Function for getting the users age.
     *
     * @return The users age.
     * */
    public int getUserAge() {
        if (this.dateOfBirth.after(Calendar.getInstance())) return Calendar.getInstance().get(Calendar.YEAR) - this.dateOfBirth.get(Calendar.YEAR);
        return Math.max(0, Calendar.getInstance().get(Calendar.YEAR) - this.dateOfBirth.get(Calendar.YEAR) - 1);
    }

    public Calendar getDateOfBirth() { return this.dateOfBirth; }
}