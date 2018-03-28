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
import com.foodpalm.foodpalm.models.Cart;
import com.foodpalm.foodpalm.models.OrderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/21/2017.
 */

public class OrderAdapter extends ArrayAdapter {

    List orderViews = new ArrayList();
    Context context;

    public OrderAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        TextView orderType,resName,brName,amount,date,time,status;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addOrderViews(List<OrderView> obj){
        orderViews = obj;
    }

    @Override
    public int getCount() {
        return this.orderViews.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.orderViews.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final OrderAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.orders_list,parent,false);
            handler = new OrderAdapter.Handler();
            handler.orderType = (TextView) row.findViewById(R.id.order_type_o);
            handler.resName = (TextView) row.findViewById(R.id.res_name);
            handler.brName = (TextView) row.findViewById(R.id.br_name);
            handler.amount = (TextView) row.findViewById(R.id.amount);
            handler.date = (TextView) row.findViewById(R.id.o_date);
            handler.time = (TextView) row.findViewById(R.id.o_time);
            handler.status = (TextView) row.findViewById(R.id.o_status);


            row.setTag(handler);
        }
        else {
            handler = (OrderAdapter.Handler)row.getTag();
        }

        OrderView orderView;
        orderView = (OrderView) this.getItem(position);

        handler.orderType.setText(orderView.getOrderType());
        handler.resName.setText(orderView.getRestaurantTitle());
        handler.brName.setText(orderView.getBranchArea());
        handler.amount.setText(orderView.getAmount());
        handler.date.setText(orderView.getOrderDate());
        handler.time.setText(orderView.getOrderTime());
        handler.status.setText(orderView.getOrderStatus());

        return row;
    }
}
