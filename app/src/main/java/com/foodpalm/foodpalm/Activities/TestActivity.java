package com.foodpalm.foodpalm.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;

public class TestActivity extends AppCompatActivity {

    String resId;
    String resTitle;
    String branchCode;
    String catId;
    String subCatId,itemId,itemImgSource,itemName,itemPrice,itemComboPrice,itemComboCheck,itemDescription,combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resId = getIntent().getExtras().getString("restaurantId");
        resTitle = getIntent().getExtras().getString("restaurantTitle");
        branchCode = getIntent().getExtras().getString("branchCode");
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

        Toast.makeText(this,resId + " \n" + resTitle + "\n" + subCatId + "\n" + itemId + "\n" + itemName + "\n" + itemPrice + "\n" + itemComboPrice + "\n" + itemComboCheck + "\n" + itemDescription + "\n" + combo,Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
