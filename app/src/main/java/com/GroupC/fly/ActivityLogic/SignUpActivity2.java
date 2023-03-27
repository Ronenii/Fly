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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import com.GroupC.fly.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUpActivity2 extends AppCompatActivity {

    String[] genderDropDown;
    AutoCompleteTextView autoCompleteTV;
    ArrayAdapter<String> adapter;

    private DatePickerDialog datePickerDialog;

    private AppCompatButton dateOfBirthButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        GlobalFuncs globalFuncs = new GlobalFuncs(this, R.id.sign_up_page2);
        globalFuncs.startBackgroundAnimation();

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

}