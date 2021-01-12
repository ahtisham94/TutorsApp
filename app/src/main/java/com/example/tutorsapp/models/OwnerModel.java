package com.example.tutorsapp.models;

import com.google.gson.annotations.SerializedName;

public class OwnerModel {

    @SerializedName("ownerID")
    private Integer ownerID;

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }
}
