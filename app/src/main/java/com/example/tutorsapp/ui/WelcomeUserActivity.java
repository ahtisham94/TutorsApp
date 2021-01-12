package com.example.tutorsapp.ui;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tutorsapp.R;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;

public class WelcomeUserActivity extends AppCompatActivity {
    private ImageView searchIv;
    private ImageView logoutIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);
        initView();
    }

    private void initView() {
        searchIv = findViewById(R.id.searchIv);
        logoutIv = findViewById(R.id.logoutIv);

        //adjusting top image of the screen
        ImageView drawerIv = findViewById(R.id.drawerIv);
        ImageView topIv = findViewById(R.id.topIv);
        ClipDrawable mImageDrawable = (ClipDrawable) topIv.getDrawable();
        mImageDrawable.setLevel(10000);
        drawerIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeUserActivity.this.startActivity(new Intent(WelcomeUserActivity.this, DashboardActivity.class));
            }
        });

        CardView teacherCv = findViewById(R.id.teacherCv);
        teacherCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.teacherTye = TeacherTypeEnum.PROFESSIONAL_TEACHER;
                Intent intent = new Intent(WelcomeUserActivity.this, EditProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        CardView quranTeacherCv = findViewById(R.id.quranTeacherCv);
        quranTeacherCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.teacherTye = TeacherTypeEnum.QURAN_TEACHER;
                Intent intent = new Intent(WelcomeUserActivity.this, EditProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        CardView academyInstitutionCv = findViewById(R.id.academyInstitutionCv);
        academyInstitutionCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.teacherTye = TeacherTypeEnum.ACADEMIC_INSTITUTE;
                Intent intent = new Intent(WelcomeUserActivity.this, EditProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        ConstraintLayout bookStoreCV = findViewById(R.id.bookStoreCV);
        bookStoreCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.teacherTye = TeacherTypeEnum.BOOK_STORE;
                Intent intent = new Intent(WelcomeUserActivity.this, EditBookStoreInfoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        CardView academyUniformCv = findViewById(R.id.academyUniformCv);
        academyUniformCv.setOnClickListener(view -> {
//            Constants.teacherTye = TeacherTypeEnum.BOOK_STORE;
//            Intent intent = new Intent(WelcomeUserActivity.this, EditBookStoreInfoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeUserActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        logoutIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeUserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
