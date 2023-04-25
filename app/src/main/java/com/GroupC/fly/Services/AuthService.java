package com.GroupC.fly.Services;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.GroupC.fly.ActivityLogic.HomePageActivity;
import com.GroupC.fly.ActivityLogic.values;
import com.GroupC.fly.data.model.FirebaseModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;
import java.util.Objects;

/*
* This class provides a verification service through an email that is sent to the user
* on registration.
* Also it provides the ability to log in/out a user.
* */
public class AuthService {
    private FirebaseAuth mAuth = null;
    private Context mContext = null;

    /**
     * @param context The context of the activity.
     * */
    public AuthService(Context context) {
        mContext = context;
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * @return Get the current user from FireBaseAuth.
     * */
    public FirebaseUser getCurrentUser() {
        if (mAuth == null) throw new NullPointerException("[FireBaseAuth]: instance is null");
        return mAuth.getCurrentUser();
    }

    /**
     * This function logs out the current user.
     * */
    public void signOut() {
        mAuth.signOut();
    }

    /**
     * This function registers a user to FirestoreAuth service..
     *
     * @param email The users email.
     * @param pwd The users password
     * @return A task with the auto result, it allows registering listeners to act appropriately
     * according to the AuthResult.
     * */
    public Task<AuthResult> createUserWithEmailAndPassword(String email, String pwd) {
        return mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(registerTask -> {
                    if (registerTask.isSuccessful()) {
                        Log.d("[Auth]", "createUserWithEmailAndPassword:success");

                        // send verification link
                        FirebaseUser user = getCurrentUser();
                        user.sendEmailVerification().addOnSuccessListener(emailTaskSuccess -> {
                            Toast.makeText(mContext, "Verification Email has been sent !", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(emailTaskFail -> {
                            Log.d("[Auth]", "verifyEmail:fail (" + emailTaskFail.getMessage() + ")");
                        });

                    } else {
                        Log.d("[Auth]", "createUserWithEmailAndPassword:fail");
                        Toast.makeText(mContext, "User creation failed, please try again !", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    /**
     * This function signs in a user.
     *
     * @param email The users email.
     * @param pwd The users password
     * @return A task with the auto result, it allows registering listeners to act appropriately
     * according to the AuthResult.
     * */
    public Task<AuthResult> signInWithEmailAndPassword(String email, String pwd) {
        return mAuth.signInWithEmailAndPassword(email, pwd);
    }
}
