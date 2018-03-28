package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.StringTokenizer;

/**
 * Created by User on 4/27/2017.
 */

public class ResDetailsModel {

    @SerializedName("restaurantTitle")
    @Expose
    private String restaurantTitle;
    @SerializedName("ratings")
    @Expose
    private double ratings;
    @SerializedName("imgSource")
    @Expose
    private String imgSource;
    @SerializedName("numofPeopleRated")
    @Expose
    private int numofPeopleRated;
    @SerializedName("description")
    @Expose
    private String description;

    public ResDetailsModel() {
    }

    public ResDetailsModel(String restaurantTitle, double ratings, String imgSource, int numofPeopleRated, String description) {
        this.restaurantTitle = restaurantTitle;
        this.ratings = ratings;
        this.imgSource = imgSource;
        this.numofPeopleRated = numofPeopleRated;
        this.description = description;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public void setRestaurantTitle(String restaurantTitle) {
        this.restaurantTitle = restaurantTitle;
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

    public int getNumofPeopleRated() {
        return numofPeopleRated;
    }

    public void setNumofPeopleRated(int numofPeopleRated) {
        this.numofPeopleRated = numofPeopleRated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
