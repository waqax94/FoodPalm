package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 6/21/2017.
 */

public class OrderView {

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("orderTime")
    @Expose
    private String orderTime;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("orderType")
    @Expose
    private String orderType;
    @SerializedName("restaurantTitle")
    @Expose
    private String restaurantTitle;
    @SerializedName("branchArea")
    @Expose
    private String branchArea;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;

    public OrderView() {
    }

    public OrderView(String orderId, String orderDate, String orderTime, String amount, String orderType, String restaurantTitle, String branchArea, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.amount = amount;
        this.orderType = orderType;
        this.restaurantTitle = restaurantTitle;
        this.branchArea = branchArea;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public void setRestaurantTitle(String restaurantTitle) {
        this.restaurantTitle = restaurantTitle;
    }

    public String getBranchArea() {
        return branchArea;
    }

    public void setBranchArea(String branchArea) {
        this.branchArea = branchArea;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
