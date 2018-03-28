package com.foodpalm.foodpalm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodpalm.foodpalm.Activities.ItemDetailsActivity;
import com.foodpalm.foodpalm.Activities.TestActivity;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.MenuItemDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/3/2017.
 */

public class MenuAdapter extends ArrayAdapter {
    List menuItems = new ArrayList();
    Context context;
    IpClass ipClass = new IpClass();
    public String resTitle;
    public String branchCode;
    public String branchArea;
    public String orderType;

    public MenuAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        ImageView menuImg;
        TextView itemName;
        TextView itemDescription;
        TextView itemPrice;
        TextView itemDiscount;
        Button addBtn;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addMenuList(List<MenuItemDetails> obj){
        menuItems = obj;
    }

    @Override
    public int getCount() {
        return this.menuItems.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.menuItems.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final MenuAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.menu_list,parent,false);
            handler = new MenuAdapter.Handler();
            handler.menuImg = (ImageView) row.findViewById(R.id.menu_item_img);
            handler.itemName = (TextView) row.findViewById(R.id.menu_item_name);
            handler.itemDescription = (TextView) row.findViewById(R.id.menu_item_description);
            handler.itemPrice = (TextView) row.findViewById(R.id.menu_item_price);
            handler.itemDiscount = (TextView) row.findViewById(R.id.menu_item_discount);
            handler.addBtn = (Button) row.findViewById(R.id.addbtn);

            row.setTag(handler);
        }
        else {
            handler = (MenuAdapter.Handler)row.getTag();
        }

        final  MenuItemDetails menuItemDetails;
        menuItemDetails = (MenuItemDetails) this.getItem(position);

        if(menuItemDetails.getImageSource() == null || menuItemDetails.getImageSource() == ""){
            handler.menuImg.setVisibility(View.GONE);
        }
        else {
            handler.menuImg.setVisibility(View.VISIBLE);
            Picasso.with(this.getContext()).load(ipClass.ipAddress + menuItemDetails.getImageSource()).into(handler.menuImg);
        }
        handler.itemName.setText(menuItemDetails.getItemName());
        handler.itemDescription.setText(menuItemDetails.getDescription());
        if(menuItemDetails.getPrice() != 0){
            handler.itemPrice.setText("Rs. " + menuItemDetails.getPrice());
        }
        else {
            handler.itemPrice.setVisibility(View.GONE);
        }
        if(menuItemDetails.getDiscount() != 0){
            handler.itemDiscount.setText(menuItemDetails.getDiscount() + "%");
            handler.itemDiscount.setVisibility(View.VISIBLE);
        }
        else {
            handler.itemDiscount.setVisibility(View.GONE);
        }

        handler.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ItemDetailsActivity.class);
                    intent.putExtra("restaurantId", "" + menuItemDetails.getRestaurantId());
                    intent.putExtra("restaurantTitle", "" + resTitle);
                    intent.putExtra("branchCode", "" + branchCode);
                    intent.putExtra("branchArea", "" + branchArea);
                    intent.putExtra("categoryId", "" + menuItemDetails.getCategoryId());
                    intent.putExtra("subcategoryId", "" + menuItemDetails.getSubCategoryId());
                    intent.putExtra("itemId","" + menuItemDetails.getItemId());
                    intent.putExtra("itemImg","" + menuItemDetails.getImageSource());
                    intent.putExtra("itemName","" + menuItemDetails.getItemName());
                    intent.putExtra("itemPrice","" + menuItemDetails.getPrice());
                    intent.putExtra("itemComboPrice","" + menuItemDetails.getComboPrice());
                    intent.putExtra("itemComboCheck","" + menuItemDetails.getComboCheck());
                    intent.putExtra("itemDescription","" + menuItemDetails.getDescription());
                    intent.putExtra("combo", "" + menuItemDetails.getCombo());
                    context.startActivity(intent);
            }
        });


        return row;
    }
}
