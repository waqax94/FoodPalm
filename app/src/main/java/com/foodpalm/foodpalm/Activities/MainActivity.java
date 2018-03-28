package com.foodpalm.foodpalm.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.GPSTracker;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int progress = 0;
    Handler h = new Handler();
    String email;
    String psw;
    Intent intent;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.splashProgress);

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0 ; i < 100 ; i++){
                    progress++;
                    h.post(new Runnable() {
                        @Override
                        public void run() {

                            progressBar.setProgress(progress);
                            if(progress == 50){
                                SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                email = loginData.getString("userEmail", "");
                                psw = loginData.getString("userPassword","");
                                if(email.equals("") || psw.equals("")){
                                    intent = new Intent(getApplicationContext(),LoginRegisterActivity.class);
                                }
                                else {
                                    intent = new Intent(getApplicationContext(),RestuarantActivity.class);
                                }
                            }
                            if(progress ==  progressBar.getMax()){
                                progressBar.setVisibility(View.GONE);
                                gps = new GPSTracker(MainActivity.this);

                                /*if(gps.canGetLocation()) {
                                    double latitude = gps.getLatitude();
                                    double longitude = gps.getLongitude();

                                    Toast.makeText(getApplicationContext(), "Current Location is \nLatitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_LONG).show();
                                    intent.putExtra("latitude", "" + latitude);
                                    intent.putExtra("longitude", "" + longitude);

                                } else {
                                    gps.showSettingsAlert();
                                }*/


                                if(!gps.canGetLocation()){
                                    while(!gps.enableCheck()) {
                                        while (gps.canGetLocation()) {
                                            gps = new GPSTracker(MainActivity.this);
                                        }
                                    }
                                }
                                double latitude = gps.getLatitude();
                                double longitude = gps.getLongitude();

                                Toast.makeText(getApplicationContext(), "Current Location is \nLatitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_LONG).show();
                                intent.putExtra("latitude", "" + latitude);
                                intent.putExtra("longitude", "" + longitude);

                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e){

                    }
                }
            }
        }).start();
    }

}
