package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LOVSRequestModel {
    @SerializedName("isQuranCategory")
    @Expose
    private boolean isQuranCategory;
    @SerializedName("parentID")
    @Expose
    private String parentID;
    @SerializedName("key")
    @Expose
    private String key;

    public LOVSRequestModel(boolean isQuranCategory, String parentID, String key) {
        this.isQuranCategory = isQuranCategory;
        this.parentID = parentID;
        this.key = key;
    }
}
