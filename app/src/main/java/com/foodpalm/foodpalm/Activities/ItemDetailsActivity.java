package com.foodpalm.foodpalm.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.InputFilterMinMax;
import com.foodpalm.foodpalm.models.IpClass;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ItemDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    IpClass ipClass = new IpClass();
    private ImageView itemImg;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    String userName,userEmail,resId,resTitle,branchCode,branchArea,catId,subCatId,itemId,itemImgSource,itemName,itemPrice,itemComboPrice,itemComboCheck,itemDescription,combo,orderType,userCity;
    TextView priceValue,itemDescp;
    EditText counterVal;
    int min = 1 , max = 20;
    Button counterInc,counterDec,addToCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        resId = getIntent().getExtras().getString("restaurantId");
        resTitle = getIntent().getExtras().getString("restaurantTitle");
        branchCode = getIntent().getExtras().getString("branchCode");
        branchArea = getIntent().getExtras().getString("branchArea");
        orderType = getIntent().getExtras().getString("orderType");
        catId = getIntent().getExtras().getString("categoryId");
        subCatId = getIntent().getExtras().getString("subcategoryId");
        itemId = getIntent().getExtras().getString("itemId");
        itemImgSource = getIntent().getExtras().getString("itemImg");
        itemName = getIntent().getExtras().getString("itemName");
        itemPrice = getIntent().getExtras().getString("itemPrice");
        itemComboPrice = getIntent().getExtras().getString("itemComboPrice");
        itemComboCheck = getIntent().getExtras().getString("itemComboCheck");
        itemDescription = getIntent().getExtras().getString("itemDescription");
        combo = getIntent().getExtras().getString("combo");

        toolbar = (Toolbar) findViewById(R.id.item_detail_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userName = loginData.getString("userName", "");
        userEmail = loginData.getString("userEmail", "");
        userCity = loginData.getString("userCity", "");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_item_toolbar);
        collapsingToolbarLayout.setTitle(""+itemName);
        itemImg = (ImageView) findViewById(R.id.item_image);
        Picasso.with(this).load(ipClass.ipAddress + itemImgSource).into(itemImg);

        dynamicToolbarColor();

        toolbarTextAppernce();

        counterVal = (EditText) findViewById(R.id.counter_value);
        counterVal.setFilters(new InputFilter[]{ new InputFilterMinMax(min, max)});

        priceValue = (TextView) findViewById(R.id.price_value);
        priceValue.setText(itemPrice);
        itemDescp = (TextView) findViewById(R.id.i_description_text);
        itemDescp.setText(itemDescription);

        counterInc = (Button) findViewById(R.id.inc_counter);
        counterDec = (Button) findViewById(R.id.dec_counter);
        addToCart = (Button) findViewById(R.id.add_to_cart);

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

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ipClass.ipAddress).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService service = retrofit.create(APIService.class);

                Call<String> cartData = service.ProcessCart(userEmail,itemId,itemName,resTitle,resId,branchCode,userCity,counterVal.getText().toString(),itemPrice,branchArea);

                cartData.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.signup);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.toolbar_color)));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
            }
        });
    }

    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
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
