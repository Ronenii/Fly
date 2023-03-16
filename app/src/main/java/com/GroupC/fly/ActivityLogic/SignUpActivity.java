package com.GroupC.fly.ActivityLogic;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.GroupC.fly.R;

public class SignUpActivity extends AppCompatActivity{
    String email = null;
    String password = null;
    String passwordRepeat = null;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordRepeat;

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
    }

    //Gets credentials from user
    private void getCredentials()
    {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        passwordRepeat = etPasswordRepeat.getText().toString();
    }

    //Checks if the credentials are ok returns true, if not will show error and return false
    private boolean credentialsCheck(String passwordRepeat, String password, String email)
    {  //TODO: finish method, figure out what we want to be the credential requirements
        if(password == null || password.length() < 8) {
            displayErrorToast("Password must contain 8 characters");
            return false;
        }
        else if(!password.equals(passwordRepeat)) {
            displayErrorToast("Passwords are not the same");
            return false;
        }
        return true;
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
    public void onGoBackClick(View view) {
        Intent moveToHome = new Intent(this, MainActivity.class);
        startActivity(moveToHome);
    }

    public void onNextClick(View view) {
        getCredentials();
        if(credentialsCheck(passwordRepeat,password,email)) {
            Intent moveToNext = new Intent(this, SignUpActivity2.class);
            startActivity(moveToNext);
        }
    }
}
