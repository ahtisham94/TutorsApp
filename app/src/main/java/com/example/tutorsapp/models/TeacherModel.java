package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherModel {

    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("lName")
    @Expose
    private String lName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cityID")
    @Expose
    private Integer cityID;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("currentAddress")
    @Expose
    private String currentAddress;
    @SerializedName("permanentAddress")
    @Expose
    private String permanentAddress;
    @SerializedName("cnicFrontPath")
    @Expose
    private String cnicFrontPath;
    @SerializedName("cnicFront")
    @Expose
    private String cnicFront;
    @SerializedName("cnicBackPath")
    @Expose
    private String cnicBackPath;
    @SerializedName("cnicBack")
    @Expose
    private String cnicBack;
    @SerializedName("teacherType")
    @Expose
    private Integer teacherType;
    @SerializedName("profilePicPath")
    @Expose
    private String profilePicPath;
    @SerializedName("profilePic")
    @Expose
    private String profilePic;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCnicFrontPath() {
        return cnicFrontPath;
    }

    public void setCnicFrontPath(String cnicFrontPath) {
        this.cnicFrontPath = cnicFrontPath;
    }

    public String getCnicFront() {
        return cnicFront;
    }

    public void setCnicFront(String cnicFront) {
        this.cnicFront = cnicFront;
    }

    public String getCnicBackPath() {
        return cnicBackPath;
    }

    public void setCnicBackPath(String cnicBackPath) {
        this.cnicBackPath = cnicBackPath;
    }

    public String getCnicBack() {
        return cnicBack;
    }

    public void setCnicBack(String cnicBack) {
        this.cnicBack = cnicBack;
    }

    public Integer getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(Integer teacherType) {
        this.teacherType = teacherType;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}