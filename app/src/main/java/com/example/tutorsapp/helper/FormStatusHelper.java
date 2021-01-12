package com.example.tutorsapp.helper;

import android.content.Intent;

import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.ui.AccountDetailsActivity;
import com.example.tutorsapp.ui.AvailabilityStatusActivity;
import com.example.tutorsapp.ui.BaseActivity;
import com.example.tutorsapp.ui.DashboardActivity;
import com.example.tutorsapp.ui.EditProfileActivity;
import com.example.tutorsapp.ui.EducationalDetailsActivity;
import com.example.tutorsapp.ui.PreferredAreaToTeachActivity;
import com.example.tutorsapp.ui.WelcomeUserActivity;
import com.example.tutorsapp.utils.Validations;

public class FormStatusHelper {

    public static Intent getStatusIntent(BaseActivity baseActivity, UserInfo userInfo) {
        if(userInfo.getProfileTypeID().equals("0")) {
            return getTeacherIntent(baseActivity, userInfo.getProfileStatus());
        } else {
            return getQuranTeacherIntent(baseActivity, userInfo.getProfileStatus());
        }
    }

    public static Intent getTeacherIntent(BaseActivity baseActivity, String profileStatus){
        Intent intent;
        if(Validations.isEmpty(profileStatus) || profileStatus.equals("0")) {
            intent = new Intent(baseActivity, WelcomeUserActivity.class);
        } else if(profileStatus.equals("1")) {
            intent = new Intent(baseActivity, EducationalDetailsActivity.class);
        } else if(profileStatus.equals("2")) {
            intent = new Intent(baseActivity, AccountDetailsActivity.class);
        } else if(profileStatus.equals("3")) {
            intent = new Intent(baseActivity, AvailabilityStatusActivity.class);
        } else if(profileStatus.equals("4")) {
            intent = new Intent(baseActivity, PreferredAreaToTeachActivity.class);
        } else if(profileStatus.equals("5")) {
            intent = new Intent(baseActivity, DashboardActivity.class);
        } else {
            intent = new Intent(baseActivity, EditProfileActivity.class);
        }
        return intent;
    }

    public static Intent getQuranTeacherIntent(BaseActivity baseActivity, String profileStatus){
        Intent intent;
        if(Validations.isEmpty(profileStatus) || profileStatus.equals("0")){
            intent = new Intent(baseActivity, EditProfileActivity.class);
        } else if(profileStatus.equals("1")) {
            intent = new Intent(baseActivity, EducationalDetailsActivity.class);
        } else if(profileStatus.equals("2")) {
            intent = new Intent(baseActivity, AccountDetailsActivity.class);
        } else if(profileStatus.equals("3")) {
            intent = new Intent(baseActivity, AvailabilityStatusActivity.class);
        } else if(profileStatus.equals("4")) {
            intent = new Intent(baseActivity, PreferredAreaToTeachActivity.class);
        } else if(profileStatus.equals("5")) {
            intent = new Intent(baseActivity, DashboardActivity.class);
        } else {
            intent = new Intent(baseActivity, EditProfileActivity.class);
        }
        return intent;
    }

}
