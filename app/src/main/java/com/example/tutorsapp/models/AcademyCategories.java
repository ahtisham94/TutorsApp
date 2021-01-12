package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AcademyCategories {

    @SerializedName("academyCafeteriaType")
    @Expose
    private ArrayList<LOVResponseModel> academyCafeteriaType;

    @SerializedName("academyClassRoomTypes")
    @Expose
    private ArrayList<LOVResponseModel> academyClassRoomTypes;

    @SerializedName("academyExamAssessmentType")
    @Expose
    private ArrayList<LOVResponseModel> academyExamAssessmentType;

    @SerializedName("academyFacultyType")
    @Expose
    private ArrayList<LOVResponseModel> academyFacultyType;

    @SerializedName("academyLibraryType")
    @Expose
    private ArrayList<LOVResponseModel> academyLibraryType;

    @SerializedName("academyLaboratoriesType")
    @Expose
    private ArrayList<LOVResponseModel> academyLaboratoriesType;

    @SerializedName("academyTeacherToStudentRatio")
    @Expose
    private ArrayList<LOVResponseModel> academyTeacherToStudentRatio;

    @SerializedName("academyParkingArea")
    @Expose
    private ArrayList<LOVResponseModel> academyParkingArea;

    @SerializedName("academySecurityAreaType")
    @Expose
    private ArrayList<LOVResponseModel> academySecurityAreaType;

    @SerializedName("academySpecialFeature")
    @Expose
    private ArrayList<LOVResponseModel> academySpecialFeature;

    public ArrayList<LOVResponseModel> getAcademyCafeteriaType() {
        return academyCafeteriaType;
    }

    public void setAcademyCafeteriaType(ArrayList<LOVResponseModel> acedamyCafeteriaType) {
        this.academyCafeteriaType = academyCafeteriaType;
    }

    public ArrayList<LOVResponseModel> getAcademyClassRoomTypes() {
        return academyClassRoomTypes;
    }

    public void setAcademyClassRoomTypes(ArrayList<LOVResponseModel> academyClassRoomTypes) {
        this.academyClassRoomTypes = academyClassRoomTypes;
    }

    public ArrayList<LOVResponseModel> getAcademyExamAssessmentType() {
        return academyExamAssessmentType;
    }

    public void setAcademyExamAssessmentType(ArrayList<LOVResponseModel> academyExamAssessmentType) {
        this.academyExamAssessmentType = academyExamAssessmentType;
    }

    public ArrayList<LOVResponseModel> getAcademyFacultyType() {
        return academyFacultyType;
    }

    public void setAcademyFacultyType(ArrayList<LOVResponseModel> academyFacultyType) {
        this.academyFacultyType = academyFacultyType;
    }

    public ArrayList<LOVResponseModel> getAcademyLibraryType() {
        return academyLibraryType;
    }

    public void setAcademyLibraryType(ArrayList<LOVResponseModel> academyLibraryType) {
        this.academyLibraryType = academyLibraryType;
    }

    public ArrayList<LOVResponseModel> getAcademyLaboratoriesType() {
        return academyLaboratoriesType;
    }

    public void setAcademyLaboratoriesType(ArrayList<LOVResponseModel> academyLaboratoriesType) {
        this.academyLaboratoriesType = academyLaboratoriesType;
    }

    public ArrayList<LOVResponseModel> getAcademyTeacherToStudentRatio() {
        return academyTeacherToStudentRatio;
    }

    public void setAcademyTeacherToStudentRatio(ArrayList<LOVResponseModel> academyTeacherToStudentRatio) {
        this.academyTeacherToStudentRatio = academyTeacherToStudentRatio;
    }

    public ArrayList<LOVResponseModel> getAcademyParkingArea() {
        return academyParkingArea;
    }

    public void setAcademyParkingArea(ArrayList<LOVResponseModel> academyParkingArea) {
        this.academyParkingArea = academyParkingArea;
    }

    public ArrayList<LOVResponseModel> getAcademySecurityAreaType() {
        return academySecurityAreaType;
    }

    public void setAcademySecurityAreaType(ArrayList<LOVResponseModel> academySecurityAreaType) {
        this.academySecurityAreaType = academySecurityAreaType;
    }

    public ArrayList<LOVResponseModel> getAcademySpecialFeature() {
        return academySpecialFeature;
    }

    public void setAcademySpecialFeature(ArrayList<LOVResponseModel> academySpecialFeature) {
        this.academySpecialFeature = academySpecialFeature;
    }
}
