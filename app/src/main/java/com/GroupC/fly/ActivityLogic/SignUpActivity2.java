package com.GroupC.fly.ActivityLogic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.GroupC.fly.R;
import com.GroupC.fly.data.Objects.Address;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.drop_down,gender_drop_down);
        autoCompleteTV.setAdapter(adapter);

    }

    public void onGoBackClick(View view) {
        Intent moveBack = new Intent(this, SignUpActivity.class);
        startActivity(moveBack);
    }

    public void insertUserToDB(String name, String username, Address address, String email, String job, String almaMatter, int age){
        // Access a Cloud Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("username", username);
        user.put("address", address);
        user.put("email", email);
        user.put("job", job);
        user.put("alma matter", almaMatter);
        user.put("age", age);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }

}