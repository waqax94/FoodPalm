package com.foodpalm.foodpalm.Service;

import com.foodpalm.foodpalm.models.Cart;
import com.foodpalm.foodpalm.models.Category;
import com.foodpalm.foodpalm.models.History;
import com.foodpalm.foodpalm.models.MenuItemDetails;
import com.foodpalm.foodpalm.models.Order;
import com.foodpalm.foodpalm.models.OrderView;
import com.foodpalm.foodpalm.models.ResDetailsModel;
import com.foodpalm.foodpalm.models.Restaurant;
import com.foodpalm.foodpalm.models.SubCategory;
import com.foodpalm.foodpalm.models.TableReservation;
import com.foodpalm.foodpalm.models.User;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by User on 2/9/2017.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("Tableprocessing.php")
    Call<List<TableReservation>> ProcessTables(@Field("email") String email);


    @FormUrlEncoded
    @POST("Tablereservationprocessing.php")
    Call<String> ProcessTableReservation(@Field("restaurantId") String restaurantId,
                                         @Field("branchCode") String branchCode,
                                         @Field("restaurantTitle") String restaurantTitle,
                                         @Field("branchArea") String branchArea,
                                         @Field("numOfPersons") String numOfPersons,
                                         @Field("reservationTime") String reservationTime,
                                         @Field("reservationDate") String reservationDate,
                                         @Field("cname") String cname,
                                         @Field("email") String email);

    @FormUrlEncoded
    @POST("Historyprocessing.php")
    Call<List<History>> ProcessHistory(@Field("email") String email);

    @FormUrlEncoded
    @POST("Orderviewprocessing.php")
    Call<List<OrderView>> ProcessOrderViews(@Field("email") String email);

    @FormUrlEncoded
    @POST("Orderprocessing3.php")
    Call<String> ProcessCartClearing(@Field("email") String email);

    @FormUrlEncoded
    @POST("Orderprocessing2.php")
    Call<String> ProcessOrderItemDetails(@Field("orderDate") String orderDate,
                                   @Field("orderTime") String orderTime,
                                   @Field("email") String email,
                                   @Field("restaurantId") String restaurantId,
                                   @Field("branchCode") String branchCode,
                                   @Field("orderId") String orderId);

    @FormUrlEncoded
    @POST("Orderprocessing1.php")
    Call<String> ProcessOrderItems(@Field("orderDate") String orderDate,
                             @Field("orderTime") String orderTime,
                             @Field("email") String email,
                             @Field("restaurantId") String restaurantId,
                             @Field("branchCode") String branchCode);

    @FormUrlEncoded
    @POST("Orderprocessing.php")
    Call<Order> ProcessOrder(@Field("orderType") String orderType,
                             @Field("email") String email,
                             @Field("cname") String cname,
                             @Field("ammount") String ammount);

    @FormUrlEncoded
    @POST("Getcartitems.php")
    Call<List<Cart>> ProcessCartItems(@Field("email") String email);

    @FormUrlEncoded
    @POST("Cartprocessing.php")
    Call<String> ProcessCart(@Field("email") String email,
                             @Field("itemDealId") String itemDealId,
                             @Field("itemDealName") String itemDealName,
                             @Field("restaurantTitle") String restaurantTitle,
                             @Field("restaurantId") String restaurantId,
                             @Field("branchCode") String branchCode,
                             @Field("city") String city,
                             @Field("quantity") String quantity,
                             @Field("price") String price,
                             @Field("branchArea") String branchArea);

    @FormUrlEncoded
    @POST("Menuprocessing.php")
    Call<List<MenuItemDetails>> ProcessMenu(@Field("subCatId") String subCatId,
                                            @Field("catId") String catId,
                                            @Field("resId") String resId);

    @FormUrlEncoded
    @POST("Subcategoryprocessing.php")
    Call<List<SubCategory>> ProcessSubCategory(@Field("catId") String catId,
                                                        @Field("resId") String resId);

    @FormUrlEncoded
    @POST("Categoryprocessing.php")
    Call<List<Category>> ProcessCategory(@Field("resId") String resId);

    @FormUrlEncoded
    @POST("Restaurantdetailprocessing.php")
    Call<ResDetailsModel> ProcessRestaurantDetails(@Field("resId") String resId);

    @FormUrlEncoded
    @POST("Restaurantprocessingdinein.php")
    Call<List<Restaurant>> ProcessDineInRestaurant(@Field("latitude") String latitude,
                                             @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("Restaurantprocessinghomedelivery.php")
    Call<List<Restaurant>> ProcessHomeDeliveryRestaurant(@Field("latitude") String latitude,
                                                   @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("Signupprocessing.php")
    Call<String> ProcessSignUp(@Field("email") String c_email,
                             @Field("cname") String c_name,
                             @Field("password") String c_psw,
                             @Field("city") String c_city);

    @FormUrlEncoded
    @POST("Loginprocessing.php")
    Call<User> ProcessLogin(@Field("email") String c_email,
                            @Field("password") String c_psw);

}
