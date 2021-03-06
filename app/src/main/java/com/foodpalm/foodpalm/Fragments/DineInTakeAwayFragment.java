package com.foodpalm.foodpalm.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.Activities.RestaurantDetails;
import com.foodpalm.foodpalm.Adapters.RestaurantAdapter;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.Restaurant;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DineInTakeAwayFragment extends Fragment {

    ListView listView;
    RestaurantAdapter restaurantAdapter;
    IpClass ipClass = new IpClass();
    TextView noRestaurant;
    Button orderBtn;
    ProgressDialog pDialog;
    public String latitude,longitude;


    public DineInTakeAwayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_dine_in_take_away, container, false);


        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        showpDialog();

        listView =(ListView)rootView.findViewById(R.id.restuarant_list);
        noRestaurant = (TextView)rootView.findViewById(R.id.no_restaurant_dt);
        orderBtn = (Button)rootView.findViewById(R.id.order_now);
        restaurantAdapter = new RestaurantAdapter(getActivity().getApplicationContext(),R.layout.restuarent_list);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<List<Restaurant>> restaurantData = service.ProcessDineInRestaurant(latitude,longitude);


        restaurantData.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(final Response<List<Restaurant>> response, Retrofit retrofit) {
                final List<Restaurant> restaurants = response.body();
                if(response.body() == null){
                    noRestaurant.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                }
                else {
                    listView.setVisibility(View.VISIBLE);
                    noRestaurant.setVisibility(View.INVISIBLE);
                    restaurantAdapter.addRestuarantList(restaurants);
                    restaurantAdapter.orderType = "Dine In/Take Away";
                    listView.setAdapter(restaurantAdapter);
                }
                hidepDialog();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), RestaurantDetails.class);
                        intent.putExtra("restaurantId", "" + restaurants.get(position).getRestaurantId());
                        intent.putExtra("restaurantTitle", "" + restaurants.get(position).getRestaurantTitle());
                        intent.putExtra("branchCode", "" + restaurants.get(position).getBranchCode());
                        intent.putExtra("branchArea", "" + restaurants.get(position).getBranchArea());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(),"Connection Error",Toast.LENGTH_LONG).show();
                hidepDialog();
            }
        });



        return rootView;

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
