package com.GroupC.fly.ActivityLogic;

import com.GroupC.fly.FragmentLogic.FragmentBlogPost;
import com.GroupC.fly.R;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.GroupC.fly.Services.AuthService;
import com.GroupC.fly.data.model.LoggedInUser;
import com.GroupC.fly.databinding.ActivityHomePageBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private AppBarConfiguration appBarConfiguration;
    private ActivityHomePageBinding binding;

    private FragmentManager fragManager;
    private AuthService mAuth;
    private static LoggedInUser loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomePage.toolbar);

        binding.appBarHomePage.fab.setOnClickListener(this::onPost);

        mAuth = new AuthService(this);

        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = new AppBarConfiguration.Builder(
             R.id.nav_home, R.id.fragmentBlogPost)
             .setOpenableLayout(drawer)
             .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
        mAuth.signOut();

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