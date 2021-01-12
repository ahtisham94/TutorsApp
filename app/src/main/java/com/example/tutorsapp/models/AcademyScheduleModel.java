package com.example.tutorsapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class AcademyScheduleModel {

    @SerializedName("MSISDN")
    private String msisdn;
    @SerializedName("SessionId")
    private String sessionId;
    @SerializedName("AcademyID")
    private String academyID;
    @SerializedName("AcademyScheduleName")
    private String academyScheduleName;
    @SerializedName("AcademyScheduleLevelID")
    private String academyScheduleLevelID;
    @SerializedName("AcademyScheduleSubject")
    private String academyScheduleSubject;
    @SerializedName("AcademyScheduleCourseName")
    private String academyScheduleCourseName;
    @SerializedName("IsOnlineClasses")
    private Boolean isOnlineClasses;
    @SerializedName("AcademyScheduleVenue")
    private String academyScheduleVenue;
    @SerializedName("TeacherName")
    private String teacherName;
    @SerializedName("TeacherOnlineID")
    private String teacherOnlineID;
    @SerializedName("TeacherPicPath")
    private String teacherPicPath;
    @SerializedName("TeacherPic")
    private File teacherPic;
    @SerializedName("ScheduleStartDate")
    private String scheduleStartDate;
    @SerializedName("ScheduleEndDate")
    private String scheduleEndDate;
    @SerializedName("StartTime")
    private String startTime;
    @SerializedName("EndTime")
    private String endTime;
    @SerializedName("NumberClassesInWeek")
    private String numberClassesInWeek;
    @SerializedName("Fees")
    private String fees;
    @SerializedName("ChannelID")
    private String channelID;

    public File getTeacherPicFile() {
        return teacherPicFile;
    }

    public void setTeacherPicFile(File teacherPicFile) {
        this.teacherPicFile = teacherPicFile;
    }

    private File teacherPicFile;


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

    public String getAcademyID() {
        return academyID;
    }

    public void setAcademyID(String academyID) {
        this.academyID = academyID;
    }

    public String getAcademyScheduleName() {
        return academyScheduleName;
    }

    public void setAcademyScheduleName(String academyScheduleName) {
        this.academyScheduleName = academyScheduleName;
    }

    public String getAcademyScheduleLevelID() {
        return academyScheduleLevelID;
    }

    public void setAcademyScheduleLevelID(String academyScheduleLevelID) {
        this.academyScheduleLevelID = academyScheduleLevelID;
    }

    public String getAcademyScheduleSubject() {
        return academyScheduleSubject;
    }

    public void setAcademyScheduleSubject(String academyScheduleSubject) {
        this.academyScheduleSubject = academyScheduleSubject;
    }

    public String getAcademyScheduleCourseName() {
        return academyScheduleCourseName;
    }

    public void setAcademyScheduleCourseName(String academyScheduleCourseName) {
        this.academyScheduleCourseName = academyScheduleCourseName;
    }

    public Boolean isOnlineClasses() {
        return isOnlineClasses;
    }

    public void setIsOnlineClasses(Boolean isOnlineClasses) {
        this.isOnlineClasses = isOnlineClasses;
    }

    public String getAcademyScheduleVenue() {
        return academyScheduleVenue;
    }

    public void setAcademyScheduleVenue(String academyScheduleVenue) {
        this.academyScheduleVenue = academyScheduleVenue;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherOnlineID() {
        return teacherOnlineID;
    }

    public void setTeacherOnlineID(String teacherOnlineID) {
        this.teacherOnlineID = teacherOnlineID;
    }

    public String getTeacherPicPath() {
        return teacherPicPath;
    }

    public void setTeacherPicPath(String teacherPicPath) {
        this.teacherPicPath = teacherPicPath;
    }

    public File getTeacherPic() {
        return teacherPic;
    }

    public void setTeacherPic(File teacherPic) {
        this.teacherPic = teacherPic;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public String getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(String scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNumberClassesInWeek() {
        return numberClassesInWeek;
    }

    public void setNumberClassesInWeek(String numberClassesInWeek) {
        this.numberClassesInWeek = numberClassesInWeek;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }
}
