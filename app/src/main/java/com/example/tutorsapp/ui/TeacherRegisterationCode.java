package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tutorsapp.R;

public class TeacherRegisterationCode extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registeration_code);
        initView();
    }

    private void initView() {
        Button proceesBtn = findViewById(R.id.proceedBtn);
        proceesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherRegisterationCode.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        //back button
        ImageView backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeacherRegisterationCode.super.onBackPressed();
            }
        });
    }
}