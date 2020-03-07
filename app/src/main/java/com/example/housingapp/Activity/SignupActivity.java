package com.example.housingapp.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.housingapp.Database.LoginTable;
import com.example.housingapp.Model.LoginViewModel;
import com.example.housingapp.R;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    EditText etEmail;
    TextView forgot,tv_pass_hint,tv_email_hint;
    EditText etPassword;
    CardView btnsignup;
    LinearLayout ll_signUp;
    ProgressBar pb_loader;
    RelativeLayout rl_loginpass,rl_login;
    private LoginViewModel loginViewModel;
    private String email, password;
    private static String TAG = "SignupActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 1.5 seconds
        animationDrawable.setEnterFadeDuration(1500);

        // setting exit fade animation duration to 1 seconds
        animationDrawable.setExitFadeDuration(1000);
        findViews();
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getGetAllData().observe(this, new Observer <List <LoginTable>>() {
            @Override
            public void onChanged(@Nullable List<LoginTable> data) {

                try {
//                    etEmail.setText((Objects.requireNonNull(data).get(0).getEmail()));
//                    etPassword.setText((Objects.requireNonNull(data.get(0).getPassword())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    /***
     * This method register all views
     *
     */
    private void findViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        rl_loginpass = findViewById(R.id.rl_loginpass);
        rl_login = findViewById(R.id.rl_login);
        btnsignup = findViewById(R.id.btnRegister);
        tv_email_hint=findViewById(R.id.tv_email_hint);
        tv_pass_hint = findViewById(R.id.tv_pass_hint);
        ll_signUp = findViewById(R.id.ll_signUp);
        btnsignup.setOnClickListener(this);
        ll_signUp.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
    /***
     *
     * @param v
     * @param position
     * it handle the focus of the underline view of Edit text
     */
    private void setFocus(View v, int position) {
        if (position == 1) {
            v.setPadding(0, 0, 0, 0);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(2)));
        } else {
            v.setPadding(0, dpToPx(1), 0, 0);
            v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(1)));
        }
    }

    /**
     * conver
     *
     * @param dp
     * @return
     */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                if (isValidate()) {
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();
                    LoginTable data = new LoginTable();
                    //if data is inserted in the room database these fields will be already filled
                    data.setEmail(email);
                    data.setPassword(password);
                    loginViewModel.insert(data);
                    pb_loader = findViewById(R.id.pb_loader);
                    startActivity(new Intent(SignupActivity.this, FeatutresActivity.class));

                }
                break;
            case R.id.ll_signUp:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));

                break;
        }
    }

    /**
     * form validation
     *
     * @return
     */
    private boolean isValidate() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            Toast.makeText(SignupActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(SignupActivity.this, "Please Enter a valid Email", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(SignupActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * email validation
     *
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}