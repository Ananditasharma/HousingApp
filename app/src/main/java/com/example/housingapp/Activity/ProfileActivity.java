package com.example.housingapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.housingapp.Database.LoginTable;
import com.example.housingapp.Model.LoginViewModel;
import com.example.housingapp.R;

import java.util.List;
import java.util.Objects;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


  CardView btnSave;
  LinearLayout llBack1;
  String name,email,phone,password,number;
  TextView tvtittle;
  EditText et_name,et_flatnumber,et_numb,et_email,et_password;
    private static final String TAG = "MyProfileCreator";
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);
        findViews();
        setToolBar();

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getGetAllData().observe(this, new Observer <List <LoginTable>>() {
            @Override
            public void onChanged(@Nullable List<LoginTable> data) {

                try {
                    et_email.setText((Objects.requireNonNull(data).get(0).getEmail()));
                    et_password.setText((Objects.requireNonNull(data.get(0).getPassword())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private void findViews() {
        et_name=findViewById(R.id.et_name);
        et_flatnumber = findViewById(R.id.et_flatnumber);
        et_numb = findViewById(R.id.et_numb);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }
 private void setToolBar(){
     llBack1 = findViewById(R.id.llBack1);
     llBack1.setOnClickListener(this);
     tvtittle = findViewById(R.id.tvTitle);
     tvtittle.setText("My Profile");
 }


    @Override
    public void onClick(View v) {


        if(!et_name.getText().toString().isEmpty()){

            updateName();
        }
        if(!et_flatnumber.getText().toString().isEmpty()){
            updateFlatNumber();
        }
        if(!et_numb.getText().toString().isEmpty()){
            updateContact();
        }
        if(!et_email.getText().toString().isEmpty()){
            updateEmail();
        }
        if(!et_password.getText().toString().isEmpty()){
            updatePassword();
        }
        if(v==btnSave) {
            if (isValidate()) {
                LoginTable data = new LoginTable();
                //if data is inserted in the room database these fields will be already filled
                data.setEmail(email);
                data.setPassword(password);
                loginViewModel.insert(data);
                Toast.makeText(ProfileActivity.this, "Your details are saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfileActivity.this, FeatutresActivity.class));
            }
        }
        if(v==llBack1){
            startActivity(new Intent(ProfileActivity.this, FeatutresActivity.class));
        }


    }

    private void updateName() {
       name= et_name.getText().toString();
    }
    private void updateContact() {
        phone =et_numb.getText().toString();
    }
    private void updateEmail() {
       email= et_email.getText().toString();
    }
    private void updateFlatNumber() {
        number =et_numb.getText().toString();
    }
    private void updatePassword() {
       password= et_password.getText().toString();
    }

        private boolean isValidate() {
            if (TextUtils.isEmpty(et_email.getText())) {
                Toast.makeText(ProfileActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (TextUtils.isEmpty(et_password.getText())) {
                Toast.makeText(ProfileActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(et_numb.getText())) {
                Toast.makeText(ProfileActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(et_name.getText())) {
                Toast.makeText(ProfileActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(et_flatnumber.getText())) {
                Toast.makeText(ProfileActivity.this, "Please Enter Flat Number", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
}