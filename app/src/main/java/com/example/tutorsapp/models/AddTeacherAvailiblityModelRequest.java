package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTeacherAvailiblityModelRequest {

    @SerializedName("availableTimeSlots")
    @Expose
    private String availableTimeSlots;
    @SerializedName("availabilityDays")
    @Expose
    private String availabilityDays;
    @SerializedName("availabilityTime")
    @Expose
    private Integer availabilityTime;
    @SerializedName("currentEmployment")
    @Expose
    private String currentEmployment;
    @SerializedName("perHourFee")
    @Expose
    private Integer perHourFee;
    @SerializedName("perMonthFee")
    @Expose
    private Integer perMonthFee;
    @SerializedName("profileID")
    @Expose
    private Integer profileID;

    public String getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    public void setAvailableTimeSlots(String availableTimeSlots) {
        this.availableTimeSlots = availableTimeSlots;
    }

    public String getAvailabilityDays() {
        return availabilityDays;
    }

    public void setAvailabilityDays(String availabilityDays) {
        this.availabilityDays = availabilityDays;
    }

    public Integer getAvailabilityTime() {
        return availabilityTime;
    }

    public void setAvailabilityTime(Integer availabilityTime) {
        this.availabilityTime = availabilityTime;
    }

    public String getCurrentEmployment() {
        return currentEmployment;
    }

    public void setCurrentEmployment(String currentEmployment) {
        this.currentEmployment = currentEmployment;
    }

    public Integer getPerHourFee() {
        return perHourFee;
    }

    public void setPerHourFee(Integer perHourFee) {
        this.perHourFee = perHourFee;
    }

    public Integer getPerMonthFee() {
        return perMonthFee;
    }

    public void setPerMonthFee(Integer perMonthFee) {
        this.perMonthFee = perMonthFee;
    }

    public Integer getProfileID() {
        return profileID;
    }

    public void setProfileID(Integer profileID) {
        this.profileID = profileID;
    }

}