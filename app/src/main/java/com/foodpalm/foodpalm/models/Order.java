package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 6/20/2017.
 */

public class Order {

    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("orderTime")
    @Expose
    private String orderTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("restaurantId")
    @Expose
    private String restaurantId;
    @SerializedName("branchCode")
    @Expose
    private String branchCode;

    public Order() {
    }

    public Order(String orderDate, String orderTime, String email, String restaurantId, String branchCode) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.email = email;
        this.restaurantId = restaurantId;
        this.branchCode = branchCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}
