package com.foodpalm.foodpalm.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.Adapters.HistoryAdapter;
import com.foodpalm.foodpalm.Adapters.OrderAdapter;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.History;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.OrderView;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HistoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ImageButton cartBtn;
    TextView toolbarHeader,noText;
    String userEmail;
    HistoryAdapter historyAdapter;
    IpClass ipClass = new IpClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbarHeader = (TextView) findViewById(R.id.toolbar_heading);
        toolbarHeader.setText("History");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        cartBtn = (ImageButton) findViewById(R.id.cart_btn);
        cartBtn.setVisibility(View.GONE);

        noText = (TextView) findViewById(R.id.no_history_text);

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userEmail = loginData.getString("userEmail", "");

        listView = (ListView) findViewById(R.id.history_list);
        historyAdapter = new HistoryAdapter(getApplicationContext(),R.layout.history_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<History>> historyData = service.ProcessHistory(userEmail);

        historyData.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Response<List<History>> response, Retrofit retrofit) {
                final List<History> histories = response.body();
                if(histories == null){
                    noText.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
                else {
                    noText.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    historyAdapter.addHistories(histories);
                    listView.setAdapter(historyAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
            }
        });

    }

}
