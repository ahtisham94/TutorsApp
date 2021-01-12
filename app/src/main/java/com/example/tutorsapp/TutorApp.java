package com.example.tutorsapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CompoundButton;

import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.ClientInstance;
import com.example.tutorsapp.network.GetDataService;
import com.google.gson.Gson;

public class TutorApp extends Application {
    public static GetDataService service;
    public static Context context;
    public Activity mCurrentActivity;
    public static TutorApp instance;
    public static UserInfo userInfo;

    public Activity getmCurrentActivity() {
        return mCurrentActivity;
    }

    public void setmCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        service = ClientInstance.getRetrofitInstance().create(GetDataService.class);
        instance = this;

    }

    public static Context getContext() {
        return context;
    }

    public static synchronized TutorApp getInstance() {
        return instance;
    }


}
