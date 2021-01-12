package com.example.tutorsapp.models;

import com.google.gson.annotations.SerializedName;

public class AcademyFeatureModel {

    @SerializedName("userInfo")
    private UserInfo userInfo;
    @SerializedName("feature_ClassRoom")
    private String feature_ClassRoom;
    @SerializedName("feature_NumberRoom")
    private String feature_NumberRoom;
    @SerializedName("feature_Faculty")
    private String feature_Faculty;
    @SerializedName("feature_TeacherToStudentRatio")
    private String feature_TeacherToStudentRatio;
    @SerializedName("feature_Laboratories")
    private String feature_Laboratories;
    @SerializedName("feature_Library")
    private String feature_Library;
    @SerializedName("feature_ParkingArea")
    private String feature_ParkingArea;
    @SerializedName("feature_SecurityLevel")
    private String feature_SecurityLevel;
    @SerializedName("feature_Cafeteria")
    private String feature_Cafeteria;
    @SerializedName("feature_Exams_Assessment")
    private String feature_Exams_Assessment;
    @SerializedName("feature_Subject_SpecialFeatures")
    private String feature_Subject_SpecialFeatures;
    @SerializedName("academyID")
    private String academyID;
    @SerializedName("channelID")
    private String channelID;


    public AcademyFeatureModel(String feature_ClassRoom, String feature_NumberRoom, String feature_Faculty, String feature_TeacherToStudentRatio, String feature_Laboratories, String feature_Library, String feature_ParkingArea, String feature_SecurityLevel, String feature_Cafeteria, String feature_Exams_Assessment, String feature_Subject_SpecialFeatures) {
        this.feature_ClassRoom = feature_ClassRoom;
        this.feature_NumberRoom = feature_NumberRoom;
        this.feature_Faculty = feature_Faculty;
        this.feature_TeacherToStudentRatio = feature_TeacherToStudentRatio;
        this.feature_Laboratories = feature_Laboratories;
        this.feature_Library = feature_Library;
        this.feature_ParkingArea = feature_ParkingArea;
        this.feature_SecurityLevel = feature_SecurityLevel;
        this.feature_Cafeteria = feature_Cafeteria;
        this.feature_Exams_Assessment = feature_Exams_Assessment;
        this.feature_Subject_SpecialFeatures = feature_Subject_SpecialFeatures;
    }

    public String getFeature_ClassRoom() {
        return feature_ClassRoom;
    }

    public void setFeature_ClassRoom(String feature_ClassRoom) {
        this.feature_ClassRoom = feature_ClassRoom;
    }

    public String getFeature_NumberRoom() {
        return feature_NumberRoom;
    }

    public void setFeature_NumberRoom(String feature_NumberRoom) {
        this.feature_NumberRoom = feature_NumberRoom;
    }

    public String getFeature_Faculty() {
        return feature_Faculty;
    }

    public void setFeature_Faculty(String feature_Faculty) {
        this.feature_Faculty = feature_Faculty;
    }

    public String getFeature_TeacherToStudentRatio() {
        return feature_TeacherToStudentRatio;
    }

    public void setFeature_TeacherToStudentRatio(String feature_TeacherToStudentRatio) {
        this.feature_TeacherToStudentRatio = feature_TeacherToStudentRatio;
    }

    public String getFeature_Laboratories() {
        return feature_Laboratories;
    }

    public void setFeature_Laboratories(String feature_Laboratories) {
        this.feature_Laboratories = feature_Laboratories;
    }

    public String getFeature_Library() {
        return feature_Library;
    }

    public void setFeature_Library(String feature_Library) {
        this.feature_Library = feature_Library;
    }

    public String getFeature_ParkingArea() {
        return feature_ParkingArea;
    }

    public void setFeature_ParkingArea(String feature_ParkingArea) {
        this.feature_ParkingArea = feature_ParkingArea;
    }

    public String getFeature_SecurityLevel() {
        return feature_SecurityLevel;
    }

    public void setFeature_SecurityLevel(String feature_SecurityLevel) {
        this.feature_SecurityLevel = feature_SecurityLevel;
    }

    public String getFeature_Cafeteria() {
        return feature_Cafeteria;
    }

    public void setFeature_Cafeteria(String feature_Cafeteria) {
        this.feature_Cafeteria = feature_Cafeteria;
    }

    public String getFeature_Exams_Assessment() {
        return feature_Exams_Assessment;
    }

    public void setFeature_Exams_Assessment(String feature_Exams_Assessment) {
        this.feature_Exams_Assessment = feature_Exams_Assessment;
    }

    public String getFeature_Subject_SpecialFeatures() {
        return feature_Subject_SpecialFeatures;
    }

    public void setFeature_Subject_SpecialFeatures(String feature_Subject_SpecialFeatures) {
        this.feature_Subject_SpecialFeatures = feature_Subject_SpecialFeatures;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getAcademyID() {
        return academyID;
    }

    public void setAcademyID(String academyID) {
        this.academyID = academyID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }
}
