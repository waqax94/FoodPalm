package com.foodpalm.foodpalm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.models.Cart;
import com.foodpalm.foodpalm.models.IpClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/19/2017.
 */

public class CartAdapter extends ArrayAdapter {

    List cartItems = new ArrayList();
    IpClass ipClass = new IpClass();
    Context context;

    public CartAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        TextView quant,itemName,price;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addCartItems(List<Cart> obj){
        cartItems = obj;
    }

    @Override
    public int getCount() {
        return this.cartItems.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.cartItems.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final CartAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.cart_list,parent,false);
            handler = new CartAdapter.Handler();
            handler.quant = (TextView) row.findViewById(R.id.cart_quantity);
            handler.itemName = (TextView) row.findViewById(R.id.cart_item_name);
            handler.price = (TextView) row.findViewById(R.id.cart_price_value);


            row.setTag(handler);
        }
        else {
            handler = (CartAdapter.Handler)row.getTag();
        }

        Cart cart;
        cart = (Cart) this.getItem(position);

        handler.itemName.setText(cart.getItemDealName());

        handler.quant.setText(cart.getQuantity());
        handler.price.setText("" + (Integer.parseInt(cart.getPrice()) * Integer.parseInt(cart.getQuantity())));

        return row;
    }
}
