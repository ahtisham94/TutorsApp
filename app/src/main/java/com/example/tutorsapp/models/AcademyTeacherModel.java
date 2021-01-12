package com.example.tutorsapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class AcademyTeacherModel {

    @SerializedName("MSISDN")
    private String msisdn;
    @SerializedName("SessionId")
    private String sessionId;
    @SerializedName("AcademyID")
    private String academyID;
    @SerializedName("TeacherName")
    private String teacherName;
    @SerializedName("Qualification")
    private String qualification;
    @SerializedName("TeacherSubject")
    private String teacherSubject;
    @SerializedName("TeacherPicPath")
    private String teacherPicPath;
    @SerializedName("TeacherPic")
    private File teacherPic;
    @SerializedName("Achievements")
    private String achievements;
    @SerializedName("ChannelID")
    private String channelID;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
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

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }
}
