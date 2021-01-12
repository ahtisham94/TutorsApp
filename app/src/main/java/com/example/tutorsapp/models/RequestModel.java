package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestModel {

    @SerializedName("requestID")
    @Expose
    private Integer requestID;
    @SerializedName("requestFrom")
    @Expose
    private String requestFrom;
    @SerializedName("requestTo")
    @Expose
    private String requestTo;
    @SerializedName("requirementID")
    @Expose
    private String requirementID;
    @SerializedName("requestStatus")
    @Expose
    private String requestStatus;
    @SerializedName("reqestFromName")
    @Expose
    private String reqestFromName;
    @SerializedName("reqestToName")
    @Expose
    private String reqestToName;
    @SerializedName("channelID")
    @Expose
    private Integer channelID;

    @SerializedName("userInfo")
    private UserInfo userInfo;

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public String getRequirementID() {
        return requirementID;
    }

    public void setRequirementID(String requirementID) {
        this.requirementID = requirementID;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getReqestFromName() {
        return reqestFromName;
    }

    public void setReqestFromName(String reqestFromName) {
        this.reqestFromName = reqestFromName;
    }

    public String getReqestToName() {
        return reqestToName;
    }

    public void setReqestToName(String reqestToName) {
        this.reqestToName = reqestToName;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getChannelID() {
        return channelID;
    }

    public void setChannelID(Integer channelID) {
        this.channelID = channelID;
    }
}