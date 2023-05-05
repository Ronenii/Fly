package com.GroupC.fly.ActivityLogic;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.GroupC.fly.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    ImageView ivProfilePicture;
    TextView tvJob, tvLocation, tvBdate, tvAge, tvName;

    /** METHODS **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfilePicture = findViewById(R.id.iv_profile_picture_profile_page);
        tvJob = findViewById(R.id.tv_job_content);
        tvLocation = findViewById(R.id.tv_location_content);
        tvBdate = findViewById(R.id.tv_bdate_content);
        tvAge = findViewById(R.id.tv_age_content);
        tvName = findViewById(R.id.tv_profile_name);

        // Initialize the db instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }
}
