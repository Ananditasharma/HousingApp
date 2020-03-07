package com.example.housingapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.housingapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CallendarActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llBack1;
    TextView tvtittle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callendar);
        setToolBar();
    }
    public void AddCalendarEvent(View view) {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", calendarEvent.getTimeInMillis());
        i.putExtra("allDay", true);
        i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        i.putExtra("title", "Calendar Event");
        startActivity(i);
    }
    private void setToolBar(){
        llBack1 = findViewById(R.id.llBack1);
        llBack1.setOnClickListener(this);
        tvtittle = findViewById(R.id.tvTitle);
        tvtittle.setText("Add your Events");
    }
    @Override
    public void onClick(View v) {
        if(v== llBack1){
            onBackPressed();
        }
    }

    public static class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

        RelativeLayout rl_feedback;
        EditText tilte;
        TextInputEditText contentOfFeedback;
        CardView btnSave;
        String TAG = "FeedBackActivity";
        LinearLayout llBack1;
        TextView tvtittle;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_feedback);
            tilte = findViewById(R.id.titleOfFeedback);
            contentOfFeedback = findViewById(R.id.content);
            rl_feedback = findViewById(R.id.rl_feedback);
            btnSave = findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FeedbackActivity.this, FeatutresActivity.class));
                }
            });
        setToolBar();


        }
        private void setToolBar(){
            llBack1 = findViewById(R.id.llBack1);
            llBack1.setOnClickListener(this);
            tvtittle = findViewById(R.id.tvTitle);
            tvtittle.setText("Feed Back");
        }

        /**
         * To get different type of feedback types available
         */


        /**
         * Function to upload the feedback
         */

      //hiding keyboard on touch
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                    INPUT_METHOD_SERVICE);
            if(imm.isAcceptingText()) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            if(v== llBack1){
                onBackPressed();
            }
        }
    }
}
