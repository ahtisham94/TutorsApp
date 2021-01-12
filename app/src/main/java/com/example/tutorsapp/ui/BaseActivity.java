package com.example.tutorsapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.models.UserInfo;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {
    TutorApp tutorApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutorApp = (TutorApp) getApplication();
    }

    protected void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

  /*  protected UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo("","", "hhj");
        userInfo.setSessionId("4f0c3be9-038c-cb73-27e0-33e374a8b3a7");
        userInfo.setMsisdn("923155707620");
        return userInfo;
    }*/

    protected void showToastBar(String msg, Activity activity) {
//        Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();

        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tutorApp.setmCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        tutorApp.setmCurrentActivity(this);
    }
    protected void redirectToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
