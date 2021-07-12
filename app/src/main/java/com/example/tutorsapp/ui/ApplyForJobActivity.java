package com.example.tutorsapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;
import com.example.tutorsapp.ui.customview.TutorCustomInputList;
import com.example.tutorsapp.ui.customview.TutorEditText;
import com.example.tutorsapp.ui.customview.TutorSpinner;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ApplyForJobActivity extends BaseActivity implements View.OnClickListener, TutorEditText.TutorClick {
    TutorSpinner experienceSpinner, salarySpinner;
    TutorEditText dobEd, fullNameEd, cnicEd, currentAddressEd, permanentAddressEd, emailAddressEd,
            contactNumEd;
    TextView experienceStartDateTv, experienceEndDateTv, profilePicTv;
    Toolbar toolbar;
    Button applyNowBtn, cancelBtn;
    TutorCustomInputList qualificationList, experienceFlexList, coreWorkingsSkillFlexList, languagesFlexList,
            computerProfiencyFlexList;
    EditText schoolNametv, designationEd, gradeClassED;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLARY = 2;
    Bitmap resizeBitmap;
    GetJobsResponseModel responseModel;
    CircleImageView circularImageCv;

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
        circularImageCv = findViewById(R.id.circularImageCv);
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
        profilePicTv = findViewById(R.id.profilePicTv);
        profilePicTv.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            responseModel = (GetJobsResponseModel) getIntent().getSerializableExtra(Constants.datePassey);

        }
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
        } else if (view.getId() == R.id.profilePicTv) {
            showImagePickerDialog();
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
        } else if (schoolNametv.getText().toString().isEmpty()) {
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

    private void loadFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, PICK_FROM_GALLARY);
    }

    private void loadFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;

        switch (requestCode) {
            case PICK_FROM_GALLARY:
                if (resultCode == Activity.RESULT_OK) {
                    //enable is it your final degree radio boxes
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);

                        resizeBitmap = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
                        circularImageCv.setImageBitmap(resizeBitmap);
                        circularImageCv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", ApplyForJobActivity.this);

                    }

                } else {
                    showToastBar("You haven't picked Image", ApplyForJobActivity.this);
                }

                break;
            case PICK_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        assert data != null;
                        resizeBitmap = Constants.getScaledBitmap((Bitmap) Objects.requireNonNull(data.getExtras().get("data")));

                        circularImageCv.setImageBitmap(resizeBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", ApplyForJobActivity.this);
                    }
                } else {
                    showToastBar("You haven't picked Image", ApplyForJobActivity.this);
                }
        }
    }

    private void showImagePickerDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(true);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }
        dialog.setContentView(R.layout.li_capture_image_alert);

        TextView galleryTv = dialog.findViewById(R.id.galleryTv);
        TextView captureTv = dialog.findViewById(R.id.captureTv);
        if (galleryTv != null)
            galleryTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadFromGallery();
                    dialog.dismiss();

                }
            });

        if (captureTv != null)
            captureTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                }
            });

        dialog.show();
    }
}