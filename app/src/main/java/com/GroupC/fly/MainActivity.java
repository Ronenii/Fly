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

        Button signUpButton = findViewById(R.id.entranceButton);
    }




    public void onSignUpClick (View view)
    {
        Intent  moveToSignUp = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(moveToSignUp);
    }
}