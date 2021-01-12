package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcademyDetailsModel {

    @SerializedName("academyID")
    @Expose
    private String academyID;
    @SerializedName("academyName")
    @Expose
    private String academyName;
    @SerializedName("academyLogo")
    @Expose
    private String academyLogo;
    @SerializedName("academyPicture")
    @Expose
    private String academyPicture;
    @SerializedName("academyTeachesIDs")
    @Expose
    private String academyTeachesIDs;
    @SerializedName("academyEmail")
    @Expose
    private String academyEmail;
    @SerializedName("primaryPhone")
    @Expose
    private String primaryPhone;
    @SerializedName("secondaryPhone")
    @Expose
    private String secondaryPhone;
    @SerializedName("currentAddress")
    @Expose
    private String currentAddress;
    @SerializedName("ownerID")
    @Expose
    private String ownerID;
    @SerializedName("ownTypeID")
    @Expose
    private String ownTypeID;
    @SerializedName("channelID")
    @Expose
    private String channelID;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("ended")
    @Expose
    private String ended;
    @SerializedName("endedBy")
    @Expose
    private String endedBy;

    public String getAcademyID() {
        return academyID;
    }

    public void setAcademyID(String academyID) {
        this.academyID = academyID;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getAcademyLogo() {
        return academyLogo;
    }

    public void setAcademyLogo(String academyLogo) {
        this.academyLogo = academyLogo;
    }

    public String getAcademyPicture() {
        return academyPicture;
    }

    public void setAcademyPicture(String academyPicture) {
        this.academyPicture = academyPicture;
    }

    public String getAcademyTeachesIDs() {
        return academyTeachesIDs;
    }

    public void setAcademyTeachesIDs(String academyTeachesIDs) {
        this.academyTeachesIDs = academyTeachesIDs;
    }

    public String getAcademyEmail() {
        return academyEmail;
    }

    public void setAcademyEmail(String academyEmail) {
        this.academyEmail = academyEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getOwnTypeID() {
        return ownTypeID;
    }

    public void setOwnTypeID(String ownTypeID) {
        this.ownTypeID = ownTypeID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public String getEndedBy() {
        return endedBy;
    }

    public void setEndedBy(String endedBy) {
        this.endedBy = endedBy;
    }

}
