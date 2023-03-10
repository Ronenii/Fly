package com.GroupC.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signUpButton = findViewById(R.id.button_sign_up);
        Button signInButton = findViewById(R.id.button_sign_in);
    }


    public void onSignUpClick (View view)
    {
        Intent moveToSignUp = new Intent(this,SignUpActivity.class);
        startActivity(moveToSignUp);
    }

    public void onSignInClick (View view)
    {
        Intent moveToSignIn = new Intent(this,SignInActivity.class);
        startActivity(moveToSignIn);
    }


}