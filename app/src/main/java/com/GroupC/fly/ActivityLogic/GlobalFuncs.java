package com.GroupC.fly.ActivityLogic;

import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class GlobalFuncs {

    public static void hideActionBar(AppCompatActivity activity)
    {
        //These lines hide the title and action bar at the top of the screen
        activity.getSupportActionBar().hide();
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
