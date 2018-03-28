package com.foodpalm.foodpalm.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.InputFilterMinMax;
import com.foodpalm.foodpalm.models.IpClass;

import java.io.File;
import java.util.Calendar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class TableRequestActivity extends AppCompatActivity{

    String resId,resTitle,branchCode,branchArea,userName,userEmail;
    Toolbar toolbar;
    TextView toolbarHeader,dateTimeVal;
    ImageButton cartBtn;
    EditText counterVal;
    Button counterInc,counterDec,picker,proceed;
    int min = 1 , max = 200;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal , yearFinal, hourFinal, minuteFinal;
    String date,time;
    IpClass ipClass = new IpClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_request);

        toolbarHeader = (TextView) findViewById(R.id.toolbar_heading);
        toolbarHeader.setText("Table Reservation");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        cartBtn = (ImageButton) findViewById(R.id.cart_btn);
        cartBtn.setVisibility(View.GONE);

        resId = getIntent().getExtras().getString("restaurantId");
        resTitle = getIntent().getExtras().getString("restaurantTitle");
        branchCode = getIntent().getExtras().getString("branchCode");
        branchArea = getIntent().getExtras().getString("branchArea");

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userEmail = loginData.getString("userEmail", "");
        userName = loginData.getString("userName", "");

        counterVal = (EditText) findViewById(R.id.person_value);
        counterVal.setFilters(new InputFilter[]{ new InputFilterMinMax(min, max)});

        counterInc = (Button) findViewById(R.id.inc_person_counter);
        counterDec = (Button) findViewById(R.id.dec_person_counter);

        counterInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(counterVal.getText().toString());
                if(value < max){
                    value++;
                }
                counterVal.setText(""+value);
            }
        });

        counterDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(counterVal.getText().toString());
                if(value > min){
                    value--;
                }
                counterVal.setText(""+value);
            }
        });

        picker = (Button) findViewById(R.id.pick_datetime);
        dateTimeVal = (TextView) findViewById(R.id.date_time);

        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TableRequestActivity.this,R.style.DialogTheme,new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yearFinal = year;
                        monthFinal = month + 1;
                        dayFinal = dayOfMonth;

                        Calendar c = Calendar.getInstance();
                        hour = c.get(Calendar.HOUR);
                        minute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(TableRequestActivity.this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hourFinal = hourOfDay;
                                minuteFinal = minute;

                                dateTimeVal.setText("Date: "+dayFinal+"/"+monthFinal+"/"+yearFinal+"\n"+
                                        "Time: "+hourFinal+":"+minuteFinal);
                            }
                        },hour,minute,DateFormat.is24HourFormat(getApplicationContext()));
                        timePickerDialog.show();
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        proceed = (Button) findViewById(R.id.proceed_reserve);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateTimeVal.getText().toString().equals("") || counterVal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"First choose date/time and number of persons!",Toast.LENGTH_SHORT).show();
                }
                else {
                    date = yearFinal + "-" + monthFinal + "-" + dayFinal;
                    time = hourFinal + ":" + minuteFinal + ":00";
                    String numOfPerson = counterVal.getText().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ipClass.ipAddress).
                                    addConverterFactory(GsonConverterFactory.create())
                            .build();
                    APIService service = retrofit.create(APIService.class);

                    Call<String> reserveData = service.ProcessTableReservation(resId,branchCode,resTitle,branchArea,numOfPerson,time,date,userName,userEmail);

                    reserveData.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response, Retrofit retrofit) {
                            Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),TableReservationsActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

    }

}
