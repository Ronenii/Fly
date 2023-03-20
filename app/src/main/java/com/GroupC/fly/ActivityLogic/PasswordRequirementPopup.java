package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.GroupC.fly.R;

public class PasswordRequirementPopup extends Activity {

    ImageView ivEightDigitsCheck;
    ImageView ivOneUpperCaseCheck;
    ImageView ivOneLowerCaseCheck;
    ImageView ivOneNumberCheck;
    ImageView ivOneSpecialCharCheck;

    boolean ivEightDigitsCheckBool;
    boolean ivOneUpperCaseCheckBool;
    boolean ivOneLowerCaseCheckBool;
    boolean ivOneNumberCheckBool;
    boolean ivOneSpecialCharCheckBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_requirement_popup);

        ivEightDigitsCheck = findViewById(R.id.iv_8_digits_check);
        ivOneUpperCaseCheck = findViewById(R.id.iv_one_uppercase_check);
        ivOneLowerCaseCheck = findViewById(R.id.iv_one_lowercase_check);
        ivOneNumberCheck = findViewById(R.id.iv_one_number_check);
        ivOneSpecialCharCheck = findViewById(R.id.iv_one_special_char_check);

        checkTheRequirements();

        //Run the popup, can change values here to decide where it will be on screen
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(width,(int) (height * 0.5));
        getWindow().setGravity(Gravity.BOTTOM);

    }

    /**
     * Uses the sent booleans to determine where a check mark should be placed
     */
    public void checkTheRequirements()
    {
        Intent i = getIntent();
        ivEightDigitsCheckBool = i.getBooleanExtra("LengthCheck",false);
        ivOneNumberCheckBool = i.getBooleanExtra("NumberCheck",false);
        ivOneLowerCaseCheckBool = i.getBooleanExtra("LowerCheck",false);
        ivOneUpperCaseCheckBool = i.getBooleanExtra("UpperCheck",false);
        ivOneSpecialCharCheckBool = i.getBooleanExtra("SpecialCheck", false);

        if(ivEightDigitsCheckBool)
            ivEightDigitsCheck.setImageResource(R.drawable.check);
        else
            ivEightDigitsCheck.setImageDrawable(null);

        if(ivOneNumberCheckBool)
            ivOneNumberCheck.setImageResource(R.drawable.check);
        else
            ivOneNumberCheck.setImageDrawable(null);

        if(ivOneLowerCaseCheckBool)
            ivOneLowerCaseCheck.setImageResource(R.drawable.check);
        else
            ivOneLowerCaseCheck.setImageDrawable(null);

        if(ivOneUpperCaseCheckBool)
            ivOneUpperCaseCheck.setImageResource(R.drawable.check);
        else
           ivOneUpperCaseCheck.setImageDrawable(null);

        if(ivOneSpecialCharCheckBool)
            ivOneSpecialCharCheck.setImageResource(R.drawable.check);
        else
            ivOneSpecialCharCheck.setImageDrawable(null);

    }
}