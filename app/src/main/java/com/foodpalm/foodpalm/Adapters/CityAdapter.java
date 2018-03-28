package com.foodpalm.foodpalm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.foodpalm.foodpalm.R;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> cities;
    LayoutInflater inflter;

    public CityAdapter(Context applicationContext, ArrayList<String> cities) {
        this.context = applicationContext;
        this.cities = cities;
        inflter = (LayoutInflater.from(applicationContext));
    }



    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_city_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.citylist);
        names.setText(cities.get(i));
        return view;
    }
}
