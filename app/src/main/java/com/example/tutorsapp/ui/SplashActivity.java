package com.example.tutorsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.FormStatusHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.UserInfo;
import com.google.gson.Gson;

import org.json.JSONObject;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler;
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean firstLaunch = Persister.getFirstLaunch(SplashActivity.this);
                if (firstLaunch) {
                    Persister.setFirstLaunch(SplashActivity.this, false);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    TutorApp.userInfo = Persister.getUser(SplashActivity.this);
                    if (TutorApp.userInfo == null) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        startActivity(FormStatusHelper.getStatusIntent(SplashActivity.this, TutorApp.userInfo).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                    finish();
                }
            }
        }, 3000);
    }
}