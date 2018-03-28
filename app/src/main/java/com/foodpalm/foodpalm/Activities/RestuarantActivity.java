package com.foodpalm.foodpalm.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.Adapters.ResViewPagerAdapter;
import com.foodpalm.foodpalm.Fragments.DineInTakeAwayFragment;
import com.foodpalm.foodpalm.Fragments.HomeDeliveryFragment;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.TableReservation;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RestuarantActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    TabLayout tabLayout;
    TextView toolbarHeader,userName;
    ViewPager viewPager;
    ResViewPagerAdapter resViewPagerAdapter;
    String uName,userEmail;
    ImageButton cartBtn;
    String latitude,longitude;
    DineInTakeAwayFragment dineInTakeAwayFragment;
    HomeDeliveryFragment homeDeliveryFragment;
    IpClass ipClass = new IpClass();
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarant);

        cartBtn = (ImageButton) findViewById(R.id.cart_btn);
        cartBtn.setVisibility(View.INVISIBLE);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Thin.ttf");


        toolbarHeader = (TextView) findViewById(R.id.toolbar_heading);
        toolbarHeader.setTypeface(typeface);
        toolbarHeader.setText("Restaurants");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        uName = loginData.getString("userName","");
        userEmail = loginData.getString("userEmail","");
        latitude = getIntent().getExtras().getString("latitude");
        longitude = getIntent().getExtras().getString("longitude");


        dineInTakeAwayFragment = new DineInTakeAwayFragment();
        dineInTakeAwayFragment.latitude = latitude;
        dineInTakeAwayFragment.longitude = longitude;

        homeDeliveryFragment = new HomeDeliveryFragment();
        homeDeliveryFragment.latitude = latitude;
        homeDeliveryFragment.longitude = longitude;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.res_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.res_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        tabLayout = (TabLayout)findViewById(R.id.res_tabView);
        viewPager = (ViewPager)findViewById(R.id.res_viewPager);

        resViewPagerAdapter = new ResViewPagerAdapter(getSupportFragmentManager());
        resViewPagerAdapter.addFragments(dineInTakeAwayFragment,"Dine In/Take Away");
        resViewPagerAdapter.addFragments(homeDeliveryFragment,"Home Delivery");

        viewPager.setAdapter(resViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        NavigationView navigationView = (NavigationView) findViewById(R.id.res_nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);
        userName = (TextView) headerView.findViewById(R.id.nav_username);
        userName.setText(uName);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void clearCart(String uEmail){
        Retrofit retrofit3 = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit3.create(APIService.class);

        Call<String> cartClearence = service.ProcessCartClearing(userEmail);

        cartClearence.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.res_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.view_profile) {
            Intent intent = new Intent(getApplicationContext(),UserProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.change_password) {


        } else if (id == R.id.history) {
            Intent intent = new Intent(getApplicationContext(),HistoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.order) {

            Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.table_reserve) {

            Intent i = new Intent(getApplicationContext(),TableReservationsActivity.class);
            startActivity(i);

        } else if (id == R.id.logout) {
            SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loginData.edit();
            editor.putString("userEmail", "");
            editor.putString("userPassword", "");
            editor.putString("userName", "");
            editor.putString("userCity", "");
            editor.apply();
            Intent homePage = new Intent(getApplicationContext(), LoginRegisterActivity.class);
            startActivity(homePage);
            Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.res_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
