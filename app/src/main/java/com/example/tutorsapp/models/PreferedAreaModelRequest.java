package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferedAreaModelRequest {

    @SerializedName("availabilityStatus")
    @Expose
    private String availabilityStatus;
    @SerializedName("prefferdArea")
    @Expose
    private String prefferdArea;
    @SerializedName("modeOfConvince")
    @Expose
    private String modeOfConvince;
    @SerializedName("lat_Long")
    @Expose
    private String latLong;
    @SerializedName("pointOfReference")
    @Expose
    private String pointOfReference;
    @SerializedName("profileID")
    @Expose
    private Integer profileID;

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getPrefferdArea() {
        return prefferdArea;
    }

    public void setPrefferdArea(String prefferdArea) {
        this.prefferdArea = prefferdArea;
    }

    public String getModeOfConvince() {
        return modeOfConvince;
    }

    public void setModeOfConvince(String modeOfConvince) {
        this.modeOfConvince = modeOfConvince;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getPointOfReference() {
        return pointOfReference;
    }

    public void setPointOfReference(String pointOfReference) {
        this.pointOfReference = pointOfReference;
    }

    public Integer getProfileID() {
        return profileID;
    }

    public void setProfileID(Integer profileID) {
        this.profileID = profileID;
    }

}