package com.example.tutorsapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;
import com.example.tutorsapp.ui.customview.TutorEditText;

public class JobViewDetailsActivity extends BaseActivity {
    Toolbar toolbar;
    GetJobsResponseModel getJobsResponseModel;
    TextView teacherTypeTv, teacherGenderTv, noOfPostTv, instituteNameTv, sportsTv, jobDescTv;
    TutorEditText qualificationEd, experienceEd, classEd, descEd, salaryEd, genderEd, applyBeforeEd,
            cityEd, locationEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view_details);
        initViews();
    }

    private void initViews() {
        if (getIntent().getExtras() != null) {
            getJobsResponseModel = (GetJobsResponseModel) getIntent().getSerializableExtra(Constants.datePassey);
        }
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.whiteColor));
        toolbar.setTitle("Job View Details");
        setTitle("Job View Details");
        teacherTypeTv=findViewById(R.id.teacherTypeTv);
        teacherGenderTv=findViewById(R.id.teacherGenderTv);
        noOfPostTv=findViewById(R.id.noOfPostTv);
        instituteNameTv=findViewById(R.id.instituteNameTv);
        sportsTv=findViewById(R.id.sportsTv);
        jobDescTv=findViewById(R.id.jobDescTv);
        qualificationEd=findViewById(R.id.qualificationEd);
        experienceEd=findViewById(R.id.experienceEd);
        classEd=findViewById(R.id.classEd);
        descEd=findViewById(R.id.descEd);
        salaryEd=findViewById(R.id.salaryEd);
        genderEd=findViewById(R.id.genderEd);
        applyBeforeEd=findViewById(R.id.applyBeforeEd);
        cityEd=findViewById(R.id.cityEd);
        locationEd=findViewById(R.id.locationEd);
        teacherTypeTv.setText(getJobsResponseModel.getJobTitle());
        teacherGenderTv.setText(getJobsResponseModel.getGender());
        noOfPostTv.setText(getJobsResponseModel.getJobPosts());
        instituteNameTv.setText(getJobsResponseModel.getInstituteName());
        sportsTv.setText(getJobsResponseModel.getSubject());
        jobDescTv.setText(getJobsResponseModel.getFunctionalArea());
        qualificationEd.setEditTextValue(getJobsResponseModel.getQulification());
        experienceEd.setEditTextValue(getJobsResponseModel.getExperience());
        classEd.setEditTextValue(getJobsResponseModel.getLevel());
        descEd.setEditTextValue(getJobsResponseModel.getJobDescription());
        salaryEd.setEditTextValue(getJobsResponseModel.getMaxSalary());
        genderEd.setEditTextValue(getJobsResponseModel.getGender());
        applyBeforeEd.setEditTextValue(getJobsResponseModel.getApplyBefore());
        cityEd.setEditTextValue(getJobsResponseModel.getJobCityName());
        locationEd.setEditTextValue(getJobsResponseModel.getJobArea());
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}