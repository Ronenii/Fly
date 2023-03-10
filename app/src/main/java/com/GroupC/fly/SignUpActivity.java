package com.GroupC.fly;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.annotations.concurrent.Background;

public class SignUpActivity extends AppCompatActivity{
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
    }
}
