package com.example.tutorsapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.utils.Validations;
import com.google.gson.Gson;

public class Persister {

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Constants.dataPrefsKey, Context.MODE_PRIVATE);
    }

    public static void logout(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.clear();
        editor.apply();
        setFirstLaunch(context, false);
    }

    public static void setUser(Context context, String json) {
        getPreferences(context).edit().putString(Constants.userLoginKey, json).apply();
    }

    public static void setUser(Context context, UserInfo userInfo) {
        Gson gson = new Gson();
        String json = gson.toJson(userInfo);
        getPreferences(context).edit().putString(Constants.userLoginKey, json).apply();
    }

    public static UserInfo getUser(Context context) {

        String userString =  getPreferences(context).getString(Constants.userLoginKey, null);
        if(Validations.isNotEmpty(userString)) {
            return new Gson().fromJson(userString, UserInfo.class);
        } else {
            return null;
        }
    }

    public static void setFirstLaunch(Context context, boolean isFirst) {
        getPreferences(context).edit().putBoolean(Constants.firstLaunch, isFirst).apply();
    }

    public static boolean getFirstLaunch(Context context) {
        return getPreferences(context).getBoolean(Constants.firstLaunch, true);
    }


    static public void setTeacherLevelAndSubjects(Context c, Object model, String key) {
        SharedPreferences.Editor editor = getPreferences(c).edit();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(model);
        editor.putString(key, jsonObject);
        editor.commit();

    }
}
