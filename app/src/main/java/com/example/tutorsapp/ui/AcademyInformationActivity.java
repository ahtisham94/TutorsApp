package com.example.tutorsapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.AcademySpinnerAdapter;
import com.example.tutorsapp.adapter.SubjectTechAdapter;
import com.example.tutorsapp.enumerationss.LOVsType;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.models.AcademyDetailsModel;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.models.OwnerModel;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.dialogs.AddCategporyDialog;
import com.example.tutorsapp.ui.dialogs.ContactDetailsDialog;
import com.example.tutorsapp.ui.dialogs.ImagesPickerDialog;
import com.google.gson.Gson;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static com.example.tutorsapp.helper.Constants.ADD_LOCATION_RESULT;

public class AcademyInformationActivity extends BaseActivity implements View.OnClickListener, APIManager.CallbackGenric {

    Button addBtn, saveBtn;
    ImageView backIv;
    Spinner academySp;
    TextView locationTv, institutionPhotoTv, uploadInnerPhotos, contactDetailTv, categoriesWantToTech;
    private SectionedRecyclerViewAdapter sectionedAdapter;
    RecyclerView subjectCategoryRecycler;
    EditText emailEd, institutionNameEv;
    private HashMap<String, List<LOVResponseModel>> subCateHashmap;
    boolean uploadAcademyLogo = false, uploadAcademyPicture = false;
    private int ownerID = 1;
    Bitmap academyLogoBitmap, academyPicBitmap;
    File academyLogoFile, academyPicFile;
    String latLong;
    boolean isAddMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy_information);
        initView();
        if (getIntent().getExtras() != null) {
            ownerID = getIntent().getIntExtra("OwnerID", 0);
        }
    }

    private void initView() {
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(this);
        academySp = findViewById(R.id.academySp);
//        academySp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.acedemyArray)))));
        obtainAcademies();
        locationTv = findViewById(R.id.locationTv);
        locationTv.setOnClickListener(this);
        institutionPhotoTv = findViewById(R.id.institutionPhotoTv);
        institutionPhotoTv.setOnClickListener(this);
        uploadInnerPhotos = findViewById(R.id.uploadInnerPhotos);
        uploadInnerPhotos.setOnClickListener(this);
        categoriesWantToTech = findViewById(R.id.categoriesWantToTech);
        categoriesWantToTech.setOnClickListener(this);
        contactDetailTv = findViewById(R.id.contactDetailTv);
        contactDetailTv.setOnClickListener(this);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        subjectCategoryRecycler = findViewById(R.id.subjectCategoryRecycler);
        subjectCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        subCateHashmap = new HashMap<>();
        institutionNameEv = findViewById(R.id.institutionNameEv);
        emailEd = findViewById(R.id.emailEd);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backIv) {
            super.onBackPressed();
        } else if (view.getId() == R.id.locationTv) {
            Intent locationIntent = new Intent(AcademyInformationActivity.this, AddLocationActivity.class);
            startActivityForResult(locationIntent, ADD_LOCATION_RESULT);

        } else if (view.getId() == R.id.institutionPhotoTv) {
            Constants.ImagePickerType = TeacherTypeEnum.ACADEMY_PICK_OUTSIDE;
            new ImagesPickerDialog(AcademyInformationActivity.this, (BaseActivity) AcademyInformationActivity.this).show();

        } else if (view.getId() == R.id.uploadInnerPhotos) {
            Constants.ImagePickerType = TeacherTypeEnum.ACADEMEY_INNER_PICK;
            new ImagesPickerDialog(AcademyInformationActivity.this, ((BaseActivity) AcademyInformationActivity.this)).show();
        } else if (view.getId() == R.id.categoriesWantToTech) {
            showTeacherCategoriesDialog();
        } else if (view.getId() == R.id.contactDetailTv) {
            showContactDetailDialog();
        } else if (view.getId() == R.id.saveBtn) {
            if (validate()) {
                isAddMore = false;
                addAcademyInfo();
            }
        } else if (view.getId() == R.id.addBtn) {
            if (validate()) {
                isAddMore = true;
                addAcademyInfo();
            }
        }
    }

    private void clearData() {
        institutionNameEv.setText("");
        locationTv.setText("");
        uploadAcademyLogo = false;
        uploadAcademyPicture = false;
        subCateHashmap = new HashMap<>();
        sectionedAdapter.removeAllSections();
        contactDetailTv.setText("");
        institutionPhotoTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        uploadAcademyLogo = false;
        uploadInnerPhotos.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        uploadAcademyPicture = false;
        subjectCategoryRecycler.setVisibility(View.GONE);
        emailEd.setText("");
    }

    private void obtainAcademies() {
        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setOwnerID(ownerID);
        APIManager.getInstance().getAcademiesInfo(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (response.isSuccessful()) {
                    GeneralResponse generalResponse = (GeneralResponse) response.body();
                    if (generalResponse.getIsSuccess()) {
                        ArrayList<AcademyDetailsModel> academyDetailsModels = (ArrayList<AcademyDetailsModel>) generalResponse.getData();
                        academySp.setAdapter(new AcademySpinnerAdapter(AcademyInformationActivity.this, R.id.spinner_item_tv, R.id.spinner_item_tv, academyDetailsModels));
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

    private void showTeacherCategoriesDialog() {
        APIManager.getInstance().getCategoryLovs(this, LOVsType.ACADEMY_TEACHING_CATE.lovsRequestModel);
    }

    private void showContactDetailDialog() {
        new ContactDetailsDialog(this, new ContactDetailsDialog.Numbers() {
            @Override
            public void numbers(String numbers) {
                contactDetailTv.setText(numbers);
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_LOCATION_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                Address address = data.getParcelableExtra(Constants.datePassey);
                if (address != null)
                    locationTv.setText(address.getAddressLine(0));
                latLong = data.getStringExtra("latlong");
            }
        } else if (requestCode == Constants.GET_PICK_GALARY) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    if (Constants.ImagePickerType == TeacherTypeEnum.ACADEMY_PICK_OUTSIDE) {
                        institutionPhotoTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        uploadAcademyLogo = true;
                        academyLogoBitmap = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
                        academyLogoFile = Constants.getScaledFile(academyLogoBitmap, this);
                    } else if (Constants.ImagePickerType == TeacherTypeEnum.ACADEMEY_INNER_PICK) {
                        uploadInnerPhotos.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        uploadAcademyPicture = true;
                        academyPicBitmap = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
                        academyPicFile = Constants.getScaledFile(academyPicBitmap, this);
                    }
                } catch (Exception e) {
                    Log.d("ex", "exception");
                }

            }

        } else if (requestCode == Constants.TAKE_PIC_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    if (Constants.ImagePickerType == TeacherTypeEnum.ACADEMY_PICK_OUTSIDE) {
                        institutionPhotoTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        uploadAcademyLogo = true;
                        assert data != null;
                        academyLogoBitmap = Constants.getScaledBitmap((Bitmap) Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get("data")));
                        academyLogoFile = Constants.getScaledFile(academyLogoBitmap, this);
                    } else if (Constants.ImagePickerType == TeacherTypeEnum.ACADEMEY_INNER_PICK) {
                        uploadInnerPhotos.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        uploadAcademyPicture = true;
                        assert data != null;
                        academyPicBitmap = Constants.getScaledBitmap((Bitmap) Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get("data")));
                        academyPicFile = Constants.getScaledFile(academyPicBitmap, this);
                    }
                } catch (Exception e) {
                    Log.d("ex", e.getMessage() + "");
                }

            }
        }
    }


    private boolean validate() {
        if (institutionNameEv.getText().toString().isEmpty()) {
            showToastBar("Please enter institute name", this);
            institutionNameEv.requestFocus();
            return false;
        } else if (locationTv.getText().toString().isEmpty()) {
            showToastBar("Please select institute location", this);
            locationTv.requestFocus();
            return false;
        } else if (!uploadAcademyLogo) {
            showToastBar("Please select academy logo", this);
            return false;
        } else if (!uploadAcademyPicture) {
            showToastBar("Please select academy picture", this);
            return false;
        } else if (subCateHashmap.size() == 0) {
            showToastBar("Please select teaching categories", this);
            return false;
        } else if (contactDetailTv.getText().toString().isEmpty()) {
            contactDetailTv.requestFocus();
            showToastBar("Please add atleast one contact number", this);
            return false;
        } else if (emailEd.getText().toString().isEmpty() || !Constants.emailValidator(emailEd.getText().toString())) {
            emailEd.requestFocus();
            showToastBar("Please enter valid email address", this);
            return false;
        }
        return true;
    }

    private void addAcademyInfo() {
        StringBuilder teachingCategory = new StringBuilder();
        for (Map.Entry<String, List<LOVResponseModel>> listEntry : subCateHashmap.entrySet()) {
            String[] header = listEntry.getKey().split(",");
            teachingCategory.append(header[0]);
            for (LOVResponseModel val : listEntry.getValue()) {
                teachingCategory.append(",");
                teachingCategory.append(val.getId());
            }
            teachingCategory.append("|");
        }

        SharedPreferences mPrefs = getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
        Gson gsonn = new Gson();
        String jsnon = mPrefs.getString(Constants.userLoginKey, "");
        UserInfo userInfo = gsonn.fromJson(jsnon, UserInfo.class);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("MSISDN", userInfo.getMsisdn());
        builder.addFormDataPart("SessionId", userInfo.getSessionId());
        builder.addFormDataPart("AcademyName", institutionNameEv.getText().toString());
        builder.addFormDataPart("AcademyLogoPath", academyLogoFile.getAbsolutePath());
        builder.addFormDataPart("AcademyPicPath", academyPicFile.getAbsolutePath());
        builder.addFormDataPart("AcademyTeachCategory", teachingCategory.toString().substring(0, teachingCategory.lastIndexOf("|")));
        builder.addFormDataPart("ChannelID", "0");
        builder.addFormDataPart("OwnerID", ownerID + "");
        builder.addFormDataPart("CurrentAddress", latLong);
        builder.addFormDataPart("AcademyLogo", academyLogoFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), academyLogoFile));
        builder.addFormDataPart("AcademyPic", academyPicFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), academyPicFile));
        if (contactDetailTv.getText().toString().contains(",")) {
            String[] contacts = contactDetailTv.getText().toString().split(",");
            builder.addFormDataPart("PrimaryContactNo", contacts[0]);
            builder.addFormDataPart("SecondaryContactNo", contacts[1]);
        } else {
            builder.addFormDataPart("PrimaryContactNo", contactDetailTv.getText().toString());
            builder.addFormDataPart("SecondaryContactNo", contactDetailTv.getText().toString());
        }
        builder.addFormDataPart("Email", emailEd.getText().toString());
        builder.addFormDataPart("AcademyType", ((AcademyDetailsModel) academySp.getSelectedItem()).getOwnTypeID());

        MultipartBody requestBody = builder.build();
        APIManager.getInstance().addAcademyInfo(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    if (!isAddMore) {
                        Intent intent = new Intent(AcademyInformationActivity.this, AcademyInformationStepThreeActivity.class);
                        startActivity(intent);
                    }else{
                        clearData();
                        isAddMore = false;
                    }

                } else {
                    DialogHelper.showMessageDialog(AcademyInformationActivity.this, "Error", ((GeneralResponse) response.body()).getMessage());
                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(AcademyInformationActivity.this, "Error", error);

            }
        }, requestBody);


    }

    @Override
    public void onResult(boolean z, Response response) {
        if (((GeneralResponse) response.body()).getIsSuccess()) {
            if (((GeneralResponse) response.body()).getData() != null &&
                    ((ArrayList<LOVCategoryResponseModel>) ((GeneralResponse) response.body()).getData()).get(0) instanceof LOVCategoryResponseModel) {
                ArrayList<LOVCategoryResponseModel> arrayList = (ArrayList<LOVCategoryResponseModel>) ((GeneralResponse) response.body()).getData();
                new AddCategporyDialog(AcademyInformationActivity.this, new AddCategporyDialog.GetSubList() {
                    @Override
                    public void SubList(HashMap<String, List<LOVResponseModel>> listHashMap) {
                        subjectCategoryRecycler.setVisibility(View.VISIBLE);
                        subCateHashmap = listHashMap;
                        sectionedAdapter = new SectionedRecyclerViewAdapter();
                        for (Map.Entry<String, List<LOVResponseModel>> listEntry : listHashMap.entrySet()) {
                            sectionedAdapter.addSection(new SubjectTechAdapter(listEntry.getKey(), listEntry.getValue(), sectionedAdapter, new SubjectTechAdapter.ClickListener() {
                                @Override
                                public void onItemRootViewClicked(@NonNull SubjectTechAdapter section, int itemAdapterPosition) {
                                    if (itemAdapterPosition != -1) {
                                        try {
                                            sectionedAdapter.notifyItemRemoved(itemAdapterPosition);

                                        } catch (Exception e) {
                                            Log.d("ex", "exception");
                                        }
                                    }

                                }
                            }));
                        }
                        subjectCategoryRecycler.setAdapter(sectionedAdapter);
                        subjectCategoryRecycler.setVisibility(View.VISIBLE);

                    }
                }, arrayList).show();
            } else {
                if (isAddMore) {
                    clearData();
                } else {
                    Intent intent = new Intent(AcademyInformationActivity.this, AcademyInformationStepThreeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("OwnerID", ownerID);
                    startActivity(intent);
                }
            }
        } else {
            DialogHelper.showMessageDialog(AcademyInformationActivity.this, "Error", ((GeneralResponse) response.body()).getMessage());

        }

    }

    @Override
    public void onError(String error) {
        DialogHelper.showMessageDialog(AcademyInformationActivity.this, "Error", error);

    }
}