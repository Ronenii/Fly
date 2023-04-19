package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.GroupC.fly.R;
import com.GroupC.fly.data.Objects.Address;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUpActivity2 extends AppCompatActivity {

    String[] genderDropDown;

    EditText etFirstName, etLastName, etNickname, etJob, etEducation, etCity;
    AutoCompleteTextView autoCompleteTV;
    ArrayAdapter<String> adapter;

    private DatePickerDialog datePickerDialog;

    private AppCompatButton dateOfBirthButton;

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

        //Makes drop down menu of gender work with spam with the design provided
        autoCompleteTV = findViewById(R.id.tv_auto_complete);
        genderDropDown = getResources().getStringArray(R.array.genders_options);
        adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.drop_down, genderDropDown);
        autoCompleteTV.setAdapter(adapter);

        dateOfBirthButton = findViewById(R.id.btn_dob);
        initDatePicker();

    }

    /**
     * Creates date picker logic
     */
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(year,month,day);
                dateOfBirthButton.setText(date);
            }
        };

        //Show today's date when starting dialog
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        //builds the dialog
        datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme,
                dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        //this is retarded but they fucked up so I need to add this
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    //TODO: Idan's mom as well change the below method to something build in, there must be something
    private String makeDateString(int year,int month,int day)
    {
        String dateFormat="";

        if (day<10 && day > 0)
            dateFormat += "0";
        dateFormat += day+"/";

        if(month<10 && month > 0)
            dateFormat += "0";
        dateFormat += month+"/";

        dateFormat += year;
        return dateFormat;
    }
    public void selectDateOfBirth(View view) {
        datePickerDialog.show();
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