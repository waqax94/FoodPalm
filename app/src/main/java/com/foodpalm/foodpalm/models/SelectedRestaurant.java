package com.foodpalm.foodpalm.models;

/**
 * Created by User on 3/17/2017.
 */

public final class SelectedRestaurant{

    private SelectedRestaurant() {
    }

    private static String selectedRestaurantID;
    private static String selectedBranchCode;

    public static String getSelectedRestaurantID() {
        return selectedRestaurantID;
    }

    public static void setSelectedRestaurantID(String selectedRestaurantID) {
        SelectedRestaurant.selectedRestaurantID = selectedRestaurantID;
    }

    public static String getSelectedBranchCode() {
        return selectedBranchCode;
    }

    public static void setSelectedBranchCode(String selectedBranchCode) {
        SelectedRestaurant.selectedBranchCode = selectedBranchCode;
    }
}
