package com.example.housingapp.Activity;


import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.housingapp.R;

/** Note that here we are inheriting ListActivity class instead of Activity class **/
public class MemberActivity extends ListActivity implements OnClickListener{
 LinearLayout llBack1;
 TextView tvtittle;
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Setting a custom layout for the list activity */
        setContentView(R.layout.activity_member);

        /** Reference to the button of the layout main.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        /** Defining a click event listener for the button "Add" */
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);

        /** Setting the adapter to the ListView */
        setListAdapter(adapter);
        setToolBar();
    }
    private void setToolBar(){
        llBack1 = findViewById(R.id.llBack1);
        llBack1.setOnClickListener(this);
        tvtittle = findViewById(R.id.tvTitle);
        tvtittle.setText("Add Member");
    }

    @Override
    public void onClick(View v) {
        if(v==llBack1){
            onBackPressed();
        }
    }
}
