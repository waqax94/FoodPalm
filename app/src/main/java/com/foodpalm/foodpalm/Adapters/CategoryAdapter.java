package com.foodpalm.foodpalm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.models.Category;
import com.foodpalm.foodpalm.models.IpClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/29/2017.
 */

public class CategoryAdapter extends ArrayAdapter {

    List categories= new ArrayList();
    IpClass ipClass = new IpClass();
    Context context;


    public CategoryAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        ImageView catimg;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addCategoryList(List<Category> obj){
        categories = obj;
    }

    @Override
    public int getCount() {
        return this.categories.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.categories.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final CategoryAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.categorey_list,parent,false);
            handler = new CategoryAdapter.Handler();
            handler.catimg = (ImageView)row.findViewById(R.id.cat_img);

            row.setTag(handler);
        }
        else {
            handler = (CategoryAdapter.Handler)row.getTag();
        }

        Category category;
        category = (Category) this.getItem(position);


        Picasso.with(this.getContext()).load(ipClass.ipAddress + category.getCatImageSource()).into(handler.catimg);

        return row;
    }
}
