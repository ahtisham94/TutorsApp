package com.example.tutorsapp.ui;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.customview.TutorCustomInputList;
import com.example.tutorsapp.ui.customview.TutorEditText;
import com.example.tutorsapp.ui.customview.TutorSpinner;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ApplyForJobActivity extends BaseActivity implements View.OnClickListener, TutorEditText.TutorClick
        , CallbackGen {
    TutorSpinner experienceSpinner, salarySpinner;
    TutorEditText dateOfBirthEd, fullNameEd, cnicEd, currentAddressEd, permanentAddressEd, emailAddressEd,
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
    File profileFile;

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
        dateOfBirthEd = findViewById(R.id.dateOfBirthEd);
        dateOfBirthEd.setClickListenser(this);
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
            DialogHelper.callbackGen = this;
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart("JobId", "" + responseModel.getJobId());
            builder.addFormDataPart("TeacherId", Persister.getUser(this).getUserID());
            builder.addFormDataPart("FullName", fullNameEd.getText());
            builder.addFormDataPart("Qulification", qualificationList.getListData());
            builder.addFormDataPart("ExperienceLevel", (String) experienceSpinner.getSpinnerSelectedItem());
            builder.addFormDataPart("Experience",
                    schoolNametv.getText().toString() + "|" +
                            designationEd.getText().toString() + "|" +
                            gradeClassED.getText().toString() + "|" +
                            experienceStartDateTv.getText().toString() + "|" +
                            experienceEndDateTv.getText().toString() + "|" +
                            experienceFlexList.getListData());
            builder.addFormDataPart("DateOfBirth", dateOfBirthEd.getText());
            builder.addFormDataPart("CNIC", cnicEd.getText());
            builder.addFormDataPart("CoreWorkingSkills", coreWorkingsSkillFlexList.getListData());
            builder.addFormDataPart("CurrentAddress", currentAddressEd.getText());
            builder.addFormDataPart("PermanentAddress", permanentAddressEd.getText());
            builder.addFormDataPart("Email", emailAddressEd.getText());
            builder.addFormDataPart("PhoneNumber", contactNumEd.getText());
            builder.addFormDataPart("LanguageProficiency", languagesFlexList.getListData());
            builder.addFormDataPart("ComputerProficiency", computerProfiencyFlexList.getListData());
            builder.addFormDataPart("SalaryRange", (String) salarySpinner.getSpinnerSelectedItem());
            builder.addFormDataPart("Picture", profileFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), profileFile));
            MultipartBody requestBody = builder.build();
            APIManager.getInstance().applyForJob(new APIManager.CallbackGenric() {
                @Override
                public void onResult(boolean z, Response response) {
                    if (((GeneralResponse) response.body()).getIsSuccess()) {
                        DialogHelper.showMessageDialog(ApplyForJobActivity.this, "Apply Form",
                                "You have successfully applied for this job");
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(ApplyForJobActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }, requestBody);
        }
    }

    private boolean vaildate() {
        if (fullNameEd.getText().isEmpty()) {
            fullNameEd.setError("Name must be entered");
            return false;
        } else if (qualificationList.getListData().isEmpty()) {
            Toast.makeText(this, "Please add atleast one qualifaction", Toast.LENGTH_SHORT).show();
            qualificationList.requestFocus();
            return false;
        } else if (schoolNametv.getText().toString().isEmpty()) {
            schoolNametv.setError("School/Institute Name must be entered");
            schoolNametv.requestFocus();
            return false;
        } else if (designationEd.getText().toString().isEmpty()) {
            designationEd.setError("Designation must be entered");
            designationEd.requestFocus();
            return false;
        } else if (gradeClassED.getText().toString().isEmpty()) {
            gradeClassED.setError("Grade/Class must be entered");
            gradeClassED.requestFocus();
            return false;
        } else if (experienceStartDateTv.getText().toString().isEmpty()) {
            experienceStartDateTv.setError("Please select start experience Date");
            experienceStartDateTv.requestFocus();
            return false;
        } else if (experienceEndDateTv.getText().toString().isEmpty()) {
            experienceEndDateTv.setError("Please select end experience Date");
            experienceEndDateTv.requestFocus();
            return false;
        } else if (experienceFlexList.getListData().isEmpty()) {
            Toast.makeText(this, "Please add atleast one experience", Toast.LENGTH_SHORT).show();
            experienceFlexList.requestFocus();
            return false;
        } else if (dateOfBirthEd.getText().isEmpty()) {
            dateOfBirthEd.setError("Please add date of birth");
            return false;
        } else if (cnicEd.getText().isEmpty()) {
            cnicEd.setError("Please add cnic number");
            return false;
        } else if (coreWorkingsSkillFlexList.getListData().isEmpty()) {
            Toast.makeText(this, "Please add atleast one core working skill", Toast.LENGTH_SHORT).show();
            return false;
        } else if (currentAddressEd.getText().isEmpty()) {
            currentAddressEd.setError("Please enter current address");
            return false;
        } else if (permanentAddressEd.getText().isEmpty()) {
            permanentAddressEd.setError("Please enter permanent address");
            return false;
        } else if (emailAddressEd.getText().isEmpty()) {
            emailAddressEd.setError("Please enter email address");
            return false;
        } else if (!Constants.emailValidator(emailAddressEd.getText())) {
            emailAddressEd.setError("Please enter valid email address");
            return false;
        } else if (contactNumEd.getText().isEmpty()) {
            contactNumEd.setError("Please enter phone number");
            return false;
        } else if (!Constants.phoneRegex(contactNumEd.getText())) {
            contactNumEd.setError("Please enter valid phone number");
            return false;
        } else if (languagesFlexList.getListData().isEmpty()) {
            Toast.makeText(this, "Please add atleast one language", Toast.LENGTH_SHORT).show();
            return false;
        } else if (computerProfiencyFlexList.getListData().isEmpty()) {
            Toast.makeText(this, "Please add atleast one computer proficiency", Toast.LENGTH_SHORT).show();
            return false;
        }

        fullNameEd.setError(null);
        schoolNametv.setError(null);
        designationEd.setError(null);
        gradeClassED.setError(null);
        experienceStartDateTv.setError(null);
        experienceEndDateTv.setError(null);
        dateOfBirthEd.setError(null);
        cnicEd.setError(null);
        currentAddressEd.setError(null);
        permanentAddressEd.setError(null);
        emailAddressEd.setError(null);
        contactNumEd.setError(null);
        return true;
    }

    private void setExperienceEndDate() {
        DatePickerDialog dobDate = new DatePickerDialog(ApplyForJobActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-YYYY", Locale.getDefault());
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-YYYY", Locale.getDefault());
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-YYYY", Locale.getDefault());
                dateOfBirthEd.setEditTextValue(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dobDate.getDatePicker().setMaxDate(System.currentTimeMillis());
        dobDate.show();
    }

    final Calendar newCalendar = Calendar.getInstance();

    @Override
    public void onTutorClick(TutorEditText tutorEditText) {
        if (tutorEditText.getId() == R.id.dateOfBirthEd)
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
                        profileFile = Constants.getScaledFile(resizeBitmap, this);
                        Glide.with(circularImageCv).load(resizeBitmap).centerCrop().into(circularImageCv);
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
                        profileFile = Constants.getScaledFile(resizeBitmap, this);
                        Glide.with(circularImageCv).load(resizeBitmap).centerCrop().into(circularImageCv);
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

    @Override
    public void returnCall(Object o, int type) {
        if (type == 1) {
            finish();
        }

    }
}