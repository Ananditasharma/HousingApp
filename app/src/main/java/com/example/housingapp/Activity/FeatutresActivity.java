package com.example.housingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.housingapp.Adapter.CustomGridRecyclerView;
import com.example.housingapp.Database.LoginTable;
import com.example.housingapp.Interface.GetIdAndPositionOfClickedAdapter;
import com.example.housingapp.Model.LoginViewModel;
import com.example.housingapp.R;
import com.example.housingapp.Adapter.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FeatutresActivity extends AppCompatActivity implements View.OnClickListener {
    CustomGridRecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RelativeLayout btnLogout;
    FloatingActionButton fab;
    private LoginViewModel loginViewModel;
    ArrayList<String> arrayList = new ArrayList <>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);
        fab = findViewById(R.id.fab);
        btnLogout = findViewById(R.id.btnlogout);
        btnLogout.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        populateData();
        initAdapter();
        runAnimationAgain();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runAnimationAgain();
            }
        });

    }

    private void populateData() {

        for (int i = 0; i <=5; i++) {

            if(i==0){
                arrayList.add("EVENTS");
            }
            if(i==1){
                arrayList.add("FEEDBACK");
            } if(i==2){
                arrayList.add("HELPDESK");
            }
            if(i==3){
                arrayList.add(" RULES");
            }
            if(i==4){
                arrayList.add("MEMBERS");
            }
            if(i==5){
                arrayList.add("PROFILE");
            }
        }

    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewAdapter = new RecyclerViewAdapter(arrayList, new GetIdAndPositionOfClickedAdapter() {
          @Override
         public void getIdPosition(String id, int position) {
             if(position==5){
                 startActivity(new Intent(FeatutresActivity.this, ProfileActivity.class));
             }
             if(position==0){
                 startActivity(new Intent(FeatutresActivity.this, CallendarActivity.class));
             }
             if(position==1){
                 startActivity(new Intent(FeatutresActivity.this, CallendarActivity.FeedbackActivity.class));
             }
              if(position==2){
                  startActivity(new Intent(FeatutresActivity.this, HelpActivity.class));
              }
              if(position==3){
                  startActivity(new Intent(FeatutresActivity.this, RulesDisplayActivity.class));
              }
              if(position==4){
                  startActivity(new Intent(FeatutresActivity.this, MemberActivity.class));
              }
         }
     });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void runAnimationAgain() {


        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.gridlayout_animation_from_bottom);
        recyclerView.setLayoutAnimation(controller);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

    @Override
    public void onClick(View v) {
        if(v==btnLogout){
                startActivity(new Intent(FeatutresActivity.this, LoginActivity.class));

        }
    }
}
