package com.foodpalm.foodpalm.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.ResDetailsModel;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RestaurantDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private String resId;
    private String resTitle,branchCode,branchArea;
    IpClass ipClass = new IpClass();
    private ResDetailsModel resDetails = new ResDetailsModel();
    private ImageView restaurantDp;
    private TextView rating,rated,description;
    Button reserveTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        resId = getIntent().getExtras().getString("restaurantId");
        resTitle = getIntent().getExtras().getString("restaurantTitle");
        branchCode = getIntent().getExtras().getString("branchCode");
        branchArea = getIntent().getExtras().getString("branchArea");

        rating = (TextView) findViewById(R.id.res_rating);
        rated = (TextView) findViewById(R.id.rated);
        description = (TextView) findViewById(R.id.res_description);

        toolbar = (Toolbar) findViewById(R.id.res_detail_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_restaurant_toolbar);
        collapsingToolbarLayout.setTitle(""+resTitle);
        restaurantDp = (ImageView) findViewById(R.id.restaurant_dp);

        reserveTable = (Button) findViewById(R.id.reserve_table_btn);
        reserveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TableRequestActivity.class);
                intent.putExtra("restaurantId", "" + resId);
                intent.putExtra("restaurantTitle", "" + resTitle);
                intent.putExtra("branchCode", "" + branchCode);
                intent.putExtra("branchArea", "" + branchArea);
                startActivity(intent);
                finish();
            }
        });

        dynamicToolbarColor();

        toolbarTextAppernce();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        final Call<ResDetailsModel> restaurantDetails = service.ProcessRestaurantDetails(resId);

        restaurantDetails.enqueue(new Callback<ResDetailsModel>() {
            @Override
            public void onResponse(Response<ResDetailsModel> response, Retrofit retrofit) {
                resDetails = response.body();

                if(response.body() == null){
                    Toast.makeText(getApplicationContext(),"No Data found for "+resTitle,Toast.LENGTH_SHORT).show();
                }
                else {
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + resDetails.getImgSource()).into(restaurantDp);
                    rating.setText(String.valueOf(resDetails.getRatings()));
                    rated.setText(String.valueOf("/ "+resDetails.getNumofPeopleRated()));
                    description.setText(resDetails.getDescription());
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(getApplicationContext(),"Connection Error ",Toast.LENGTH_SHORT).show();

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
