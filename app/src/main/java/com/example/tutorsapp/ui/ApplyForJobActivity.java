package com.example.tutorsapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.ui.customview.TutorCustomInputList;
import com.example.tutorsapp.ui.customview.TutorEditText;
import com.example.tutorsapp.ui.customview.TutorSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ApplyForJobActivity extends BaseActivity implements View.OnClickListener, TutorEditText.TutorClick {
    TutorSpinner experienceSpinner, salarySpinner;
    TutorEditText dobEd, fullNameEd, cnicEd, currentAddressEd, permanentAddressEd, emailAddressEd,
            contactNumEd;
    TextView experienceStartDateTv, experienceEndDateTv;
    Toolbar toolbar;
    Button applyNowBtn, cancelBtn;
    TutorCustomInputList qualificationList, experienceFlexList, coreWorkingsSkillFlexList, languagesFlexList,
            computerProfiencyFlexList;
    EditText schoolNametv, designationEd, gradeClassED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_job);
        initViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.whiteColor));
        toolbar.setTitle("Apply For Job");
        setTitle("Apply For Job");
        experienceSpinner = findViewById(R.id.experienceSpinner);
        salarySpinner = findViewById(R.id.salarySpinner);
        dobEd = findViewById(R.id.dobEd);
        dobEd.setClickListenser(this);
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("Beginner");
        arrayList.add("Experienced (3 to 5 Years)");
        arrayList.add("Professional Level (6 to above Years)");
        ArrayList<String> salarayArray = new ArrayList();
        salarayArray.add("10,000 to 20,000");
        salarayArray.add("20,000 to 30,000");
        salarayArray.add("30,000 to 40,000");
        salarayArray.add("40,000 to 50,000");
        experienceSpinner.setSpinnerArray(arrayList);
        salarySpinner.setSpinnerArray(salarayArray);
        experienceStartDateTv = findViewById(R.id.experienceStartDateTv);
        experienceStartDateTv.setOnClickListener(this);
        experienceEndDateTv = findViewById(R.id.experienceEndDateTv);
        experienceEndDateTv.setOnClickListener(this);
        applyNowBtn = findViewById(R.id.applyNowBtn);
        applyNowBtn.setOnClickListener(this);
        cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        fullNameEd = findViewById(R.id.fullNameEd);
        qualificationList = findViewById(R.id.qualificationList);
        schoolNametv = findViewById(R.id.schoolNametv);
        designationEd = findViewById(R.id.designationEd);
        gradeClassED = findViewById(R.id.gradeClassED);
        experienceFlexList = findViewById(R.id.experienceFlexList);
        cnicEd = findViewById(R.id.cnicEd);
        coreWorkingsSkillFlexList = findViewById(R.id.coreWorkingsSkillFlexList);
        currentAddressEd = findViewById(R.id.currentAddressEd);
        permanentAddressEd = findViewById(R.id.permanentAddressEd);
        emailAddressEd = findViewById(R.id.emailAddressEd);
        contactNumEd = findViewById(R.id.contactNumEd);
        languagesFlexList = findViewById(R.id.languagesFlexList);
        computerProfiencyFlexList = findViewById(R.id.computerProfiencyFlexList);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.experienceStartDateTv) {
            setExperienceStartDate();
        } else if (view.getId() == R.id.experienceEndDateTv) {
            setExperienceEndDate();
        } else if (view.getId() == R.id.applyNowBtn) {
            applyForJob();
        } else if (view.getId() == R.id.cancelBtn) {
            finish();
        }
    }

    private void applyForJob() {
        if (vaildate()) {

        }
    }

    private boolean vaildate() {
        if (fullNameEd.getText().isEmpty()) {
            fullNameEd.setError("Name must be entered");
            return false;
        }else if(schoolNametv.getText().toString().isEmpty()){
            schoolNametv.setError("School/Institute Name must be entered");
        }
        return true;
    }

    private void setExperienceEndDate() {
        DatePickerDialog dobDate = new DatePickerDialog(ApplyForJobActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());
                experienceEndDateTv.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dobDate.getDatePicker().setMaxDate(System.currentTimeMillis());
        dobDate.show();
    }

    private void setExperienceStartDate() {
        DatePickerDialog dobDate = new DatePickerDialog(ApplyForJobActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());
                experienceStartDateTv.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dobDate.getDatePicker().setMaxDate(System.currentTimeMillis());
        dobDate.show();
    }

    private void setDOB() {
        DatePickerDialog dobDate = new DatePickerDialog(ApplyForJobActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());
                dobEd.setEditTextValue(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dobDate.getDatePicker().setMaxDate(System.currentTimeMillis());
        dobDate.show();
    }

    final Calendar newCalendar = Calendar.getInstance();

    @Override
    public void onTutorClick(TutorEditText tutorEditText) {
        if (tutorEditText.getId() == R.id.dobEd)
            setDOB();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}