package com.example.tutorsapp.models;

public class CancelledAssignmentModel {
    private String labelText;
    private String date;
    private String cancelledBy;
    private boolean isExpanded;

    public CancelledAssignmentModel(String labelText, String date, String cancelledBy) {
        this.labelText = labelText;
        this.date = date;
        this.cancelledBy = cancelledBy;
        this.isExpanded = false;
    }

    public String getDate() {
        return date;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
