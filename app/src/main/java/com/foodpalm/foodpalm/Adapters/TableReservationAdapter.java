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
import com.foodpalm.foodpalm.models.OrderView;
import com.foodpalm.foodpalm.models.TableReservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/21/2017.
 */

public class TableReservationAdapter extends ArrayAdapter {

    List tableReservations = new ArrayList();
    Context context;

    public TableReservationAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        TextView resName,branchArea,tableId,numOfPersons,time,date,tableStatus;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addTableReservations(List<TableReservation> obj){
        tableReservations = obj;
    }

    @Override
    public int getCount() {
        return this.tableReservations.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.tableReservations.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final TableReservationAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.table_list,parent,false);
            handler = new TableReservationAdapter.Handler();
            handler.resName = (TextView) row.findViewById(R.id.r_res_name);
            handler.branchArea = (TextView) row.findViewById(R.id.r_brancharea);
            handler.numOfPersons = (TextView) row.findViewById(R.id.r_numofperson);
            handler.tableId = (TextView) row.findViewById(R.id.r_table_id);
            handler.date = (TextView) row.findViewById(R.id.r_date);
            handler.time = (TextView) row.findViewById(R.id.r_time);
            handler.tableStatus = (TextView) row.findViewById(R.id.r_status);


            row.setTag(handler);
        }
        else {
            handler = (TableReservationAdapter.Handler)row.getTag();
        }

        TableReservation tableReservation;
        tableReservation = (TableReservation) this.getItem(position);

        handler.resName.setText(tableReservation.getRestaurantTitle());
        handler.branchArea.setText(tableReservation.getBranchArea());
        handler.numOfPersons.setText(tableReservation.getNumOfPersons());
        handler.tableId.setText(tableReservation.getTableId());
        handler.date.setText(tableReservation.getReservationDate());
        handler.time.setText(tableReservation.getReservationTime());
        handler.tableStatus.setText(tableReservation.getTableStatus());

        return row;
    }
}
