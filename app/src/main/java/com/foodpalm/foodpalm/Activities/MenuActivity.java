package com.foodpalm.foodpalm.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.Adapters.MenuAdapter;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.MenuItemDetails;
import com.foodpalm.foodpalm.models.TableReservation;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String resId,resTitle,branchCode,branchArea,catId,subCatId,subCatName,orderType,uName;
    IpClass ipClass = new IpClass();
    Toolbar toolbar;
    DrawerLayout drawer;
    ListView listView;
    TextView errorText;
    TextView toolbarHeader,userName;
    MenuAdapter menuAdapter;
    ImageButton cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        resId = getIntent().getExtras().getString("restaurantId");
        resTitle = getIntent().getExtras().getString("restaurantTitle");
        branchCode = getIntent().getExtras().getString("branchCode");
        branchArea = getIntent().getExtras().getString("branchArea");
        catId = getIntent().getExtras().getString("categoryId");
        subCatId = getIntent().getExtras().getString("subcategoryId");
        subCatName = getIntent().getExtras().getString("subcategoryName");
        orderType = getIntent().getExtras().getString("orderType");

        cartBtn = (ImageButton) findViewById(R.id.cart_btn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActitvity.class);
                intent.putExtra("orderType", "" + orderType);
                startActivity(intent);
            }
        });

        toolbarHeader = (TextView) findViewById(R.id.toolbar_heading);
        toolbarHeader.setText(subCatName);

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        uName = loginData.getString("userName","");


        errorText = (TextView) findViewById(R.id.no_item_text);

        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.menu_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.menu_nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);
        userName = (TextView) headerView.findViewById(R.id.nav_username);
        userName.setText(uName);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.menu_list);
        menuAdapter = new MenuAdapter(getApplicationContext(),R.layout.menu_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<MenuItemDetails>> menuItemData = service.ProcessMenu(subCatId,catId,resId);

        menuItemData.enqueue(new Callback<List<MenuItemDetails>>() {
            @Override
            public void onResponse(Response<List<MenuItemDetails>> response, Retrofit retrofit) {
                if(response.body() != null){
                    final List<MenuItemDetails> menuItems = response.body();
                    menuAdapter.resTitle = resTitle;
                    menuAdapter.branchCode = branchCode;
                    menuAdapter.branchArea = branchArea;
                    menuAdapter.orderType = orderType;
                    menuAdapter.addMenuList(menuItems);
                    listView.setAdapter(menuAdapter);
                    errorText.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
                else {
                    listView.setVisibility(View.GONE);
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.menu_drawer_layout);
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
            Intent homePage = new Intent(getApplicationContext(),LoginRegisterActivity.class);
            startActivity(homePage);
            Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.menu_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
