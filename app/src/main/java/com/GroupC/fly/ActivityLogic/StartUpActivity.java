package com.GroupC.fly.ActivityLogic;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.GroupC.fly.R;
import com.GroupC.fly.Services.AuthService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class StartUpActivity extends AppCompatActivity {
    private CheckBox btnShowPassword;
    private Dialog signInDialog;
    private AuthService mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalFuncs globalFuncs = new GlobalFuncs(this, R.id.welcome_page_page);
        mAuth = new AuthService(this);
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
       // View popup = getLayoutInflater().inflate(R.layout.activity_sign_in, null);

        //Makes round edges of popup more refined
        signInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        signInDialog.show();
    }

    public void onSignInCompleteClick(View view) {
        EditText password_sign_in = signInDialog.findViewById(R.id.et_password_si);
        EditText email_sign_in = signInDialog.findViewById(R.id.et_email_si);

        mAuth.signInWithEmailAndPassword
        (
                email_sign_in.getText().toString(),
                password_sign_in.getText().toString()
        )
        .addOnCompleteListener(this, completeListener -> {
            Intent moveToHome = new Intent(this, HomePageActivity.class);
            startActivity(moveToHome);
        })
        .addOnFailureListener(failureListener -> {
            Log.v(TAG, failureListener.getMessage());
        });
    }

    /**
     * This function registers a listener for the check box button that shows/hides the passwords.
     * @param view The view that was set by 'onCreate'
     */
    public void onShowPassword(View view) {
        btnShowPassword = signInDialog.findViewById(R.id.btn_show_password);
        EditText tmpPassword = signInDialog.findViewById(R.id.et_password_si);

        if(btnShowPassword.isChecked()) {
            tmpPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            tmpPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else {
            tmpPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            tmpPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }


    public void onForgotPasswordClick(View view) {
        // TODO: go to forgot password activity.
    }

    public void goToHomePage(View view){
        Intent moveToHomePage= new Intent(this, HomePageActivity.class);
        startActivity(moveToHomePage);
    }
}