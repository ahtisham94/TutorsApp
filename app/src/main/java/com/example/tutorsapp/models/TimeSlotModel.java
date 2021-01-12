package com.example.tutorsapp.models;

public class TimeSlotModel {
    public TimeSlotModel(boolean isSelected, String timeSlot) {
        this.isSelected = isSelected;
        this.timeSlot = timeSlot;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    boolean isSelected = false;
    String timeSlot = "";
}
