package com.foodpalm.foodpalm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 6/21/2017.
 */

public class TableReservation {

    @SerializedName("tableId")
    @Expose
    private String tableId;
    @SerializedName("restaurantTitle")
    @Expose
    private String restaurantTitle;
    @SerializedName("branchArea")
    @Expose
    private String branchArea;
    @SerializedName("tableStatus")
    @Expose
    private String tableStatus;
    @SerializedName("numOfPersons")
    @Expose
    private String numOfPersons;
    @SerializedName("reservationTime")
    @Expose
    private String reservationTime;
    @SerializedName("reservationDate")
    @Expose
    private String reservationDate;

    public TableReservation() {
    }

    public TableReservation(String tableId, String restaurantTitle, String branchArea, String tableStatus, String numOfPersons, String reservationTime, String reservationDate) {
        this.tableId = tableId;
        this.restaurantTitle = restaurantTitle;
        this.branchArea = branchArea;
        this.tableStatus = tableStatus;
        this.numOfPersons = numOfPersons;
        this.reservationTime = reservationTime;
        this.reservationDate = reservationDate;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public String getNumOfPersons() {
        return numOfPersons;
    }

    public void setNumOfPersons(String numOfPersons) {
        this.numOfPersons = numOfPersons;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
