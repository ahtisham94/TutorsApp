package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;

    @SerializedName("ProfileTypeID")
    @Expose
    private String profileTypeID;

    @SerializedName("profileStatus")
    @Expose
    private String profileStatus;

    @SerializedName("userID")
    @Expose
    private String userID;

    public UserInfo() {
    }

    public UserInfo(String msisdn, String sessionId, String profileStatus, String profileTypeID, String userID) {
        this.msisdn = msisdn;
        this.sessionId = sessionId;
        this.profileStatus = profileStatus;
        this.profileTypeID = profileTypeID;
        this.userID = userID;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProfileTypeID() {
        return profileTypeID;
    }

    public void setProfileTypeID(String profileTypeID) {
        this.profileTypeID = profileTypeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }
}