package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.GroupC.fly.HomePageActivity;
import com.GroupC.fly.R;
import com.GroupC.fly.data.Objects.Address;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity2 extends AppCompatActivity {

    String[] gender_drop_down;

    EditText etFirstName, etLastName, etNickname, etJob, etEducation, etCity;
    AutoCompleteTextView autoCompleteTV;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        GlobalFuncs globalFuncs = new GlobalFuncs(this, R.id.sign_up_page2);

        globalFuncs.hideActionBar();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        globalFuncs.startBackgroundAnimation();

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etNickname = findViewById(R.id.et_nickname);
        etJob = findViewById(R.id.et_job);
        etEducation = findViewById(R.id.et_city);
        etCity = findViewById(R.id.et_education);

        //Makes drop down menu of gender work with spam with the design provided
        autoCompleteTV = findViewById(R.id.tv_auto_complete);
        gender_drop_down= getResources().getStringArray(R.array.genders_options);
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.drop_down,gender_drop_down);
        autoCompleteTV.setAdapter(adapter);

    }

    public void onGoBackClick(View view) {
        Intent moveBack = new Intent(this, SignUpActivity.class);
        startActivity(moveBack);
    }

    public void onNextClickPartTwo(View view)
    {
        Address userCity = new Address(etCity.getText().toString());
        SignUpActivity.user.setFirstName(etFirstName.getText().toString());
        SignUpActivity.user.setLastName(etLastName.getText().toString());
        SignUpActivity.user.setNickname(etNickname.getText().toString());
        SignUpActivity.user.setJob(etJob.getText().toString());
        SignUpActivity.user.setAddress(userCity);
        SignUpActivity.user.setAlmaMatter(etEducation.getText().toString());
        //TODO: add the option to draw users birthday when implemented into the 2nd sign up activity
        //TODO: Check validity of data
        //TODO: this will redirect to and activity where a user can add a profile picture
        //TODO: Upload this data to firebase


        Intent moveToHome = new Intent(this, HomePageActivity.class);
        startActivity(moveToHome);
    }
}