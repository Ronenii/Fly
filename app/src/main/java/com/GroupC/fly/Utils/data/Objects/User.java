package com.GroupC.fly.Utils.data.Objects;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.GroupC.fly.ActivityLogic.values;

import com.google.firebase.firestore.DocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;

enum RelationshipStatus {
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

    private String email, job, almaMatter, username,
            firstName, lastName, nickname;
    private RelationshipStatus relationshipStatus;
    private Vector<User> friends;

    private Calendar dateOfBirth;

    /**
     * METHODS
     **/

    public User() {} // empty c'tor.
    public User(String email, String firstName, String lastName, String username, String job, String almaMatter, Address address,
                RelationshipStatus relationshipStatus) {
        setAddress(address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.job = job;
        this.almaMatter = almaMatter;
        this.relationshipStatus = relationshipStatus;
    }

    public User(DocumentSnapshot usrDocSnapshot) {
        this.firstName = usrDocSnapshot.getString(values.KEY_FIRST_NAME);
        this.lastName = usrDocSnapshot.getString(values.KEY_LAST_NAME);
        this.email = usrDocSnapshot.getString(values.KEY_EMAIL);
        this.username = usrDocSnapshot.getString(values.KEY_USER_NAME);
        this.job = usrDocSnapshot.getString(values.KEY_JOB);
        this.almaMatter = usrDocSnapshot.getString(values.KEY_ALMA_MATTER);
        this.setAddress((HashMap) usrDocSnapshot.get(values.KEY_ADDRESS));
       // this.relationshipStatus = RelationShipStatusFactory(Objects.requireNonNull(usrDocSnapshot.getString(values.KEY_RELATIONSHIP_STATUS)));
        setDateOfBirth((HashMap) Objects.requireNonNull(usrDocSnapshot.get(values.KEY_DOB)));
    }

    private void setAddress(HashMap addressMap) {
        this.setAddress(new Address(Objects.requireNonNull(addressMap.get("country")).toString(),
                                    Objects.requireNonNull(addressMap.get("city")).toString()));
    }

    private static RelationshipStatus RelationShipStatusFactory(@NonNull String relation) {
        String transformed = relation.trim().toLowerCase(Locale.ROOT);

        switch (transformed) {
            case values.KEY_SINGLE: return RelationshipStatus.SINGLE;
            case values.KEY_MARRIED: return RelationshipStatus.MARRIED;
            case values.KEY_DIVORCED: return RelationshipStatus.DIVORCED;
            case values.KEY_WIDOW: return RelationshipStatus.WIDOW;
            case values.KEY_IAR: return RelationshipStatus.IAR;
        }

        Log.v(TAG, "Unknown status: " + relation);
        return null;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Calendar getDateOfBirth() { return this.dateOfBirth; }

    private void setDateOfBirth(@NonNull HashMap calendarMap) {
        String timestamp = Objects.requireNonNull(calendarMap.get("time")).toString();

        int seconds = extractSeconds(timestamp);
        int nanoseconds = extractNanoSecond(timestamp);

        // Create a new Date object using the seconds and nanoseconds
        Date date = new Date(seconds * 1000L + nanoseconds / 1000000L);

        // Convert the Date object to a Calendar object
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        setDateOfBirth(calendar);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + "\n" +
                this.username + "\n" +
                this.email + "\n" +
                this.dateOfBirth + "\n" +
                this.job + "\n";
    }

    private int extractSeconds(String timestamp) {
        int start = timestamp.indexOf("=");
        int end = timestamp.indexOf(",");
        return Integer.parseInt(timestamp.substring(start + 1, end));
    }

    private int extractNanoSecond(String timestamp) {
        int start = timestamp.lastIndexOf("=");
        int end = timestamp.indexOf(")");
        return Integer.parseInt(timestamp.substring(start + 1, end));
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
}