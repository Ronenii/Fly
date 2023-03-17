package com.GroupC.fly.ActivityLogic;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GroupC.fly.R;

/**
 * Create an instance to use the functions.
 * Receives the activity used and the ID of the XML page.
 */
public class GlobalFuncs {
    Activity activity; // Current activity using the functions.
    final Integer ID; // ID of the XML page for that activity.

    public GlobalFuncs(Activity _activity, Integer _id)
    {
        activity = _activity;
        ID = _id;
    }

    /**
     * Hides title and action bar on the top of the screen.
     */
    public void hideActionBar()
    {
        AppCompatActivity act = (AppCompatActivity) activity;
        if(act.getSupportActionBar().isShowing()) act.getSupportActionBar().hide();
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * Starts the background animation.
     * To change the duration and speed go to 'values.java'.
     */
    public void startBackgroundAnimation()
    {
        ConstraintLayout sign_up_page2 = activity.findViewById(ID);
        AnimationDrawable animation = (AnimationDrawable) sign_up_page2.getBackground();

        animation.setEnterFadeDuration(values.BG_ANIMATION_ENTER_FADE_DURATION);
        animation.setExitFadeDuration(values.BG_ANIMATION_EXIT_FADE_DURATION);
        animation.start();
    }
}
