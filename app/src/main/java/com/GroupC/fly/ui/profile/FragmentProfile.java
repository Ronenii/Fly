package com.GroupC.fly.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.GroupC.fly.R;
import com.GroupC.fly.Services.AuthService;
import com.GroupC.fly.Utils.data.Objects.User;
import com.GroupC.fly.Utils.data.model.FirebaseModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private AuthService authService;
    private String mParam2;

    private FirebaseModel db;

    ImageView ivProfilePicture;
    TextView tvJob, tvLocation, tvBdate, tvAge, tvName;


    /**
     * METHODS
     **/

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ivProfilePicture = rootView.findViewById(R.id.iv_profile_picture_profile_page);
        tvJob = rootView.findViewById(R.id.tv_job_content);
        tvLocation = rootView.findViewById(R.id.tv_location_content);
        tvBdate = rootView.findViewById(R.id.tv_bdate_content);
        tvAge = rootView.findViewById(R.id.tv_age_content);
        tvName = rootView.findViewById(R.id.tv_profile_name);

        // Initialize the db instanc
        // db = new FirebaseModel(getContext());
       // authService = new AuthService(getContext());

        //getUserData(authService.getCurrentUser().getEmail());
        return rootView;
    }

    public void getUserData(String userEmail)
    {
        Task<DocumentSnapshot> userFromDB =  db.getUserFromDB(userEmail);
        User profileDisplayUser = new User(userFromDB.getResult());
        setProfileData(profileDisplayUser);
    }

    public void setProfileData(User user)
    {
        String userFullName = user.getFirstName() + " " + user.getLastName();
        tvAge.setText(user.getUserAge());
        tvJob.setText(user.getJob());
        tvName.setText(userFullName);
        tvBdate.setText(user.getDateOfBirth().toString());
        tvLocation.setText(user.getAddress().toString());
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
}