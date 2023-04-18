package com.GroupC.fly.ActivityLogic;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;

import com.GroupC.fly.R;
import com.GroupC.fly.data.Objects.Address;
import com.GroupC.fly.data.model.FirebaseModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private CheckBox btnShowPassword;
    private Dialog signInDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalFuncs globalFuncs = new GlobalFuncs(this, R.id.welcome_page_page);
    }

    public void onSignUpClick (View view)
    {
        Intent moveToSignUp = new Intent(this,SignUpActivity.class);
        startActivity(moveToSignUp);
    }

    public void onSignInClick(View view)
    {
        // test
        // sets a new dialog on this activity and shows the sign in activity
        signInDialog = new Dialog(this);
        signInDialog.setContentView(R.layout.activity_sign_in);

        // For storing the values from input - later on....
        View popup = getLayoutInflater().inflate(R.layout.activity_sign_in, null);
        // EditText password_sign_in = popup.findViewById(R.id.et_password_si);
        EditText email_sign_in = popup.findViewById(R.id.et_email_si);
        Button sign_in_btn = popup.findViewById(R.id.btn_sign_in);

        //Makes round edges of popup more refined
        signInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        signInDialog.show();

       sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: define sign in button
            }
        });
    }

    /**
     * This function registers a listener for the check box button that shows/hides the passwords.
     * @param view The view that was set by 'onCreate'
     */
    public void onShowPassword(View view) {
        btnShowPassword = signInDialog.findViewById(R.id.btn_show_password);
        btnShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            EditText tmpPassword = signInDialog.findViewById(R.id.et_password_si);
            if (isChecked) {
                tmpPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                tmpPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }


    public void onForgotPasswordClick(View view) {
        // TODO: go to forgot password activity.
    }

    public void goToHomePage(View view){
        Intent moveToHomePage= new Intent(this, HomePageActivity.class);
        startActivity(moveToHomePage);
    }
}