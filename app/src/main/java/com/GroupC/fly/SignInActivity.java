package com.GroupC.fly;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SignInActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //These lines hide the title and action bar at the top of the screen
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animation Declaration & Start
        ConstraintLayout sign_in_page = findViewById(R.id.sign_in_page);
        AnimationDrawable animation = (AnimationDrawable) sign_in_page.getBackground();

        animation.setEnterFadeDuration(10);
        animation.setExitFadeDuration(5000);
        animation.start();
    }

    public void onReturnClick(View view) {
        Intent moveToHome = new Intent(this,MainActivity.class);
        startActivity(moveToHome);
    }

    public void onForgotPasswordClick(View view) {
        // TODO: add forgot password activity
    }
}
