package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 4/29/2017.
 */

public class Category {

    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("catImageSource")
    @Expose
    private String catImageSource;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String catImageSource) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.catImageSource = catImageSource;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCatImageSource() {
        return catImageSource;
    }

    public void setCatImageSource(String catImageSource) {
        this.catImageSource = catImageSource;
    }
}
