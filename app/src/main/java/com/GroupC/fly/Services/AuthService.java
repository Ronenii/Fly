package com.GroupC.fly.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
* This class provides a verification service through an email that is sent to the user
* on registration.
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

    public void signOut() {
        mAuth.signOut();
    }

    /**
     *
     * */
    public void createUserWithEmailAndPassword(String email, String pwd) {
        mAuth.createUserWithEmailAndPassword(email, pwd)
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

    public void signInWithEmailAndPassword(String email, String pwd) {
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(signInTask -> {
                   if (signInTask.isSuccessful()) {
                       Log.d("[Auth]", "signInWithEmailAndPassword:success");
                       Toast.makeText(mContext, "Signed in successfully !", Toast.LENGTH_SHORT).show();
                   } else {
                       Log.d("[Auth]", "signInWithEmailAndPassword:fail");
                       Toast.makeText(mContext, "Failed to sign in !", Toast.LENGTH_SHORT).show();
                   }
                }
        );
    }
}
