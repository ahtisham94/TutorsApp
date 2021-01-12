package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationModel {

@SerializedName("userInfo")
@Expose
private UserInfo userInfo;
@SerializedName("educationLevel")
@Expose
private String educationLevel;
@SerializedName("obtainedMarks")
@Expose
private Integer obtainedMarks;
@SerializedName("totalMarks")
@Expose
private Integer totalMarks;
@SerializedName("instituteName")
@Expose
private String instituteName;
@SerializedName("uni_Board")
@Expose
private String uniBoard;
@SerializedName("grade")
@Expose
private String grade;
@SerializedName("degree")
@Expose
private String degree;
@SerializedName("profileID")
@Expose
private Integer profileID;

public UserInfo getUserInfo() {
return userInfo;
}

public void setUserInfo(UserInfo userInfo) {
this.userInfo = userInfo;
}

public String getEducationLevel() {
return educationLevel;
}

public void setEducationLevel(String educationLevel) {
this.educationLevel = educationLevel;
}

public Integer getObtainedMarks() {
return obtainedMarks;
}

public void setObtainedMarks(Integer obtainedMarks) {
this.obtainedMarks = obtainedMarks;
}

public Integer getTotalMarks() {
return totalMarks;
}

public void setTotalMarks(Integer totalMarks) {
this.totalMarks = totalMarks;
}

public String getInstituteName() {
return instituteName;
}

public void setInstituteName(String instituteName) {
this.instituteName = instituteName;
}

public String getUniBoard() {
return uniBoard;
}

public void setUniBoard(String uniBoard) {
this.uniBoard = uniBoard;
}

public String getGrade() {
return grade;
}

public void setGrade(String grade) {
this.grade = grade;
}

public String getDegree() {
return degree;
}

public void setDegree(String degree) {
this.degree = degree;
}

public Integer getProfileID() {
return profileID;
}

public void setProfileID(Integer profileID) {
this.profileID = profileID;
}

}