package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 6/19/2017.
 */

public class Cart {

    @SerializedName("cartId")
    @Expose
    private String cartId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("itemDealId")
    @Expose
    private String itemDealId;
    @SerializedName("ItemDealName")
    @Expose
    private String ItemDealName;
    @SerializedName("restaurantTitle")
    @Expose
    private String restaurantTitle;
    @SerializedName("restaurantId")
    @Expose
    private String restaurantId;
    @SerializedName("branchCode")
    @Expose
    private String branchCode;
    @SerializedName("branchArea")
    @Expose
    private String branchArea;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;

    public Cart() {
    }

    public Cart(String cartId, String email, String itemDealId, String itemDealName, String restaurantTitle, String restaurantId, String branchCode, String branchArea, String city, String quantity, String price) {
        this.cartId = cartId;
        this.email = email;
        this.itemDealId = itemDealId;
        this.ItemDealName = itemDealName;
        this.restaurantTitle = restaurantTitle;
        this.restaurantId = restaurantId;
        this.branchCode = branchCode;
        this.branchArea = branchArea;
        this.city = city;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemDealId() {
        return itemDealId;
    }

    public void setItemDealId(String itemDealId) {
        this.itemDealId = itemDealId;
    }

    public String getItemDealName() {
        return ItemDealName;
    }

    public void setItemDealName(String itemDealName) {
        ItemDealName = itemDealName;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public void setRestaurantTitle(String restaurantTitle) {
        this.restaurantTitle = restaurantTitle;
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

    public String getBranchArea() {
        return branchArea;
    }

    public void setBranchArea(String branchArea) {
        this.branchArea = branchArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
