package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.GroupC.fly.R;
import com.GroupC.fly.data.Objects.Address;
import com.GroupC.fly.data.Objects.User;
import com.GroupC.fly.data.model.FirebaseModel;
import com.GroupC.fly.data.model.LoggedInUser;

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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        globalFuncs.startBackgroundAnimation();

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etNickname = findViewById(R.id.et_nickname);
        etJob = findViewById(R.id.et_job);
        etEducation = findViewById(R.id.et_city);
        etCity = findViewById(R.id.et_education);

        // Makes drop down menu of gender work with spam with the design provided
        autoCompleteTV = findViewById(R.id.tv_auto_complete);
        gender_drop_down= getResources().getStringArray(R.array.genders_options);
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.drop_down,gender_drop_down);
        autoCompleteTV.setAdapter(adapter);
    }

    public void onGoBackClick(View view) {
        Intent moveBack = new Intent(this, SignUpActivity.class);
        startActivity(moveBack);
    }

    /**
     *  Adds the user into the Cloud Database, Sets the LoggedInUser.
     */
    public void onNextClickPartTwo(View view)
    {
        FirebaseModel fbModel = new FirebaseModel(SignUpActivity2.this);    // Create instance of firebase model.
        User newUser = new User();

        // Set user info:
        newUser.setFirstName(etFirstName.getText().toString());
        newUser.setLastName(etLastName.getText().toString());
        newUser.setNickname(etNickname.getText().toString());
        newUser.setJob(etJob.getText().toString());
        newUser.setAddress(new Address(etCity.getText().toString()));
        newUser.setAlmaMatter(etEducation.getText().toString());
        newUser.setEmail(getIntent().getStringExtra("email")); // Get the email field from SignUpActivity.
        //TODO: set age to newUser
        //TODO: set relationship status to newUser

        fbModel.insertUserToDB(newUser); // Add the new user to the Cloud Database.
        HomePageActivity.setLoggedInUser(new LoggedInUser(newUser.getEmail(), newUser.getFirstName(), newUser)); // Set the logged-in user.

        //TODO:
        // 1. add the option to draw users birthday when implemented into the 2nd sign up activity
        // 2. Check validity of data
        // 3. this will redirect to and activity where a user can add a profile picture

        Intent moveToHome = new Intent(this, HomePageActivity.class);
        startActivity(moveToHome);
    }
}