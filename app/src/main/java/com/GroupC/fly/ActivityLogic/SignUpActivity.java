package com.GroupC.fly.ActivityLogic;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.Window;

import com.google.firebase.annotations.concurrent.Background;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kotlin.Result;

import com.GroupC.fly.R;

public class SignUpActivity extends AppCompatActivity{
    String email = null;
    String password = null;
    String passwordRepeat = null;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordRepeat;
    Button nextButton;
    CheckBox btnShowPassword;

    static private final String SHA_TYPE = "SHA-256";

    // A regex to match an email address.
    static private final String EMAIL_PATH = "^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    /*  A regex to match password of length 8 - 20, must contain:
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

        //These lines hide the title and action bar at the top of the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animation Declaration & Start
        ConstraintLayout sign_up_page = findViewById(R.id.sign_up_page);
        AnimationDrawable animation = (AnimationDrawable) sign_up_page.getBackground();

        animation.setEnterFadeDuration(10);
        animation.setExitFadeDuration(5000);
        animation.start();

        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordRepeat = (EditText) findViewById(R.id.et_password_repeat);
        nextButton = (Button) findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getCredentials())
                {
                    if(verifyPassword()) {
                        //TODO: Save/Create new user and redirect to profile creation
                        //the following is a temporary message to make sure our credentials check is ok
                        displayErrorToast("Succeeded");
                        Intent moveToNext = new Intent(getApplicationContext(), SignUpActivity2.class);
                        startActivity(moveToNext);
                    }
                }
            }
        });
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
        etEmail = findViewById(R.id.et_email);

        if (!verifyEmail()) {
            displayErrorToast("Invalid email !");
            return false;
        }
        if (!isValidPassword(etPassword.getText().toString()) || !isValidPassword(etPasswordRepeat.getText().toString())) {
            displayErrorToast("Passwords are invalid !");
            return false;
        }

        password = digestPassword(etPassword.getText().toString());
        passwordRepeat = digestPassword(etPasswordRepeat.getText().toString());

        if (!verifyPassword()) {
            displayErrorToast("Passwords do not match !");
            return false;
        }

        return true;
    }

    // This function verifies whether the password contains all the valid chars and of a valid length.
    private boolean isValidPassword(String password) {
        return password.matches(password_PAT);
    }

    // This function verifies the email address.
    private boolean verifyEmail() {
        return etEmail.getText().toString().matches(EMAIL_PATH);
    }

    // This function verifies the password against the 'passwordRepeat'.
    private boolean verifyPassword() {
        return password.equals(passwordRepeat);
    }

    // This function displays an error toast.
    private void displayErrorToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View toast_layout = inflater.inflate(R.layout.credentials_error_toast,(ViewGroup) findViewById(R.id.error_toast_layout));
        TextView displayMessage = toast_layout.findViewById(R.id.toast_message);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        displayMessage.setText(message);
        toast.setView(toast_layout);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER, 0, 180);

        toast.show();
    }

    /**
     * This function moves back from the current view to the home screen.
     * @param view The view that was set by 'onCreate'
     */
    public void onReturnClick(View view) {
        Intent moveToHome = new Intent(this, MainActivity.class);
        startActivity(moveToHome);
    }

    /**
     * This function registers a listener for the check box button that shows/hides the passwords.
     * @param view The view that was set by 'onCreate'
     */
    public void onShowPassword(View view) {
        btnShowPassword = findViewById(R.id.btn_show_password);
        btnShowPassword.setOnCheckedChangeListener((tempView, isChecked) -> {
            if (isChecked) {
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etPasswordRepeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etPasswordRepeat.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }
}

