package com.example.housingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.housingapp.R;
import com.example.housingapp.R.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HelpActivity extends AppCompatActivity  implements View.OnClickListener {
LinearLayout llBack1;
TextView tvtittle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_help);
        MyListData[] myListData = new MyListData[] {
                new MyListData("Director", R.drawable.girl,"anandita@gmail.com","70603645892","Anandita"),
                new MyListData("Electrician", R.drawable.person,"harish@gmail.com","874745734","Harish"),
                new MyListData("Security Guard", R.drawable.person,"ram@gmail.com","665567678","Ram"),
                new MyListData("Police Station", R.drawable.person,"singham@gmail.com","6646534264674","Singham"),

        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        HelpDeskAdapter adapter = new HelpDeskAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        setToolBar();
    }
    private void setToolBar(){
        llBack1 = findViewById(R.id.llBack1);
        llBack1.setOnClickListener(this);
        tvtittle = findViewById(R.id.tvTitle);
        tvtittle.setText("Help Desk");
    }

    @Override
    public void onClick(View v) {
        if(v==llBack1){
            startActivity(new Intent(HelpActivity.this, FeatutresActivity.class));
        }
        }

}