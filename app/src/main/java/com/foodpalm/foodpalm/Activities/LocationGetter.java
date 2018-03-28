package com.foodpalm.foodpalm.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.GPSTracker;

public class LocationGetter extends AppCompatActivity {

    Button btnShowLocation;

    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_getter);

        btnShowLocation = (Button) findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gps = new GPSTracker(LocationGetter.this);

                if(gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Current Location is \nLatitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),RestuarantActivity.class);
                    i.putExtra("latitude", "" + latitude);
                    i.putExtra("longitude", "" + longitude);
                    startActivity(i);

                } else {
                    gps.showSettingsAlert();
                }
            }
        });
    }
}
