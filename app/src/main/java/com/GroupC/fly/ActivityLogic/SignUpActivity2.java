package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.GroupC.fly.R;

public class SignUpActivity2 extends AppCompatActivity {

    String[] gender_drop_down;
    AutoCompleteTextView autoCompleteTV;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        //These lines hide the title and action bar at the top of the screen
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animation Declaration & Start
        ConstraintLayout sign_up_page2 = findViewById(R.id.sign_up_page2);
        AnimationDrawable animation = (AnimationDrawable) sign_up_page2.getBackground();

        animation.setEnterFadeDuration(10);
        animation.setExitFadeDuration(5000);
        animation.start();


        //Makes drop down menu of gender work with spam with the design provided
        autoCompleteTV = findViewById(R.id.tv_auto_complete);
        gender_drop_down=getResources().getStringArray(R.array.genders_options);
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.drop_down,gender_drop_down);
        autoCompleteTV.setAdapter(adapter);

    }

    public void onGoBackClick(View view) {
        Intent moveBack = new Intent(this, SignUpActivity.class);
        startActivity(moveBack);
    }
}