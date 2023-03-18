package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.GroupC.fly.R;

public class SignUpActivity extends AppCompatActivity {
    String email = null;
    String password = null;
    String passwordRepeat = null;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordRepeat;
    Button nextButton;
    CheckBox btnShowPassword;

    ImageView ivEightDigitsCheck;
    ImageView ivOneUpperCaseCheck;
    ImageView ivOneLowerCaseCheck;
    ImageView ivOneNumberCheck;
    ImageView ivOneSpecialCharCheck;

    private static int sPasswordValidityCounter = 0;


    static private final String SHA_TYPE = "SHA-256";

    // A regex to match an email address.
    static private final String EMAIL_PATH = "^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    /* A regex to match password of length 8 - 20, must contain:
        Password must contain at least one digit [0-9].
        Password must contain at least one lowercase/uppercase Latin character [a-z]/[A-Z].
        Password must contain at least one special character like ! @ # & % * ? $ ^.
        Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    static private final String password_PAT = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&^])[A-Za-z\\d@$!%*#?&^]{8,20}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        GlobalFuncs globalFuncs = new GlobalFuncs(this, R.id.sign_up_page);

        globalFuncs.hideActionBar(); // Hide annoying action bar.
        globalFuncs.startBackgroundAnimation(); // Start Background animation.

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordRepeat = (EditText) findViewById(R.id.et_password_repeat);
        nextButton = (Button) findViewById(R.id.btn_next);
        btnShowPassword = findViewById(R.id.btn_show_password);

        ivEightDigitsCheck = findViewById(R.id.iv_8_digits_check);
        ivOneUpperCaseCheck = findViewById(R.id.iv_one_uppercase_check);
        ivOneLowerCaseCheck = findViewById(R.id.iv_one_lowercase_check);
        ivOneNumberCheck = findViewById(R.id.iv_one_number_check);
        ivOneSpecialCharCheck = findViewById(R.id.iv_one_special_char_check);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getCredentials()) {
                    if (verifyEmail() && verifyPassword()) {
                        //TODO: Save/Create new user

                        // Move to the profile information activity.
                        Intent moveToNext = new Intent(getApplicationContext(), SignUpActivity2.class);
                        startActivity(moveToNext);
                    }
                }
            }
        });
        onPasswordChange();
    }

    // This function creates a SHA-256 hash of the password, will be later stored in the db.
    private String digestPassword(@NonNull String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_TYPE);
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = md.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // handle exception
            Log.v("[digestPasswordError]:", e.toString());
        }

        return null;
    }

    //Gets credentials from user
    private boolean getCredentials() {
        if (isValidPassword()) {
            password = digestPassword(etPassword.getText().toString());
            passwordRepeat = digestPassword(etPasswordRepeat.getText().toString());
            return true;
        } else {
            displayErrorToast(values.INVALID_PASSWORD);
            return false;
        }
    }

    /**
     * // This function verifies whether the password contains all the valid chars and of a valid length.
     * private boolean isValidPassword(String password) {
     * return password.matches(password_PAT);
     * }
     **/

    private boolean isValidPassword() {
        boolean isValidLength = false, hasNumber = false, hasUpperCase = false, hasLowerCase = false, hasSpecialChar = false;
        String password = etPassword.getText().toString();
        isValidLength = passwordIsCorrectLength(password);
        hasNumber = passwordHasNumbers(password);
        hasUpperCase = passwordHasUpperCase(password);
        hasLowerCase = passwordHasLowerCase(password);
        hasSpecialChar = passwordHasSpecialChar(password);

        return isValidLength && sPasswordValidityCounter >= 3;
    }

    private boolean passwordIsCorrectLength(String i_password) {
        final String PASSWORD_LENGTH_SCOPE = "^[a-zA-Z\\d!@#$%^&*()_+=[\\]{}|;':\",./<>?`~]]{8,20}$";
        if (i_password.matches(PASSWORD_LENGTH_SCOPE)) {
            ivEightDigitsCheck.setImageResource(R.drawable.check);
            return true;
        } else {
            ivEightDigitsCheck.setImageDrawable(null);
            return false;

        }
    }

    private boolean passwordHasNumbers(String i_password) {
        final String NUMBERS = ".*\\d.*";
        if (i_password.matches(NUMBERS)) {
            ivOneNumberCheck.setImageResource(R.drawable.check);
            sPasswordValidityCounter++;
            return true;
        } else {
            ivOneNumberCheck.setImageDrawable(null);
            if (sPasswordValidityCounter > 0) sPasswordValidityCounter--;
            return false;

        }
    }

    private boolean passwordHasUpperCase(String i_password) {
        final String UPPER_CASE = ".*[A-Z].*";
        if (i_password.matches(UPPER_CASE)) {
            ivOneUpperCaseCheck.setImageResource(R.drawable.check);
            sPasswordValidityCounter++;
            return true;
        } else {
            ivOneUpperCaseCheck.setImageDrawable(null);
            if (sPasswordValidityCounter > 0) sPasswordValidityCounter--;
            return false;

        }
    }

    private boolean passwordHasLowerCase(String i_password) {
        final String LOWER_CASE = ".*[a-z].*";
        if (i_password.matches(LOWER_CASE)) {
            ivOneLowerCaseCheck.setImageResource(R.drawable.check);
            sPasswordValidityCounter++;
            return true;
        } else {
            ivOneLowerCaseCheck.setImageDrawable(null);
            if (sPasswordValidityCounter > 0) sPasswordValidityCounter--;
            return false;

        }
    }

    private boolean passwordHasSpecialChar(String i_password) {
        final String SPECIAL_CHARS = ".*[!@#$%^&*()_+=[\\]{}|;':\",./<>?`~]].*";
        if (i_password.matches(SPECIAL_CHARS)) {
            ivOneSpecialCharCheck.setImageResource(R.drawable.check);
            sPasswordValidityCounter++;
            return true;
        } else {
            ivOneSpecialCharCheck.setImageDrawable(null);
            if (sPasswordValidityCounter > 0) sPasswordValidityCounter--;
            return false;

        }
    }

    private void onPasswordChange() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidPassword();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // This function verifies the email address.
    private boolean verifyEmail() {
        if (!etEmail.getText().toString().matches(EMAIL_PATH)) {
            displayErrorToast(values.INVALID_EMAIL);
            return false;
        } else {
            return true;
        }
    }

    // This function verifies the password against the 'passwordRepeat'.
    private boolean verifyPassword() {
        if (!password.equals(passwordRepeat)) {
            displayErrorToast(values.PASSWORDS_UNMATCHED);
            return false;
        } else {
            return true;
        }
    }

    // This function displays an error toast.
    private void displayErrorToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View toast_layout = inflater.inflate(R.layout.credentials_error_toast, (ViewGroup) findViewById(R.id.error_toast_layout));
        TextView displayMessage = toast_layout.findViewById(R.id.toast_message);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        displayMessage.setText(message);
        toast.setView(toast_layout);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 180);

        toast.show();
    }

    /**
     * This function moves back from the current view to the home screen.
     *
     * @param view The view that was set by 'onCreate'
     */
    public void onReturnClick(View view) {
        Intent moveToHome = new Intent(this, MainActivity.class);
        startActivity(moveToHome);
    }

    /**
     * This function registers a listener for the check box button that shows/hides the passwords.
     *
     * @param view The view that was set by 'onCreate'
     */

    //TODO: Currentyl from some reason, this only workds on the 2nd click of the checkbox. We need to fix this.
    public void onShowPassword(View view) {

        btnShowPassword.setOnCheckedChangeListener((tempView, isChecked) -> {
            if (isChecked) {
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                etPasswordRepeat.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                etPasswordRepeat.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }


}

