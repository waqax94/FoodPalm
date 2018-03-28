package com.foodpalm.foodpalm.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.Adapters.CartAdapter;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.Cart;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.Order;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CartActitvity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarHeader,restName,ordrType,bill,noItem;
    ListView listView;
    Button checkoutBtn,clearCartBtn;
    ImageButton cartBtn;
    RelativeLayout data;
    String userEmail,oType,userName,orderId;
    Order order;
    IpClass ipClass = new IpClass();
    CartAdapter cartAdapter;
    int sum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_actitvity);

        oType = getIntent().getExtras().getString("orderType");

        toolbarHeader = (TextView) findViewById(R.id.toolbar_heading);
        toolbarHeader.setText("Cart");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        cartBtn = (ImageButton) findViewById(R.id.cart_btn);
        cartBtn.setVisibility(View.GONE);

        data = (RelativeLayout) findViewById(R.id.cart_data);
        restName = (TextView) findViewById(R.id.restarant_name);
        ordrType = (TextView) findViewById(R.id.order_type);
        bill = (TextView) findViewById(R.id.total);
        noItem = (TextView) findViewById(R.id.no_item_text);

        listView = (ListView) findViewById(R.id.cart_item_list);
        checkoutBtn = (Button) findViewById(R.id.checkout_btn);
        clearCartBtn = (Button) findViewById(R.id.clear_cart_btn);

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userEmail = loginData.getString("userEmail", "");
        userName = loginData.getString("userName", "");

        cartAdapter = new CartAdapter(getApplicationContext(),R.layout.cart_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Cart>> cartData = service.ProcessCartItems(userEmail);

        cartData.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Response<List<Cart>> response, Retrofit retrofit) {
                final List<Cart> cartList = response.body();
                if(cartList == null){
                    data.setVisibility(View.GONE);
                    noItem.setVisibility(View.VISIBLE);
                }
                else {
                    noItem.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);
                    restName.setText(cartList.get(0).getRestaurantTitle());
                    ordrType.setText(oType);
                    for (int i = 0 ; i < cartList.size() ; i++){
                        sum += (Integer.parseInt( cartList.get(i).getPrice()) * Integer.parseInt( cartList.get(i).getQuantity()));
                    }
                    bill.setText("" + sum);
                    cartAdapter.addCartItems(cartList);
                    listView.setAdapter(cartAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sum > 0){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ipClass.ipAddress).
                                    addConverterFactory(GsonConverterFactory.create())
                            .build();
                    APIService service = retrofit.create(APIService.class);

                    Call<Order> orderData = service.ProcessOrder(oType,userEmail,userName,bill.getText().toString());

                    orderData.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Response<Order> response, Retrofit retrofit) {
                            if(response.body() != null) {
                                order = response.body();
                                Retrofit retrofit1 = new Retrofit.Builder()
                                        .baseUrl(ipClass.ipAddress).
                                                addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                APIService service = retrofit1.create(APIService.class);

                                Call<String> orderItemData = service.ProcessOrderItems(order.getOrderDate(),order.getOrderTime(),order.getEmail(),order.getRestaurantId(),order.getBranchCode());

                                orderItemData.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                        if(response.body() != null) {
                                            orderId = response.body();
                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            APIService service = retrofit2.create(APIService.class);

                                            Call<String> orderItemDetailsData = service.ProcessOrderItemDetails(order.getOrderDate(),order.getOrderTime(),order.getEmail(),order.getRestaurantId(),order.getBranchCode(),orderId);

                                            orderItemDetailsData.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_SHORT).show();
                                                    Retrofit retrofit3 = new Retrofit.Builder()
                                                            .baseUrl(ipClass.ipAddress).
                                                                    addConverterFactory(GsonConverterFactory.create())
                                                            .build();
                                                    APIService service = retrofit3.create(APIService.class);

                                                    Call<String> cartClearence = service.ProcessCartClearing(order.getEmail());

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
                                                public void onFailure(Throwable t) {
                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

        clearCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
