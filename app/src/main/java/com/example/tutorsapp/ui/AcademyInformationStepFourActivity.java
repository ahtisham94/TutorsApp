package com.example.tutorsapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.example.tutorsapp.adapter.AcademySpinnerAdapter;
import com.example.tutorsapp.models.AcademyDetailsModel;
import com.example.tutorsapp.models.OwnerModel;
import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.AcademyScheduleModel;
import com.example.tutorsapp.models.AcademyTeacherModel;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.dialogs.AcademyScheduleDialog;
import com.example.tutorsapp.ui.dialogs.AttachFacalityDetailsDialog;
import com.google.gson.Gson;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static com.example.tutorsapp.helper.Constants.ADD_LOCATION_RESULT;

public class AcademyInformationStepFourActivity extends BaseActivity implements View.OnClickListener, APIManager.CallbackGenric {
    Spinner academyListSp;
    TextView sheduleTv, facalityDetailsTv;
    Button saveBtn;
    private AcademyScheduleDialog academyScheduleDialog;
    private AttachFacalityDetailsDialog attachFacalityDetailsDialog;
    private AcademyScheduleModel academyScheduleModel;
    private AcademyTeacherModel academyTeacherModel;
    private Bitmap origianlBitmap, resizeBitmap;
    private boolean isTeacherPicScheduleSelected = false, isTeacherPicFeatureSelected = false;
    File teacherPicFileSchedule, teacherPicFileFeature;
    private int ownerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        academyScheduleModel = new AcademyScheduleModel();
        academyTeacherModel = new AcademyTeacherModel();
        setContentView(R.layout.activity_academy_information_step_four);
        initViews();
    }

    private void initViews() {
        academyListSp = findViewById(R.id.academyListSp);
        if (getIntent().getExtras() != null) {
            ownerID = getIntent().getIntExtra("OwnerID", 0);
        }
//        academyListSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.academiesArray)))));
        sheduleTv = findViewById(R.id.sheduleTv);
        sheduleTv.setOnClickListener(this);
        facalityDetailsTv = findViewById(R.id.facalityDetailsTv);
        facalityDetailsTv.setOnClickListener(this);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        obtainAcademies();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sheduleTv:
                academyScheduleDialog = new AcademyScheduleDialog(this, academyScheduleModel, new CallbackGen<AcademyScheduleModel>() {
                    @Override
                    public void returnCall(AcademyScheduleModel academyScheduleModel,int type) {
                        addAcademyScheduleToServer(academyScheduleModel);
                    }
                });
                academyScheduleDialog.show();
                break;
            case R.id.facalityDetailsTv:
                attachFacalityDetailsDialog = new AttachFacalityDetailsDialog(this, academyTeacherModel, new CallbackGen<AcademyTeacherModel>() {
                    @Override
                    public void returnCall(AcademyTeacherModel academyTeacherModel,int type) {
                        addAcademyTeacherToServer(academyTeacherModel);
                    }
                });
                attachFacalityDetailsDialog.show();
                break;
            case R.id.saveBtn:
                if (isTeacherPicFeatureSelected && isTeacherPicScheduleSelected) {
                    Intent intent = new Intent(this, AccountDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (!isTeacherPicScheduleSelected)
                    showToastBar("Please add schedule details", AcademyInformationStepFourActivity.this);
                else if (!isTeacherPicFeatureSelected)
                    showToastBar("Please add feature details", AcademyInformationStepFourActivity.this);
                break;
            case R.id.backIv:
                super.onBackPressed();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_LOCATION_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                Address address = data.getParcelableExtra(Constants.datePassey);
                if (address != null) {
                    academyScheduleDialog.setVenueTv(address);
                }
            }
        } else if (requestCode == Constants.GET_PICK_GALARY) {
            if (resultCode == Activity.RESULT_OK) {
                if (academyScheduleDialog != null && academyScheduleDialog.isShowing()) {
                    academyScheduleDialog.teacherImageUploaded();
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        origianlBitmap = BitmapFactory.decodeStream(imageStream);
                        resizeBitmap = Constants.getScaledBitmap(origianlBitmap);
                        ///        te.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        isTeacherPicScheduleSelected = true;
                        teacherPicFileSchedule = Constants.getScaledFile(resizeBitmap, AcademyInformationStepFourActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", AcademyInformationStepFourActivity.this);
                    }
                } else if (attachFacalityDetailsDialog != null && attachFacalityDetailsDialog.isShowing()) {
                    attachFacalityDetailsDialog.teacherImageUploaded();
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        origianlBitmap = BitmapFactory.decodeStream(imageStream);
                        resizeBitmap = Constants.getScaledBitmap(origianlBitmap);
                        ///        te.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        isTeacherPicFeatureSelected = true;
                        attachFacalityDetailsDialog.teacherImageUploaded();

                        teacherPicFileFeature = Constants.getScaledFile(resizeBitmap, AcademyInformationStepFourActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", AcademyInformationStepFourActivity.this);
                    }
                }
            }
        } else if (requestCode == Constants.TAKE_PIC_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                if (academyScheduleDialog != null && academyScheduleDialog.isShowing()) {
                    academyScheduleDialog.teacherImageUploaded();
                    try {
                        origianlBitmap = (Bitmap) data.getExtras().get("data");
                        resizeBitmap = Constants.getScaledBitmap(origianlBitmap);
                        //   uploadYourFinalCerTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        isTeacherPicScheduleSelected = true;
                        teacherPicFileSchedule = Constants.getScaledFile(resizeBitmap, AcademyInformationStepFourActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", AcademyInformationStepFourActivity.this);
                    }
                } else if (attachFacalityDetailsDialog != null && attachFacalityDetailsDialog.isShowing()) {
                    attachFacalityDetailsDialog.teacherImageUploaded();

                    try {
                        origianlBitmap = (Bitmap) data.getExtras().get("data");
                        resizeBitmap = Constants.getScaledBitmap(origianlBitmap);
                        //   uploadYourFinalCerTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        isTeacherPicFeatureSelected = true;
                        attachFacalityDetailsDialog.teacherImageUploaded();
                        teacherPicFileFeature = Constants.getScaledFile(resizeBitmap, AcademyInformationStepFourActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", AcademyInformationStepFourActivity.this);
                    }
                }
            }
        }
    }

    private void obtainAcademies() {
        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setOwnerID(ownerID);
        APIManager.getInstance().getAcademiesInfo(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if(response.isSuccessful()) {
                    GeneralResponse generalResponse = (GeneralResponse) response.body();
                    if(generalResponse.getIsSuccess()) {
                        ArrayList<AcademyDetailsModel> academyDetailsModels = (ArrayList<AcademyDetailsModel>) generalResponse.getData();
                        academyListSp.setAdapter(new AcademySpinnerAdapter(AcademyInformationStepFourActivity.this, R.id.spinner_item_tv, R.id.spinner_item_tv, academyDetailsModels));
                    } else {
                        //todo show error dialog
                    }
                } else {
                    //todo show error dialog
                }
            }

            @Override
            public void onError(String error) {
                //todo show error dialog
            }
        }, ownerModel);
    }


    private void addAcademyScheduleToServer(AcademyScheduleModel academyScheduleModel) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        SharedPreferences mPrefs = getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
        Gson gsonn = new Gson();
        String jsnon = mPrefs.getString(Constants.userLoginKey, "");
        UserInfo userInfo = gsonn.fromJson(jsnon, UserInfo.class);

        builder.addFormDataPart("MSISDN", userInfo.getMsisdn());
        builder.addFormDataPart("SessionId", userInfo.getSessionId());
        builder.addFormDataPart("AcademyID", "0");
        builder.addFormDataPart("AcademyScheduleName", academyScheduleModel.getAcademyScheduleName());
        builder.addFormDataPart("AcademyScheduleLevelID", academyScheduleModel.getAcademyScheduleLevelID());
        builder.addFormDataPart("AcademyScheduleSubject", academyScheduleModel.getAcademyScheduleSubject());
        builder.addFormDataPart("AcademyScheduleCourseName", academyScheduleModel.getAcademyScheduleCourseName());
        builder.addFormDataPart("IsOnlineClasses", String.valueOf(academyScheduleModel.isOnlineClasses()));
        builder.addFormDataPart("AcademyScheduleVenue", academyScheduleModel.getAcademyScheduleVenue());
        builder.addFormDataPart("TeacherOnlineID", academyScheduleModel.getTeacherOnlineID());
        builder.addFormDataPart("TeacherName", academyScheduleModel.getTeacherName());
        builder.addFormDataPart("TeacherPicPath", teacherPicFileSchedule.getAbsolutePath());
        builder.addFormDataPart("TeacherPic", teacherPicFileSchedule.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), teacherPicFileSchedule));
        builder.addFormDataPart("ScheduleStartDate", academyScheduleModel.getScheduleStartDate());
        builder.addFormDataPart("ScheduleEndDate", academyScheduleModel.getScheduleEndDate());
        builder.addFormDataPart("StartTime", academyScheduleModel.getStartTime());
        builder.addFormDataPart("EndTime", academyScheduleModel.getEndTime());
        builder.addFormDataPart("NumberClassesInWeek", academyScheduleModel.getNumberClassesInWeek());
        builder.addFormDataPart("Fees", academyScheduleModel.getFees());
        builder.addFormDataPart("ChannelID", "1");

        MultipartBody requestBody = builder.build();
        APIManager.getInstance().addAcademySchedule(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
//                    ArrayList<AcademyCategories> arrayList = (ArrayList<AcademyCategories>) ((GeneralResponse) response.body()).getData();
//                    showClassEnvDialog(arrayList);
                    if (!isTeacherPicFeatureSelected) {
                        attachFacalityDetailsDialog = new AttachFacalityDetailsDialog(AcademyInformationStepFourActivity.this, academyTeacherModel, new CallbackGen<AcademyTeacherModel>() {
                            @Override
                            public void returnCall(AcademyTeacherModel academyTeacherModel,int type) {
                                addAcademyTeacherToServer(academyTeacherModel);
                            }
                        });
                        attachFacalityDetailsDialog.show();
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        }, requestBody);
    }

    private void addAcademyTeacherToServer(AcademyTeacherModel academyTeacherModel) {

        SharedPreferences mPrefs = getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
        Gson gsonn = new Gson();
        String jsnon = mPrefs.getString(Constants.userLoginKey, "");
        UserInfo userInfo = gsonn.fromJson(jsnon, UserInfo.class);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("MSISDN", userInfo.getMsisdn());
        builder.addFormDataPart("SessionId", userInfo.getSessionId());
        builder.addFormDataPart("AcademyID", "0");
        builder.addFormDataPart("TeacherName", academyTeacherModel.getTeacherName());
        builder.addFormDataPart("Qualification", academyTeacherModel.getQualification());
        builder.addFormDataPart("TeacherSubject", academyTeacherModel.getTeacherSubject());
        builder.addFormDataPart("TeacherPicPath", teacherPicFileFeature.getAbsolutePath());
        builder.addFormDataPart("TeacherPic", teacherPicFileFeature.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), teacherPicFileFeature));
        builder.addFormDataPart("ChannelID", "1");
        builder.addFormDataPart("Achievements", academyTeacherModel.getAchievements());

        MultipartBody requestBody = builder.build();
        APIManager.getInstance().addAcademyTeacherProfile(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
//                    ArrayList<AcademyCategories> arrayList = (ArrayList<AcademyCategories>) ((GeneralResponse) response.body()).getData();
//                    showClassEnvDialog(arrayList);
                    if (!isTeacherPicScheduleSelected) {    //if schedule diaalog is not filled
                        academyScheduleDialog = new AcademyScheduleDialog(AcademyInformationStepFourActivity.this, academyScheduleModel, new CallbackGen<AcademyScheduleModel>() {
                            @Override
                            public void returnCall(AcademyScheduleModel academyScheduleModel,int type) {
                                addAcademyScheduleToServer(academyScheduleModel);
                            }
                        });
                        academyScheduleDialog.show();
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        }, requestBody);
    }

    private void validate() {

    }

    @Override
    public void onResult(boolean z, Response response) {

    }

    @Override
    public void onError(String error) {

    }
}