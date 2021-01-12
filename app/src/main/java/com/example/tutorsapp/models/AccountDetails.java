package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDetails {

    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;
    @SerializedName("accountTitle")
    @Expose
    private String accountTitle;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("branchCode")
    @Expose
    private String branchCode;
    @SerializedName("accounType")
    @Expose
    private String accounType;
    @SerializedName("cityID")
    @Expose
    private Integer cityID;
    @SerializedName("bankID")
    @Expose
    private Integer bankID;
    @SerializedName("profileID")
    @Expose
    private Integer profileID;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getBankID() {
        return bankID;
    }

    public void setBankID(Integer bankID) {
        this.bankID = bankID;
    }

    public Integer getProfileID() {
        return profileID;
    }

    public void setProfileID(Integer profileID) {
        this.profileID = profileID;
    }


    public String getAccounType() {
        return accounType;
    }

    public void setAccounType(String accounType) {
        this.accounType = accounType;
    }



}