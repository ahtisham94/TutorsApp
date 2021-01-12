package com.example.tutorsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LOVCategoryResponseModel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LOVResponseModel> getCatList() {
        return catList;
    }

    public void setCatList(ArrayList<LOVResponseModel> data) {
        this.catList = data;
    }

    @SerializedName("catList")
    @Expose
    private ArrayList<LOVResponseModel> catList;

}
