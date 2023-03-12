package com.GroupC.fly.ActivityLogic;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.GroupC.fly.R;

public class SignUpActivity extends AppCompatActivity{
    String email, password;
    EditText etEmail;
    EditText etPassword;
    EditText etPassword_check;
    Button submitButton;

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

        submitButton = (Button) findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmailAddress();
                getPassword();

            }
        });
    }

    //Getting email address from the EditText.
    private void getEmailAddress(){
        etEmail = (EditText) findViewById(R.id.et_email);
        email = etEmail.getText().toString();
    }
    //Getting password address from the EditText.
    private void getPassword(){
        etPassword = (EditText) findViewById(R.id.et_password);
        password = etPassword.getText().toString();
    }

    public void onReturnClick(View view) {
        Intent moveToHome = new Intent(this,MainActivity.class);
        startActivity(moveToHome);
    }
}
