package com.GroupC.fly.ActivityLogic;

/**
 * Use for CONSTANTS that are used in the various java classes.
 */
public interface values {
    /** BACKGROUND ANIMATION **/
    static final int BG_ANIMATION_ENTER_FADE_DURATION = 10;
    static final int BG_ANIMATION_EXIT_FADE_DURATION = 5000;


    /** ERROR MESSAGES **/
    static final String INVALID_PASSWORD = "Password invalid";
    static final String INVALID_EMAIL = "Email invalid";
    static final String PASSWORDS_UNMATCHED = "Passwords do not match";


    /** KEYS **/
    static final String KEY_DOB = "dob";
    static final String COLLECTION_PATH = "Users";
    static final String KEY_FIRST_NAME = "firstName";
    static final String KEY_LAST_NAME = "lastName";
    static final String KEY_USER_NAME = "username";
    static final String KEY_ADDRESS = "address";
    static final String KEY_EMAIL = "email";
    static final String KEY_JOB = "job";
    static final String KEY_ALMA_MATTER = "alma-matter";
    static final String KEY_PASSWORD = "password";

    static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    static final String LENGTH_SCOPE_REGEX = "^[a-zA-Z\\d!@#$%^&*()_+=[\\]{}|;':\",./<>?`~]]{8,20}$";  // Checks if the string is from 8 to 20 characters.
    static final String NUMBERS_REGEX = ".*\\d.*";          // Checks if the string contains a number.
    static final String UPPER_CASE_REGEX = ".*[A-Z].*";     // Checks if the string contains an upper case letter.
    static final String LOWER_CASE_REGEX = ".*[a-z].*";     // Checks if the string contains a lower case letter.
    static final String SPECIAL_CHARS_REGEX = ".*[!@#$%^&*()_+=[\\]{}|;':\",./<>?`~]].*"; // Checks if the string contains a special character.

    static final String KEY_SINGLE = "single";
    static final String KEY_MARRIED = "married";
    static final String KEY_DIVORCED = "divorced";
    static final String KEY_WIDOW = "widow";
    static final String KEY_IAR = "iar";
    static final String KEY_RELATIONSHIP_STATUS = "rsStatus";

    static final String DOB_PARSING_FORMAT = "dd/MM/yyyy"; // For date of birth parsing.

    /** MORE VALUES **/
    // Add more here..,
}
