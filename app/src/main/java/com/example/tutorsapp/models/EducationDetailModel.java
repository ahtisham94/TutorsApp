package com.example.tutorsapp.models;

import com.example.tutorsapp.ui.EducationalDetailsActivity;

import java.util.List;

public class EducationDetailModel {
    private String educational;
    private String instituteName;
    private String boardName;
    private String grade;
    private String degreeName;

    public EducationDetailModel(String educational, String instituteName, String boardName, String grade, String degreeName) {
        this.educational = educational;
        this.instituteName = instituteName;
        this.boardName = boardName;
        this.grade = grade;
        this.degreeName = degreeName;
    }

    public EducationDetailModel() {
    }

    public String getEducational() {
        return educational;
    }

    public void setEducation(String educational) {
        this.educational = educational;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }


    public boolean isContains(List<EducationDetailModel> list) {
        for (EducationDetailModel model : list) {
            if (model.getDegreeName().equals(this.degreeName))
                return true;
        }
        return false;
    }
}
