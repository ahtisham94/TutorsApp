package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralResponse<T> {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private T data;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("sessionID")
    @Expose
    private String sessionID;
    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("profileStatus")
    @Expose
    private String profileStatus;

    @SerializedName("profileTypeID")
    @Expose
    private String profileTypeID;

    @SerializedName("userID")
    @Expose
    private String userID;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}