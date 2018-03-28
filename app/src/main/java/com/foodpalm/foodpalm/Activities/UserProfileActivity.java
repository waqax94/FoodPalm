package com.foodpalm.foodpalm.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.foodpalm.foodpalm.R;

public class UserProfileActivity extends AppCompatActivity {

    TextView name,email,city;
    String userName,userEmail,userCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userName = loginData.getString("userName","");
        userEmail = loginData.getString("userEmail","");
        userCity = loginData.getString("userCity","");


        name = (TextView) findViewById(R.id.profile_name);
        email = (TextView) findViewById(R.id.profile_email);
        city = (TextView) findViewById(R.id.profile_city);

        name.setText(userName);
        email.setText(userEmail);
        city.setText(userCity);
    }

}
