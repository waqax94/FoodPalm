package com.foodpalm.foodpalm.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.foodpalm.foodpalm.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 3/10/2017.
 */

public class Restaurant {

    @SerializedName("restaurantId")
    @Expose
    private int restaurantId;
    @SerializedName("branchCode")
    @Expose
    private int branchCode;
    @SerializedName("restaurantTitle")
    @Expose
    private String restaurantTitle;
    @SerializedName("branchArea")
    @Expose
    private String branchArea;
    @SerializedName("ratings")
    @Expose
    private double ratings;
    @SerializedName("imgSource")
    @Expose
    private String imgSource;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, int branchCode, String restaurantTitle, String branchArea, double ratings, String imgSource) {
        this.restaurantId = restaurantId;
        this.branchCode = branchCode;
        this.restaurantTitle = restaurantTitle;
        this.branchArea = branchArea;
        this.ratings = ratings;
        this.imgSource = imgSource;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
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

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }
}
