package com.example.tutorsapp.ui.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;

import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.AcademyScheduleModel;
import com.example.tutorsapp.ui.AddLocationActivity;
import com.example.tutorsapp.ui.BaseActivity;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.example.tutorsapp.utils.Validations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.example.tutorsapp.helper.Constants.ADD_LOCATION_RESULT;

public class AcademyScheduleDialog extends Dialog implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView startDateTv, endDateTv, startTimeTv, endTimeTv, onlineClassTv, venueTv, uploadTeacherPicTv;
    private Calendar calendar;
    Spinner scheduleSpinner;
    String teachingToolType;
    Button saveBtn;
    EditText leveEd, subjectEd, courseName, teacherNameEd, noClassPerWeekEd, feeEd;
    boolean isTeacherPic = false;

    private AcademyScheduleModel academyScheduleModel;
    private CallbackGen<AcademyScheduleModel> callback;

    public AcademyScheduleDialog(@NonNull Context context, AcademyScheduleModel academyScheduleModel, CallbackGen<AcademyScheduleModel> callback) {
        super(context);
        this.academyScheduleModel = academyScheduleModel;
        this.callback = callback;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.academy_shedule_dialog_layout);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
            lp.height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.70);
            getWindow().setAttributes(lp);
        }
        initViews();
    }

    private void initViews() {
        calendar = Calendar.getInstance();
        startDateTv = findViewById(R.id.startDateTv);
        endDateTv = findViewById(R.id.endDateTv);
        startTimeTv = findViewById(R.id.startTimeTv);
        endTimeTv = findViewById(R.id.endTimeTv);
        startDateTv.setOnClickListener(this);
        endDateTv.setOnClickListener(this);
        startTimeTv.setOnClickListener(this);
        endTimeTv.setOnClickListener(this);
        scheduleSpinner = findViewById(R.id.scheduleSpinner);
        scheduleSpinner.setAdapter(new CustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.scheduleArray)))));
        onlineClassTv = findViewById(R.id.onlineClassTv);
        onlineClassTv.setOnClickListener(this);
        venueTv = findViewById(R.id.venueTv);
        venueTv.setOnClickListener(this);
        uploadTeacherPicTv = findViewById(R.id.uploadTeacherPicTv);
        uploadTeacherPicTv.setOnClickListener(this);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        leveEd = findViewById(R.id.leveEd);
        subjectEd = findViewById(R.id.subjectEd);
        courseName = findViewById(R.id.courseName);
        teacherNameEd = findViewById(R.id.teacherNameEd);
        noClassPerWeekEd = findViewById(R.id.noClassPerWeekEd);
        feeEd = findViewById(R.id.feeEd);

        if (academyScheduleModel != null) {
            if (academyScheduleModel.getAcademyScheduleLevelID() != null) {
                leveEd.setText(academyScheduleModel.getAcademyScheduleLevelID());
                subjectEd.setText(academyScheduleModel.getAcademyScheduleSubject());
                courseName.setText(academyScheduleModel.getAcademyScheduleCourseName());
                onlineClassTv.setText(academyScheduleModel.getTeacherOnlineID());
                teacherNameEd.setText(academyScheduleModel.getTeacherName());
                venueTv.setText(academyScheduleModel.getAcademyScheduleVenue());
                teacherImageUploaded();
                startTimeTv.setText(academyScheduleModel.getStartTime());
                endTimeTv.setText(academyScheduleModel.getEndTime());
                startDateTv.setText(academyScheduleModel.getScheduleStartDate());
                endDateTv.setText(academyScheduleModel.getScheduleEndDate());
                noClassPerWeekEd.setText(academyScheduleModel.getNumberClassesInWeek());
                feeEd.setText(academyScheduleModel.getFees());
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startDateTv:
                openCalender(0);
                break;
            case R.id.endDateTv:
                openCalender(1);
                break;

            case R.id.startTimeTv:
                openTimeDialog(0);
                break;

            case R.id.endTimeTv:
                openTimeDialog(1);
                break;

            case R.id.onlineClassTv:
                showTeacherSocialIdDialog();
                break;

            case R.id.venueTv:
                Intent locationIntent = new Intent(getContext(), AddLocationActivity.class);
                TutorApp.getInstance().getmCurrentActivity().startActivityForResult(locationIntent, ADD_LOCATION_RESULT);
                break;

            case R.id.uploadTeacherPicTv:
                new ImagesPickerDialog(getContext(), (BaseActivity) TutorApp.getInstance().mCurrentActivity).show();
                break;

            case R.id.saveBtn:
                if (validate()) {
                    obtainData();
                    dismiss();
                }
                break;
        }

    }

    public void setVenueTv(Address address) {
        if (address != null) {
            venueTv.setText(address.getAddressLine(0));
        }
    }

    public void teacherImageUploaded() {
        isTeacherPic = true;
        uploadTeacherPicTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
        uploadTeacherPicTv.setText("Teacher Image selected");
    }

    private void showTeacherSocialIdDialog() {
        new TeacherSocialIdDialog(getContext(), new TeacherSocialIdDialog.SocialMediaInformation() {
            @Override
            public void information(String userId, String socialMediaType) {
                teachingToolType = userId + "," + socialMediaType;
                onlineClassTv.setText(userId + " ( " + socialMediaType + " ) ");
            }
        }).show();
    }

    private void openCalender(int val) {    // 0 for start date , 1 for end date
        Calendar startDateCal = Calendar.getInstance();
        Calendar endDateCalender = Calendar.getInstance();

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        // date picker dialog
        DatePickerDialog picker = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (val == 0) {
                            startDateTv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            startDateCal.set(year, monthOfYear, dayOfMonth);
                        } else {
                            endDateTv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            endDateCalender.set(year, monthOfYear, dayOfMonth);
                        }
                    }
                }, year, month, day);
        picker.getDatePicker().setMaxDate(System.currentTimeMillis());
        picker.show();
    }

    private void openTimeDialog(int val) {  // 0 for start time, 1 for end time

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if (val == 0)
                    startTimeTv.setText(selectedHour + ":" + selectedMinute);
                else
                    endTimeTv.setText(selectedHour + ":" + selectedMinute);

            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

    }

    private boolean validate() {
        if (leveEd.getText().toString().isEmpty()) {
            leveEd.setError("Please enter schedule level");
            leveEd.requestFocus();
            return false;
        } else if (subjectEd.getText().toString().isEmpty()) {
            subjectEd.setError("Please enter subject name");
            subjectEd.requestFocus();
            return false;
        } else if (courseName.getText().toString().isEmpty()) {
            courseName.setError("Please enter course name");
            courseName.requestFocus();
            return false;
//        } else if (onlineClassTv.getText().toString().isEmpty()) {
//            Toast.makeText(getContext(), "Please add your social media information", Toast.LENGTH_SHORT).show();
//            return false;
        } else if (venueTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please add course schedule location", Toast.LENGTH_SHORT).show();
            return false;
        } else if (teacherNameEd.getText().toString().isEmpty()) {
            teacherNameEd.setError("Please enter teacher name");
            teacherNameEd.requestFocus();
            return false;
        } else if (!isTeacherPic) {
            Toast.makeText(getContext(), "Please add teacher photo", Toast.LENGTH_SHORT).show();
            return false;
        } else if (startDateTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select start date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (endDateTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select end date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (startTimeTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select start time", Toast.LENGTH_SHORT).show();
            return false;
        } else if (endTimeTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select end time", Toast.LENGTH_SHORT).show();
            return false;
        } else if (noClassPerWeekEd.getText().toString().isEmpty()) {
            noClassPerWeekEd.setError("Please enter no of classes in week");
            noClassPerWeekEd.requestFocus();
            return false;
        } else if (feeEd.getText().toString().isEmpty()) {
            feeEd.setError("Please enter fee per subject");
            feeEd.requestFocus();
            return false;
        } else {
            leveEd.setError(null);
            subjectEd.setError(null);
            courseName.setError(null);
            teacherNameEd.setError(null);
            noClassPerWeekEd.setError(null);
            feeEd.setError(null);
            return true;
        }
    }

    /*<<<<<<< HEAD
        public AcademyScheduleModel getAcademySchedule() {
            boolean isOnlineClass=false;
            if(onlineClassTv.getText().toString()!=null && !onlineClassTv.getText().toString().equalsIgnoreCase("online class"))
                isOnlineClass=true;

            AcademyScheduleModel academyFeatureModel = new AcademyScheduleModel(scheduleSpinner.getSelectedItem().toString(),
                    leveEd.getText().toString(), subjectEd.getText().toString(), courseName.toString(),
                    isOnlineClass, venueTv.getText().toString(), teacherNameEd.getText().toString(), onlineClassTv.getText().toString(), "",
                   "", startDateTv.getText().toString(), endDateTv.getText().toString(),  startTimeTv.getText().toString(), endTimeTv.getText().toString(),
                    noClassPerWeekEd.getText().toString(), feeEd.getText().toString());
            return academyFeatureModel;
    =======*/
    private void obtainData() {
        academyScheduleModel.setAcademyScheduleName((String) scheduleSpinner.getSelectedItem());
        academyScheduleModel.setAcademyScheduleLevelID(leveEd.getText().toString());
        academyScheduleModel.setAcademyScheduleSubject(subjectEd.getText().toString());
        academyScheduleModel.setAcademyScheduleCourseName(courseName.getText().toString());
        academyScheduleModel.setIsOnlineClasses(Validations.isNotEmpty(onlineClassTv.getText()));
        academyScheduleModel.setTeacherOnlineID(onlineClassTv.getText().toString());
        academyScheduleModel.setAcademyScheduleVenue(venueTv.getText().toString());
        academyScheduleModel.setTeacherName(teacherNameEd.getText().toString());
        academyScheduleModel.setScheduleStartDate(startDateTv.getText().toString());
        academyScheduleModel.setScheduleEndDate(endDateTv.getText().toString());
        academyScheduleModel.setStartTime(startTimeTv.getText().toString());
        academyScheduleModel.setEndTime(endTimeTv.getText().toString());
        academyScheduleModel.setNumberClassesInWeek(noClassPerWeekEd.getText().toString());
        academyScheduleModel.setFees(feeEd.getText().toString());
        callback.returnCall(academyScheduleModel);
    }
}
