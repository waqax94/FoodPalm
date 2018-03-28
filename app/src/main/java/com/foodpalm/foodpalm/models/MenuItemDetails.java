package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 6/3/2017.
 */

public class MenuItemDetails {

    @SerializedName("restaurantId")
    @Expose
    private int restaurantId;
    @SerializedName("subCategoryId ")
    @Expose
    private int subCategoryId ;
    @SerializedName("categoryId")
    @Expose
    private int categoryId ;
    @SerializedName("itemId")
    @Expose
    private int itemId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageSource")
    @Expose
    private String imageSource;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("combo")
    @Expose
    private String combo;
    @SerializedName("discount")
    @Expose
    private double discount;
    @SerializedName("comboCheck")
    @Expose
    private String comboCheck;
    @SerializedName("comboPrice")
    @Expose
    private int comboPrice;

    public MenuItemDetails() {
    }

    public MenuItemDetails(int restaurantId, int subCategoryId, int categoryId, int itemId, String itemName, String description, String imageSource, int price, String combo, double discount, String comboCheck, int comboPrice) {
        this.restaurantId = restaurantId;
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.imageSource = imageSource;
        this.price = price;
        this.combo = combo;
        this.discount = discount;
        this.comboCheck = comboCheck;
        this.comboPrice = comboPrice;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getComboCheck() {
        return comboCheck;
    }

    public void setComboCheck(String comboCheck) {
        this.comboCheck = comboCheck;
    }

    public int getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(int comboPrice) {
        this.comboPrice = comboPrice;
    }
}
