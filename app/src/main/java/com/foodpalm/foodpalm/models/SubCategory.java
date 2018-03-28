package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 5/7/2017.
 */

public class SubCategory {

    @SerializedName("subCategoryId")
    @Expose
    private int subCategoryId;
    @SerializedName("subCategoryName")
    @Expose
    private String subCategoryName;

    public SubCategory() {
    }

    public SubCategory(int subCategoryId, String subCategoryName) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
