package com.GroupC.fly.ActivityLogic;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.GroupC.fly.R;
import com.GroupC.fly.Services.AuthService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

public class SignUpActivity extends AppCompatActivity implements ToolTipsManager.TipListener {
    String email = null, password = null, passwordRepeat = null;

    TextInputEditText etEmail, etPassword, etPasswordRepeat;
    ViewGroup layout;
    ToolTipsManager toolTipsManager;
    ImageView ivQuestionMark;
    Button nextButton;
    AuthService mAuth;

    boolean ivEightDigitsCheckBool, ivOneUpperCaseCheckBool, ivOneLowerCaseCheckBool,
            ivOneNumberCheckBool, ivOneSpecialCharCheckBool, showHideIconToggleOn = false;


    /** METHODS **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        GlobalFuncs globalFuncs = new GlobalFuncs(this, R.id.sign_up_page);
        globalFuncs.startBackgroundAnimation();

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPasswordRepeat = findViewById(R.id.et_password_repeat);
        nextButton = findViewById(R.id.btn_next);
        layout = findViewById(R.id.sign_up_page);
        ivQuestionMark = findViewById(R.id.ic_question_mark);

        //Initialize tooltip manager
        toolTipsManager = new ToolTipsManager(this);
        mAuth = new AuthService(this);

        onShowPasswordToggle();
        onPasswordChange();
    }

    /**
     *  Responsible for what happens when next is clicked in the first sign up screen.
     *  Credentials are verified and afterwards the email is drawn into the person object that represents the user.
     *  Afterward we are redirected to the next screen.
     */
    public void onNextClickPartOne(View view)
    {
        if (getCredentials()) {
            if (verifyEmail() && verifyPassword()) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(completeListener -> {
                    Intent moveToNext = new Intent(getApplicationContext(), SignUpActivity2.class);
                    moveToNext.putExtra(values.KEY_EMAIL, email);       // Send email field to the next activity.
                    moveToNext.putExtra(values.KEY_PASSWORD, password); // Send password field to the next activity.
                    startActivity(moveToNext);
                }).addOnFailureListener(failureListener -> {
                    Log.v(TAG, failureListener.getMessage());
                });

            }
        }
    }


    /**
     * Gets credentials from text input boxes.
     */
    private boolean getCredentials() {
        email = etEmail.getText().toString();
        if (isValidPassword()) {
            password = etPassword.getText().toString();
            passwordRepeat = etPasswordRepeat.getText().toString();
            return true;
        } else {
            displayErrorToast(values.INVALID_PASSWORD);
            return false;
        }
    }


    /**
     * Validates that the password is up to the correct requirements.
     * Returns true if the password is of valid length and contains at least character 3 out of 4 requirements.
     */
    private boolean isValidPassword() {
        // A regex that checks if a string containing the specified chars is between 8-20 chars long.


        boolean isValidLength;
        int passwordValidityCounter = 0;
        String password = etPassword.getText().toString();
        isValidLength = ivEightDigitsCheckBool = checkPasswordRegex(password, values.LENGTH_SCOPE_REGEX);
        ivOneNumberCheckBool = checkPasswordRegex(password, values.NUMBERS_REGEX);             // Check for a number in password.
        ivOneUpperCaseCheckBool = checkPasswordRegex(password, values.UPPER_CASE_REGEX);       // Check for an upper case letter in password.
        ivOneLowerCaseCheckBool = checkPasswordRegex(password, values.LOWER_CASE_REGEX);       // Check for a lower case letter in password.
        ivOneSpecialCharCheckBool = checkPasswordRegex(password, values.SPECIAL_CHARS_REGEX);  // Check for a special character letter in password.

        for(boolean bool : new boolean[] {ivOneNumberCheckBool, ivOneUpperCaseCheckBool, ivOneLowerCaseCheckBool ,ivOneSpecialCharCheckBool})
            passwordValidityCounter += bool ? 1 : 0; // Add 1 to counter for each boolean variable that its value is 'true'.

        return isValidLength && passwordValidityCounter >= 3;
    }


    /**
     * Checks if a password given by a String is matching a given regex.
     */
    private boolean checkPasswordRegex(String password, final String regex) {
        return password.matches(regex);
    }


    /**
     * listens for changes in the password and allows for live updates of the checkmarks
     * in the signup screen.
     */
    private void onPasswordChange() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidPassword();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    if (!isValidPassword())
                        displayErrorTooltip();
                }
            }
        });
    }


    /**
     * Verifies the email address.
     */
    private boolean verifyEmail() {
        if (!email.matches(values.EMAIL_PATTERN)) {
            displayErrorToast(values.INVALID_EMAIL);
            return false;
        } else {
            return true;
        }
    }


    /**
     * Verifies the password against the 'passwordRepeat'.
     */
    private boolean verifyPassword() {
        if (!password.equals(passwordRepeat)) {
            displayErrorToast(values.PASSWORDS_UNMATCHED);
            return false;
        } else {
            return true;
        }
    }


    /**
     * Displays an error toast.
     */
    private void displayErrorToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View toast_layout = inflater.inflate(R.layout.credentials_error_toast, (ViewGroup) findViewById(R.id.error_toast_layout));
        TextView displayMessage = toast_layout.findViewById(R.id.toast_message);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        displayMessage.setText(message);
        toast.setView(toast_layout);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, -60);

        toast.show();
    }


    private void displayErrorTooltip()
    {
        toolTipsManager.findAndDismiss(ivQuestionMark);
        ToolTip.Builder builder = new ToolTip.Builder(this,ivQuestionMark
                ,layout,"Password Requirements", ToolTip.POSITION_LEFT_TO);
        builder.setAlign(ToolTip.ALIGN_LEFT);
        builder.setBackgroundColor(R.color.blueBtnColor);
        toolTipsManager.show(builder.build());
    }


    /**
     * This function moves back from the current view to the home screen.
     */
    public void onReturnClick(View view) {
        Intent moveToHome = new Intent(this, StartUpActivity.class);
        startActivity(moveToHome);
    }


    /**
     * This Method creates a listener on the Eye icon on the password field and controls the hide/show behavior.
     */
    public void onShowPasswordToggle() {

        TextInputLayout ilPassword = findViewById(R.id.textInputLayout2);
        ilPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!showHideIconToggleOn) {
                    showHideIconToggleOn = true;
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPasswordRepeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    showHideIconToggleOn = false;
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPasswordRepeat.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }


    /**
    * Sends controlling booleans to the Password REQ popup activity so that the
    * check marks will appear at the corresponding positions.
    */
    public void onQuestionMarkClick(View view)
    {
        toolTipsManager.dismissAll();
        Intent intent = new Intent(getApplicationContext(), PasswordRequirementPopup.class);
        intent.putExtra("LengthCheck", ivEightDigitsCheckBool);
        intent.putExtra("NumberCheck", ivOneNumberCheckBool);
        intent.putExtra("LowerCheck",ivOneLowerCaseCheckBool);
        intent.putExtra("UpperCheck", ivOneUpperCaseCheckBool);
        intent.putExtra("SpecialCheck", ivOneSpecialCharCheckBool);
        startActivity(intent);
    }


    @Override
    public void onTipDismissed(View view, int anchorViewId, boolean byUser) {

    }
}

