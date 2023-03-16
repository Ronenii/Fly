package com.GroupC.fly.ActivityLogic;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
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
    Button submitButton;

    static private final String SHA_TYPE = "SHA-256";

    // A regex to match an email address.
    static private final String EMAIL_PATH = "^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    /* A regex to match password of length 8 - 20, must contain:
        Password must contain at least one digit [0-9].
        Password must contain at least one lowercase Latin character [a-z].
        Password must contain at least one uppercase Latin character [A-Z].
        Password must contain at least one special character like ! @ # & ( ).
        Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    static private final String password_PAT = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //These lines hide the title and action bar at the top of the screen
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animation Declaration & Start
        ConstraintLayout sign_up_page = findViewById(R.id.sign_up_page);
        AnimationDrawable animation = (AnimationDrawable) sign_up_page.getBackground();

        animation.setEnterFadeDuration(10);
        animation.setExitFadeDuration(5000);
        animation.start();

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordRepeat = (EditText) findViewById(R.id.et_password_repeat);
        submitButton = (Button) findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getCredentials())
                {
                    verifyEmail();
                    verifyPassword();
                    //TODO: Save/Create new user and redirect to profile creation
                }
            }
        });
    }

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
        }

        return null;
    }

    //Gets credentials from user
    private boolean getCredentials() {
        if (isValidPassword(etPassword.toString()) && isValidPassword(etPasswordRepeat.toString())) {
            password = digestPassword(etPassword.toString());
            passwordRepeat = digestPassword(etPasswordRepeat.toString());
            return true;
        } else {
            displayErrorToast("Password invalid");
            return false;
        }
    }

    private boolean isValidPassword(String password) {
        return password.matches(password_PAT);
    }

    private void verifyEmail() {
        if (!etEmail.getText().toString().matches(EMAIL_PATH)) {
            displayErrorToast("Email is not valid");
        } else {

        }
    }

    private void verifyPassword() {
        if (!password.equals(passwordRepeat)) {
            // prompt an unmatching password error.
            displayErrorToast("Passwords does not match");
        } else {
            // Move to another screen etc.
        }
    }


    //Shows Toast error message because of bad credentials input
    private void displayErrorToast(String message)
    {
        LayoutInflater inflater = getLayoutInflater();
        View toast_layout = inflater.inflate(R.layout.credentials_error_toast,(ViewGroup) findViewById(R.id.error_toast_layout));
        TextView displayMessage = toast_layout.findViewById(R.id.toast_message);
        displayMessage.setText(message);
        Toast toast = Toast.makeText(this,message, Toast.LENGTH_SHORT);
        toast.setView(toast_layout);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER, 0, 180);
        toast.show();

    }
    public void onReturnClick(View view) {
        Intent moveToHome = new Intent(this,MainActivity.class);
        startActivity(moveToHome);
    }
}
