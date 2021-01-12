package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tutorsapp.R;

public class RegistrationFeeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_fee);
        initView();
    }

    private void initView() {
        Button proceesBtn = findViewById(R.id.proceedBtn);
        proceesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationFeeActivity.this, TeacherRegisterationCode.class);
                startActivity(intent);
                //back button
                ImageView backIv = findViewById(R.id.backIv);
                backIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RegistrationFeeActivity.super.onBackPressed();
                    }
                });
            }
        });

    }
}