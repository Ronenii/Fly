package com.GroupC.fly.ActivityLogic;

import android.graphics.drawable.AnimationDrawable;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GroupC.fly.R;

public class GlobalFuncs {
    /**
     * Hides title and action bar on the top of the screen.
     * Receives the current activity.
     */
    public static void hideActionBar(AppCompatActivity activity)
    {
        //These lines hide the title and action bar at the top of the screen
        if(activity.getSupportActionBar().isShowing()) activity.getSupportActionBar().hide();
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Starts the background animation.
     * Receives the current activity.
     */
    public static void startBackgroundAnimation(AppCompatActivity activity)
    {
        //Animation Declaration & Start
        ConstraintLayout sign_up_page2 = activity.findViewById(R.id.sign_up_page2);
        AnimationDrawable animation = (AnimationDrawable) sign_up_page2.getBackground();

        animation.setEnterFadeDuration(values.BG_ANIMATION_ENTER_FADE_DURATION);
        animation.setExitFadeDuration(values.BG_ANIMATION_EXIT_FADE_DURATION);
        animation.start();
    }
}
