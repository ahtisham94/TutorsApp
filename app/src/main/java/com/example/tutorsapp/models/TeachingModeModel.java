package com.example.tutorsapp.models;

import java.util.List;

public class TeachingModeModel {
    private String teachingMethod;
    private boolean isChecked;

    private String checkboxTile;
    private int id;

    public String getCheckboxTile() {
        return checkboxTile;
    }

    public void setCheckboxTile(String checkboxTile) {
        this.checkboxTile = checkboxTile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeachingModeModel(boolean isChecked, String checkboxTile, int id) {
        this.isChecked = isChecked;
        this.checkboxTile = checkboxTile;
        this.id = id;
    }

    public TeachingModeModel(String teachingMethod, boolean isChecked) {
        this.teachingMethod = teachingMethod;
        this.isChecked = isChecked;
    }

    public TeachingModeModel() {
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isAdded(List<String> list) {
        return list.contains(this);
    }
}
