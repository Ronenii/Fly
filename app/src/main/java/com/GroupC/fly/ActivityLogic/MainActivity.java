package com.GroupC.fly.ActivityLogic;

import static android.content.ContentValues.TAG;

import com.GroupC.fly.FragmentLogic.FragmentBlogPost;
import com.GroupC.fly.ui.home.SharedViewModel;
import com.GroupC.fly.ui.profile.FragmentProfile;
import com.GroupC.fly.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import com.GroupC.fly.Utils.data.Objects.User;
import com.GroupC.fly.Services.AuthService;
import com.GroupC.fly.Utils.data.model.FirebaseModel;
import com.GroupC.fly.Utils.data.model.LoggedInUser;
import com.GroupC.fly.databinding.ActivityHomePageBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private FirebaseModel firebaseModel;

    private SharedViewModel viewModel;
    private User user;
    private Bundle extras;
    private AppBarConfiguration appBarConfiguration;
    private ActivityHomePageBinding binding;

    private FragmentManager fragManager;
    private AuthService auth;
    private static LoggedInUser loggedInUser;

    private TextView emailTw;
    private TextView usernameTw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        setSupportActionBar(binding.appBarHomePage.toolbar);

        auth = new AuthService(this);
        firebaseModel = new FirebaseModel(this);
        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_profile)
             .setOpenableLayout(drawer)
             .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        extras = getIntent().getExtras();

        if (extras != null) {
            firebaseModel.getUserFromDB(extras.getString(values.KEY_EMAIL))
                    .addOnSuccessListener(this::onUserDataFetchSuccess)
                    .addOnFailureListener(this::onUserDataFetchFail);
        }
        else {
            firebaseModel.getUserFromDB(auth.getCurrentUser().getEmail())
                    .addOnSuccessListener(this::onUserDataFetchSuccess)
                    .addOnFailureListener(this::onUserDataFetchFail);
        }
    }

    private void onUserDataFetchFail(Exception e) {
        Log.v(TAG, e.getMessage());
    }

    private void onUserDataFetchSuccess(DocumentSnapshot documentSnapshot) {
        user = new User(documentSnapshot);
        viewModel.setUser(user);

        emailTw = findViewById(R.id.tw_email);
        usernameTw = findViewById(R.id.tw_username);

        if (extras != null) {
            emailTw.setText(extras.getString(values.KEY_EMAIL));
        } else {
            emailTw.setText(auth.getCurrentUser().getEmail());
        }

        usernameTw.setText(user.getUsername());
    }

    public void onPost(View view) {
        FragmentBlogPost fragment = new FragmentBlogPost();
        fragment.show(getSupportFragmentManager(),"PostDialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    /**
     *  Return to welcome page, logout current user.
     */
    public void onLogoutClick(View view) {
        auth.signOut();

        Intent moveToWelcomeScreen = new Intent(this, StartUpActivity.class);
        startActivity(moveToWelcomeScreen);
    }

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(LoggedInUser _loggedInUser) {
        loggedInUser = _loggedInUser;
    }


}