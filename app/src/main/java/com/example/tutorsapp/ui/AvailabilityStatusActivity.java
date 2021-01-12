package com.example.tutorsapp.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.core.content.ContextCompat;

import retrofit2.Response;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.AddTeacherAvailiblityModelRequest;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.TimeSlotModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.example.tutorsapp.ui.dialogs.TimeSlotDialog;
import com.google.android.flexbox.FlexboxLayout;
import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AvailabilityStatusActivity extends BaseActivity implements View.OnClickListener {
    private TextView fromTv;
    private TextView toTv, selectTimeSlotTv, availabilityInWeekTv, feePerHourtv, feePerMonthTv;
    private Spinner currentEmploymentSp;
    private Button saveChangesBtn;
    private FlexboxLayout flexboxLayout, daysSlotFL;
    private CustomSpinnerAdapter currentEmployAdapter;
    private ImageView backIv;
    private TickSeekBar perHourSeekBar, perMonthlySeekbar;
    ArrayList<TimeSlotModel> daysSlotArray, timeSlotArray;
    private int viewIds = 1, profileId = 0;
    private RadioGroup fullTimePartTimeRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_status);
        initView();
        if (getIntent().getExtras() != null) {
            profileId = getIntent().getIntExtra("profileId", 0);
        }
    }

    private void initView() {
        fullTimePartTimeRg = findViewById(R.id.fullTimePartTimeRg);
        feePerHourtv = findViewById(R.id.perHourFeeTv);
        feePerMonthTv = findViewById(R.id.perMonthFeeTv);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(this);
        currentEmploymentSp = findViewById(R.id.currentEmploymentSp);
        flexboxLayout = findViewById(R.id.timeSlotLL);
        daysSlotFL = findViewById(R.id.daysSlotFL);
        selectTimeSlotTv = findViewById(R.id.selectTimeSlotTv);
        selectTimeSlotTv.setOnClickListener(this);
        availabilityInWeekTv = findViewById(R.id.availabilityInWeekTv);
        availabilityInWeekTv.setOnClickListener(this);
        currentEmployAdapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.current_employment_spinner_items))));
        currentEmploymentSp.setAdapter(currentEmployAdapter);
        backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(this);
        perHourSeekBar = findViewById(R.id.perHourFeeRb);
        perMonthlySeekbar = findViewById(R.id.perMonthFeeRb);
        daysSlotArray = new ArrayList<>();
        timeSlotArray = new ArrayList<>();
        perMonthlySeekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                feePerMonthTv.setText("Rs." + seekParams.progress);
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });

        perHourSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                feePerHourtv.setText("Rs." + seekParams.progress + "");

            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });

    }

    public void showHourPicker(final int val) {
        final Calendar myCalender = Calendar.getInstance();
        final int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        final int minute = myCalender.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);
                    String time = "am";
                    if (hourOfDay > 12) {  // converting in 12 hour format
                        hourOfDay = hourOfDay - 12;
                        time = "pm";
                    }
                    if (val == 0)
                        if (minute < 10)
                            fromTv.setText("" + hourOfDay + " : " + "0" + minute + " " + time);
                        else
                            fromTv.setText("" + hourOfDay + " : " + "" + minute + " " + time);
                    if (val == 1)
                        if (minute < 10)
                            toTv.setText("" + hourOfDay + " : " + "0" + minute + " " + time);
                        else
                            toTv.setText("" + hourOfDay + " : " + "" + minute + " " + time);

                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, false);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveChangesBtn:
                if (validateInput()) {
                    addAvailiblityStatusToServer();

                }
                break;
            case R.id.selectTimeSlotTv:
                openTimeSlotDialog();
                break;
            case R.id.availabilityInWeekTv:
                openDaysSlotsDialog();
                break;
            case R.id.backIv:
                super.onBackPressed();
                break;

        }
    }

    private void openDaysSlotsDialog() {
        new TimeSlotDialog(this, getResources().getStringArray(R.array.days_arr), "Select your available day", new TimeSlotDialog.TimeSlot() {
            @Override
            public void timeSlot(ArrayList<TimeSlotModel> timeSlotModels) {
                daysSlotArray = timeSlotModels;
                if (timeSlotModels != null && timeSlotModels.size() > 0) {

                    daysSlotFL.setVisibility(View.VISIBLE);
                    daysSlotFL.removeAllViews();
                    for (TimeSlotModel model : timeSlotModels) {
                        RelativeLayout timeSlotLayout = slotRelativeLayout();
                        TextView timeSlotTv = getSlotTv(model.getTimeSlot());
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) timeSlotTv.getLayoutParams();
                        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, timeSlotLayout.getId());
                        ImageView cancelImage = getCrossImg();
                        params.addRule(RelativeLayout.START_OF, cancelImage.getId());
                        timeSlotTv.setLayoutParams(params);
                        timeSlotLayout.addView(timeSlotTv);
                        timeSlotLayout.addView(cancelImage);
                        daysSlotFL.addView(timeSlotLayout);
                        cancelImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                daysSlotFL.removeView(timeSlotLayout);
                                daysSlotArray.remove(model);
                                if (daysSlotArray.size() == 0)
                                    daysSlotFL.setVisibility(View.GONE);

                            }
                        });


                    }
                }
            }
        }).show();

    }

    private void openTimeSlotDialog() {
        new TimeSlotDialog(this, getResources().getStringArray(R.array.time_slot), "Select your available time", new TimeSlotDialog.TimeSlot() {
            @Override
            public void timeSlot(ArrayList<TimeSlotModel> timeSlotModels) {
                timeSlotArray = timeSlotModels;
                if (timeSlotModels != null && timeSlotModels.size() > 0) {

                    flexboxLayout.setVisibility(View.VISIBLE);
                    flexboxLayout.removeAllViews();
                    for (TimeSlotModel model : timeSlotModels) {
                        RelativeLayout timeSlotLayout = slotRelativeLayout();
                        TextView timeSlotTv = getSlotTv(model.getTimeSlot());
                        ImageView cancelImage = getCrossImg();
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) timeSlotTv.getLayoutParams();
                        params.addRule(RelativeLayout.START_OF, cancelImage.getId());
                        timeSlotTv.setLayoutParams(params);
                        timeSlotLayout.addView(timeSlotTv);
                        timeSlotLayout.addView(cancelImage);
                        flexboxLayout.addView(timeSlotLayout);
                        cancelImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flexboxLayout.removeView(timeSlotLayout);
                                timeSlotArray.remove(model);
                                if (timeSlotArray.size() == 0)
                                    flexboxLayout.setVisibility(View.GONE);

                            }
                        });


                    }
                }
            }
        }).show();
    }


    private TextView getSlotTv(String name) {
        TextView textView = new TextView(AvailabilityStatusActivity.this);
        textView.setId(viewIds);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(getResources().getDimensionPixelSize(R.dimen._15sdp), 0, 0, 0);
        textView.setText(name);
        textView.setTextColor(ContextCompat.getColor(AvailabilityStatusActivity.this, R.color.colorPrimary));
        viewIds++;
        return textView;
    }

    private RelativeLayout slotRelativeLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(AvailabilityStatusActivity.this);
        relativeLayout.setId(viewIds);
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen._120sdp), RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setBackground(getResources().getDrawable(R.drawable.holo_brwn_circle));
        relativeParams.setMargins(getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp));
        relativeLayout.setLayoutParams(relativeParams);
        viewIds++;
        return relativeLayout;
    }

    private ImageView getCrossImg() {
        ImageView imageView = new ImageView(AvailabilityStatusActivity.this);
        imageView.setId(viewIds);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        params1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        params1.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen._10sdp), 0);
