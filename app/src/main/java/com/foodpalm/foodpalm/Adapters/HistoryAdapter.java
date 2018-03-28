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
import com.foodpalm.foodpalm.models.History;
import com.foodpalm.foodpalm.models.OrderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/21/2017.
 */

public class HistoryAdapter extends ArrayAdapter {

    List histories = new ArrayList();
    Context context;

    public HistoryAdapter(Context context, int resource) {
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

    public void addHistories(List<History> obj){
        histories = obj;
    }

    @Override
    public int getCount() {
        return this.histories.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.histories.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final HistoryAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.history_list,parent,false);
            handler = new HistoryAdapter.Handler();
            handler.orderType = (TextView) row.findViewById(R.id.order_type_h);
            handler.resName = (TextView) row.findViewById(R.id.res_name_h);
            handler.brName = (TextView) row.findViewById(R.id.br_name_h);
            handler.amount = (TextView) row.findViewById(R.id.amount_h);
            handler.date = (TextView) row.findViewById(R.id.o_date_h);
            handler.time = (TextView) row.findViewById(R.id.o_time_h);
            handler.status = (TextView) row.findViewById(R.id.o_status_h);


            row.setTag(handler);
        }
        else {
            handler = (HistoryAdapter.Handler)row.getTag();
        }

        History history;
        history = (History) this.getItem(position);

        handler.orderType.setText(history.getOrderType());
        handler.resName.setText(history.getRestaurantTitle());
        handler.brName.setText(history.getBranchArea());
        handler.amount.setText(history.getAmount());
        handler.date.setText(history.getOrderDate());
        handler.time.setText(history.getOrderTime());
        handler.status.setText(history.getOrderStatus());

        return row;
    }
}
