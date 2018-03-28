package com.foodpalm.foodpalm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.models.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/7/2017.
 */

public class SubCategoryAdapter extends ArrayAdapter {
    List subCategories = new ArrayList();
    Context context;

    public SubCategoryAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        TextView subCatName;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addSubCategoryList(List<SubCategory> obj){
        subCategories = obj;
    }

    @Override
    public int getCount() {
        return this.subCategories.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.subCategories.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final SubCategoryAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.sub_category_grid,parent,false);
            handler = new SubCategoryAdapter.Handler();
            handler.subCatName = (TextView) row.findViewById(R.id.sub_cat_name);

            row.setTag(handler);
        }
        else {
            handler = (SubCategoryAdapter.Handler)row.getTag();
        }

        SubCategory subCategory;
        subCategory = (SubCategory) this.getItem(position);


        handler.subCatName.setText(subCategory.getSubCategoryName());

        return row;
    }
}
