package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEducationMainRequestModel {
    public String getTeachingMode() {
        return teachingMode;
    }

    public void setTeachingMode(String teachingMode) {
        this.teachingMode = teachingMode;
    }

    public String getFinalDegreePath() {
        return finalDegreePath;
    }

    public void setFinalDegreePath(String finalDegreePath) {
        this.finalDegreePath = finalDegreePath;
    }

    public String getTeachingToolType() {
        return teachingToolType;
    }

    public void setTeachingToolType(String teachingToolType) {
        this.teachingToolType = teachingToolType;
    }

    public String getTeachingCategory() {
        return teachingCategory;
    }

    public void setTeachingCategory(String teachingCategory) {
        this.teachingCategory = teachingCategory;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    @SerializedName("teachingMode")
    @Expose
    private String teachingMode;

    @SerializedName("finalDegreePath")
    @Expose
    private String finalDegreePath;

    @SerializedName("teachingToolType")
    @Expose
    private String teachingToolType;

    @SerializedName("teachingCategory")
    @Expose
    private String teachingCategory;

    @SerializedName("profileID")
    @Expose
    private int profileID;

}
