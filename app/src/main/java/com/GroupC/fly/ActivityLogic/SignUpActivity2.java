package com.GroupC.fly.ActivityLogic;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.GroupC.fly.R;
import com.GroupC.fly.Services.AuthService;
import com.GroupC.fly.data.Objects.Address;
import com.GroupC.fly.data.Objects.User;
import com.GroupC.fly.data.model.FirebaseModel;
import com.GroupC.fly.data.model.LoggedInUser;

import org.checkerframework.checker.units.qual.A;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

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
        etEducation = findViewById(R.id.et_education);
        etCity = findViewById(R.id.et_city);

        // Makes drop down menu of gender work with spam with the design provided
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
                String date = makeDateString(year, month, day);
                dateOfBirthButton.setText(date);
            }
        };

        //Show today's date when starting dialog
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        //builds the dialog
        datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        //this is retarded but they fucked up so I need to add this
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private String makeDateString(int year, int month, int day) {
        String dateFormat="";

        if (day < 10 && day > 0)
            dateFormat += "0";
        dateFormat += day+"/";

        if(month < 10 && month > 0)
            dateFormat += "0";
        dateFormat += month+"/";

        dateFormat += year;
        return dateFormat;
    }
    
    public void selectDateOfBirth(View view) {
        datePickerDialog.show();
    }


    /**
     * Move back into SignUpActivity
     */
    public void onGoBackClick(View view) {
        Intent moveBack = new Intent(this, SignUpActivity.class);
        startActivity(moveBack);
    }

    /**
     * Checks validity of the necessary fields for the signup process:
     * First name | Last name | Date of birth | Gender
     * @return are all the necessary fields valid.
     */
    private Boolean checkNecessaryFields() {
        return TextUtils.isEmpty(etFirstName.getText().toString()) ||
                TextUtils.isEmpty(etLastName.getText().toString());
        // TODO: add date of birth check, gender check
    }

    /**
     * Displays an error toast.
     * @param message string to display in the error toast.
     */
    private void displayErrorToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View toast_layout = inflater.inflate(R.layout.credentials_error_toast, (ViewGroup) findViewById(R.id.error_toast_layout));
        TextView displayMessage = toast_layout.findViewById(R.id.toast_message);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        displayMessage.setText(message);
        toast.setView(toast_layout);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 420);

        toast.show();
    }

    /**
     *  Checks validity of necessary fields,
     *  if all are valid, creates a user and adds it into the Cloud Database.
     *  Sets the user as LoggedInUser.
     */
    public void onNextClickPartTwo(View view) throws ParseException {
        /*
         Check for necessary fields' validity, if one is not provided, will display an error toast,
         otherwise will create the new user and move to HomePageActivity.
         */
        if(checkNecessaryFields()) {
            displayErrorToast(values.INVALID_NECESSARY_FIELDS);
        }
        else {
            FirebaseModel fbModel = new FirebaseModel(SignUpActivity2.this);    // Create instance of firebase model.
            User newUser = new User();

            // Get user info from sign up fields into a new user variable:
            newUser.setFirstName(etFirstName.getText().toString());
            newUser.setLastName(etLastName.getText().toString());
            newUser.setNickname(etNickname.getText().toString());
            newUser.setJob(etJob.getText().toString());
            newUser.setAddress(new Address("NULL", etCity.getText().toString())); // TODO: add country edittext, create the address with the input.
            newUser.setAlmaMatter(etEducation.getText().toString());
            newUser.setEmail(getIntent().getStringExtra(values.KEY_EMAIL));
            newUser.setDateOfBirth(dateOfBirthButton.getText().toString());
            newUser.setUsername(newUser.getFirstName() + " " + newUser.getLastName());

            //TODO: set relationship status to newUser

            fbModel.insertUserToDB(newUser); // Add the new user to the Cloud Database.
            HomePageActivity.setLoggedInUser(new LoggedInUser(newUser.getEmail(), newUser.getFirstName(), newUser)); // Set the logged-in user.

            //TODO:
            // 1. add the option to draw users birthday when implemented into the 2nd sign up activity
            // 2. Check validity of data
            // 3. this will redirect to and activity where a user can add a profile picture

            // Send the email and username to homepage after successful signup for display in nav menu
            Intent moveToHome = new Intent(this, HomePageActivity.class);
            moveToHome.putExtra(values.KEY_EMAIL, newUser.getEmail());
            moveToHome.putExtra(values.KEY_USER_NAME, newUser.getUsername());
            startActivity(moveToHome);
        }
    }
}