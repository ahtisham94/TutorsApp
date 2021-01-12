package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentDetails {

    @SerializedName("requestFormID")
    @Expose
    private Integer requestFormID;
    @SerializedName("studentID")
    @Expose
    private Integer studentID;
    @SerializedName("lat_Long")
    @Expose
    private String latLong;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("cityID")
    @Expose
    private Integer cityID;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("numberOfDaysInWeek")
    @Expose
    private String numberOfDaysInWeek;
    @SerializedName("teachingTime")
    @Expose
    private String teachingTime;
    @SerializedName("monthTeachingRequired")
    @Expose
    private Integer monthTeachingRequired;
    @SerializedName("minFee")
    @Expose
    private Integer minFee;
    @SerializedName("maxFee")
    @Expose
    private Integer maxFee;
    @SerializedName("teachingMode")
    @Expose
    private String teachingMode;
    @SerializedName("teacherRequireFor")
    @Expose
    private Integer teacherRequireFor;
    @SerializedName("numberOfStudent")
    @Expose
    private Integer numberOfStudent;
    @SerializedName("levelSubjectID")
    @Expose
    private Integer levelSubjectID;

    public Integer getRequestFormID() {
        return requestFormID;
    }

    public void setRequestFormID(Integer requestFormID) {
        this.requestFormID = requestFormID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberOfDaysInWeek() {
        return numberOfDaysInWeek;
    }

    public void setNumberOfDaysInWeek(String numberOfDaysInWeek) {
        this.numberOfDaysInWeek = numberOfDaysInWeek;
    }

    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }

    public Integer getMonthTeachingRequired() {
        return monthTeachingRequired;
    }

    public void setMonthTeachingRequired(Integer monthTeachingRequired) {
        this.monthTeachingRequired = monthTeachingRequired;
    }

    public Integer getMinFee() {
        return minFee;
    }

    public void setMinFee(Integer minFee) {
        this.minFee = minFee;
    }

    public Integer getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(Integer maxFee) {
        this.maxFee = maxFee;
    }

    public String getTeachingMode() {
        return teachingMode;
    }

    public void setTeachingMode(String teachingMode) {
        this.teachingMode = teachingMode;
    }

    public Integer getTeacherRequireFor() {
        return teacherRequireFor;
    }

    public void setTeacherRequireFor(Integer teacherRequireFor) {
        this.teacherRequireFor = teacherRequireFor;
    }

    public Integer getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Integer getLevelSubjectID() {
        return levelSubjectID;
    }

    public void setLevelSubjectID(Integer levelSubjectID) {
        this.levelSubjectID = levelSubjectID;
    }

}