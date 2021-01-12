package com.example.tutorsapp.models;

import com.example.tutorsapp.helper.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExperienceDetailModel {
    @SerializedName("organization")
    @Expose
    private String organization;
    transient private String startDate;
    transient private String endDate;
    transient private String experienceInYears;
    transient private String insitituteType;

    @SerializedName("fromDate")
    @Expose
    private String fromDate;

    @SerializedName("toDate")
    @Expose
    private String toDate;

    @SerializedName("profileID")
    @Expose
    private int profileID;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @SerializedName("duration")
    @Expose
    private String duration;


    public ExperienceDetailModel(String organization, String startDate, String endDate, String experienceInYears, String insitituteType) {
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.experienceInYears = experienceInYears;
        this.insitituteType = insitituteType;
    }

    public ExperienceDetailModel() {
    }

    public String getorganization() {
        return organization;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(Calendar calendar) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(Constants.timeFormate);
        this.fromDate = inputFormat.format(calendar.getTime());
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(Calendar calendar) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(Constants.timeFormate);

        this.toDate = inputFormat.format(calendar.getTime());
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public void setorganization(String organization) {
        this.organization = organization;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(String experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public String getInsitituteType() {
        return insitituteType;
    }

    public void setInsitituteType(String insitituteType) {
        this.insitituteType = insitituteType;
    }


}