//                        params.addRule(RelativeLayout.START_OF,cancelImage.getId());
        imageView.setBackground(getResources().getDrawable(R.drawable.ic_close));
        imageView.setLayoutParams(params1);
        viewIds++;
        return imageView;
    }

    private boolean validateInput() {
        if (timeSlotArray.size() == 0) {
            showToastBar("Please add atleast one time for your availability", this);
            return false;
        } else if (daysSlotArray.size() == 0) {
            showToastBar("Please add atlest one day when you are available", this);
            return false;
        } else if (feePerHourtv.getText().toString().isEmpty()) {
            showToastBar("Please add your fee per hour", this);
            return false;
        } else if (feePerMonthTv.getText().toString().isEmpty()) {
            showToastBar("Please add your fee per month", this);
            return false;
        }
        return true;
    }


    private void addAvailiblityStatusToServer() {
        StringBuilder daysBuilder = new StringBuilder();
        StringBuilder timesBuilder = new StringBuilder();
        AddTeacherAvailiblityModelRequest request = new AddTeacherAvailiblityModelRequest();
        for (TimeSlotModel days : daysSlotArray) {
            daysBuilder.append(days.getTimeSlot());
            daysBuilder.append(",");
        }
        request.setAvailabilityDays(daysBuilder.toString().substring(0, daysBuilder.lastIndexOf(",")));
        for (TimeSlotModel time : timeSlotArray) {
            timesBuilder.append(time.getTimeSlot());
            timesBuilder.append(",");
        }
        request.setAvailableTimeSlots(timesBuilder.toString().substring(0, timesBuilder.lastIndexOf(",")));
        request.setAvailabilityTime(fullTimePartTimeRg.getCheckedRadioButtonId() == R.id.partTimeRb ? TeacherTypeEnum.PART_TIME_AVAILIBILITY.teacherType.type :
                TeacherTypeEnum.FULL_TIME_AVAILIBILITY.teacherType.type);
        request.setCurrentEmployment((String) currentEmploymentSp.getSelectedItem());
        request.setPerHourFee(perHourSeekBar.getProgress());
        request.setPerMonthFee(perMonthlySeekbar.getProgress());
        request.setProfileID(profileId);
        APIManager.getInstance().addTeacherAvailability(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    TutorApp.userInfo.setProfileStatus("4");
                    Persister.setUser(AvailabilityStatusActivity.this, TutorApp.userInfo);
                    Intent intent = new Intent(AvailabilityStatusActivity.this, PreferredAreaToTeachActivity.class);
                    intent.putExtra("profileId", profileId);
                    startActivity(intent);
                } else {
                    DialogHelper.showMessageDialog(AvailabilityStatusActivity.this, "Error", ((GeneralResponse) response.body()).getMessage());
                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(AvailabilityStatusActivity.this, "Error", error);

            }
        }, request);

    }
}
