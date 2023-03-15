package com.GroupC.fly.ActivityLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.GroupC.fly.R;

public class MainActivity extends AppCompatActivity {

   /* private AlertDialog.Builder sign_in_dialog_window;
    private AlertDialog dialog;
    private EditText password_sign_in;
    private EditText email_sign_in;
    private Button sign_in_btn;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //These lines hide the title and action bar at the top of the screen
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onSignUpClick (View view)
    {
        Intent moveToSignUp = new Intent(this,SignUpActivity.class);
        startActivity(moveToSignUp);
    }

    public void onSignInClick(View view)
    {

        //test
        //sets a new dialog on this activity and shows the sign in activity
        Dialog sign_in_dialog = new Dialog(this);
        sign_in_dialog.setContentView(R.layout.activity_sign_in);

        //For storing the values from input - later on....
        View popup = getLayoutInflater().inflate(R.layout.activity_sign_in, null);
        EditText password_sign_in = popup.findViewById(R.id.et_password_si);
        EditText email_sign_in = popup.findViewById(R.id.et_email_si);
        Button sign_in_btn = popup.findViewById(R.id.btn_sign_in);

        //Makes round edges of popup more refined
        sign_in_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        sign_in_dialog.show();

       sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define sign in button here!
            }
        });
    }


}