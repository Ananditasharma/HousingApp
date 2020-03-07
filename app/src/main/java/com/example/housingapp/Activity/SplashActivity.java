package com.example.housingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.housingapp.R;


public class SplashActivity extends AppCompatActivity {
    private Handler splashHandler;
    private static int SPLASH_TIME = 2000;
    private String TAG = "SplashActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashHandler = new Handler();
        splashHandler.postDelayed(splashRun, SPLASH_TIME);
    }

    private Runnable splashRun = new Runnable() {
        @Override
        public void run() {

            Intent mySuperIntent = new Intent(SplashActivity.this, LoginActivity.class);
            mySuperIntent.putExtra("FROM", 1); // autologin
            startActivity(mySuperIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();


        }


    };
}