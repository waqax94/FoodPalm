package com.foodpalm.foodpalm.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodpalm.foodpalm.Activities.CategoryActivity;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.RatingBarSetter;
import com.foodpalm.foodpalm.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/11/2017.
 */

public class RestaurantAdapter extends ArrayAdapter {
    List restuarents= new ArrayList();
    RatingBarSetter ratingBarSetter = new RatingBarSetter();
    IpClass ipClass = new IpClass();
    Context context;
    public String orderType;




    public RestaurantAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        ImageView logo,star1,star2,star3,star4,star5;
        TextView title;
        TextView branch;
        Button orderBtn;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        restuarents.add(object);
    }

    public void addRestuarantList(List<Restaurant> obj){
        restuarents = obj;
    }



    @Override
    public int getCount() {
        return this.restuarents.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.restuarents.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.restuarent_list,parent,false);
            handler = new Handler();
            handler.logo = (ImageView)row.findViewById(R.id.res_icon);
            handler.title = (TextView)row.findViewById(R.id.res_title);
            handler.branch = (TextView)row.findViewById(R.id.res_branch_title);
            handler.star1 = (ImageView)row.findViewById(R.id.ratingStar1);
            handler.star2 = (ImageView)row.findViewById(R.id.ratingStar2);
            handler.star3 = (ImageView)row.findViewById(R.id.ratingStar3);
            handler.star4 = (ImageView)row.findViewById(R.id.ratingStar4);
            handler.star5 = (ImageView)row.findViewById(R.id.ratingStar5);
            handler.orderBtn = (Button)row.findViewById(R.id.order_now);

            row.setTag(handler);
        }
        else {
            handler = (Handler)row.getTag();
        }

        final Restaurant restaurant;
        restaurant = (Restaurant) this.getItem(position);

        handler.orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("restaurantId", "" + restaurant.getRestaurantId());
                intent.putExtra("restaurantTitle", "" + restaurant.getRestaurantTitle());
                intent.putExtra("branchCode", "" + restaurant.getBranchCode());
                intent.putExtra("branchArea", "" + restaurant.getBranchArea());
                intent.putExtra("orderType", "" + orderType);
                context.startActivity(intent);
            }
        });

        Picasso.with(this.getContext()).load(ipClass.ipAddress + restaurant.getImgSource()).into(handler.logo);
        handler.title.setText(restaurant.getRestaurantTitle());
        handler.branch.setText(restaurant.getBranchArea());
        ratingBarSetter.setRatingStar(restaurant.getRatings(),handler.star1,handler.star2,handler.star3,handler.star4,handler.star5);

        return row;
    }
}
