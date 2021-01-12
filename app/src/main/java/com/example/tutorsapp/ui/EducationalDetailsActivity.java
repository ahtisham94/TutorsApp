package com.example.tutorsapp.ui;

import android.Manifest;
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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.adapter.EducationRecyclerAdapter;
import com.example.tutorsapp.adapter.ExperienceRecyclerAdapter;
import com.example.tutorsapp.adapter.QuranSubjectAdapter;
import com.example.tutorsapp.adapter.SubjectTechAdapter;
import com.example.tutorsapp.adapter.TeachingModeRecyclerAdapter;
import com.example.tutorsapp.enumerationss.LOVsType;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.ArrayEmpty;
import com.example.tutorsapp.models.EducationDetailModel;
import com.example.tutorsapp.models.EducationModel;
import com.example.tutorsapp.models.ExperienceDetailModel;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.models.TeachingModeModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.example.tutorsapp.ui.dialogs.AddCategporyDialog;
import com.example.tutorsapp.ui.dialogs.AddEducationDialog;
import com.example.tutorsapp.ui.dialogs.TeacherSocialIdDialog;
import com.example.tutorsapp.utils.PermissionUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class EducationalDetailsActivity extends BaseActivity implements View.OnClickListener, ArrayEmpty {
    private Dialog dialog;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLARY = 2;
    private boolean isSelected = false;
    private TextView whatCatDoYouEv, experienceTv, educationTv, uploadYourFinalCerTv, teachingMode;
    private RecyclerView addEducationRecycler, experienceRV, subjectRecyclerView;
    private List<EducationDetailModel> educationDetailModelList;
    private List<ExperienceDetailModel> experianceDetailModels = new ArrayList<>();
    private EducationRecyclerAdapter educationRecyclerAdapter;
    private SectionedRecyclerViewAdapter sectionedAdapter;
    private Bitmap origianlBitmap, resizeBitmap;
    private Button saveChangesBtn;
    private ImageView backIv;
    private HashMap<String, List<LOVResponseModel>> teachCategoryHashmap;
    private TextView skypeIdEd;
    CustomSpinnerAdapter qariTeacherTypeAdapter;
    Spinner qariTypesp;
    RelativeLayout qariTyeRL;
    int profileId = 0;
    String teachingToolType = "";
    File degreeFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// pretending keyboard from automatically opening
        initView();
        if (getIntent().getExtras() != null) {
            profileId = getIntent().getIntExtra("profileId", 0);
        }
    }

    private void initView() {
        uploadYourFinalCerTv = findViewById(R.id.uploadYourFinalCerTv);
        educationDetailModelList = new ArrayList<>();
        experienceTv = findViewById(R.id.experienceTv);
        whatCatDoYouEv = findViewById(R.id.whatCatDoYouEv);
        addEducationRecycler = findViewById(R.id.recyclerView);
        addEducationRecycler.setLayoutManager(new LinearLayoutManager(this));
        educationRecyclerAdapter = new EducationRecyclerAdapter(educationDetailModelList);
        addEducationRecycler.setAdapter(educationRecyclerAdapter);
        subjectRecyclerView = findViewById(R.id.subjectCategoryRecycler);
        experienceRV = findViewById(R.id.experienceRV);
        educationTv = findViewById(R.id.educationTv);
        educationTv.setOnClickListener(this);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(this);
        backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(this);
        teachingMode = findViewById(R.id.teachingModeTV);
        teachingMode.setOnClickListener(this);
        uploadYourFinalCerTv.setOnClickListener(this);
        experienceTv.setOnClickListener(this);
        whatCatDoYouEv.setOnClickListener(this);
        skypeIdEd = findViewById(R.id.onlineIdEv);
        skypeIdEd.setOnClickListener(this);
//        if (Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER) {
//            subjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//            educationTv.setVisibility(View.VISIBLE);
//            qariTyeRL.setVisibility(View.GONE);
//        } else if (Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER) {
//            subjectRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//            quranSubjectArray = new ArrayList<>();
//            quranSubjectAdapter = new QuranSubjectAdapter(quranSubjectArray, this);
//            subjectRecyclerView.setAdapter(quranSubjectAdapter);
//            educationTv.setVisibility(View.VISIBLE);
//            qariTyeRL.setVisibility(View.VISIBLE);
//            qariTeacherTypeAdapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hafize_Quran_Spinner))));
//            qariTypesp.setAdapter(qariTeacherTypeAdapter);
//        } else {
//            educationTv.setVisibility(View.VISIBLE);
//
//        }


    }

//    private void showQuranTeacherCategoriesDialog() {
//        final List<TeachingModeModel> teachingModeModels = getQuranSubjectList();
//        dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (dialog.getWindow() != null) {
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.getWindow().setSoftInputMode(
//                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        }
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.li_alert_add_teaching_mode);
//        final RecyclerView teachingModelRV = dialog.findViewById(R.id.teachingModelRV);
//        TextView heading = dialog.findViewById(R.id.topHeading);
//        heading.setText("What Category you want to teach");
//        teachingModelRV.setLayoutManager(new LinearLayoutManager(this));
//        TeachingModeRecyclerAdapter teachingModeRecyclerAdapter = new TeachingModeRecyclerAdapter(teachingModeModels);
//        teachingModelRV.setAdapter(teachingModeRecyclerAdapter);
//        Button saveBtn = dialog.findViewById(R.id.saveBtn);
//
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (TeachingModeModel modeModel : teachingModeModels) {
//                    if (modeModel.isChecked() && !quranSubjectArray.contains(modeModel.getTeachingMethod())) {
//                        quranSubjectArray.add(modeModel.getTeachingMethod());
//                    }
//                }
//                quranSubjectAdapter.setStringsArray(quranSubjectArray);
//                subjectRecyclerView.setVisibility(View.VISIBLE);
//
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

    private List<TeachingModeModel> getQuranSubjectList() {
        List<TeachingModeModel> teachingModeModels = new ArrayList<>();
        teachingModeModels.add(new TeachingModeModel("Tafseer of Quran and translation", false));
        teachingModeModels.add(new TeachingModeModel("Tajweed", false));
        teachingModeModels.add(new TeachingModeModel("Hifz", false));
        teachingModeModels.add(new TeachingModeModel("Kirat", false));
        teachingModeModels.add(new TeachingModeModel("Sirat", false));
        teachingModeModels.add(new TeachingModeModel("Islamiyat History", false));
        teachingModeModels.add(new TeachingModeModel("Islamiyat", false));
        return teachingModeModels;
    }

    private void showImagePickerDialog() {
        dialog = new Dialog(this);
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
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                }
            });

        dialog.show();
    }

    private void showAddExperienceDialog() {
        Calendar fromDateCalender, toDateCalender;
        fromDateCalender = Calendar.getInstance();
        toDateCalender = Calendar.getInstance();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.li_alert_add_experience);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        dialog.setCancelable(true);
        final EditText institutionName = dialog.findViewById(R.id.institutionName);
        final TextView fromTv = dialog.findViewById(R.id.fromTv);
        final TextView toTv = dialog.findViewById(R.id.toTv);
        Button addBtn = dialog.findViewById(R.id.addBtn);
        Spinner typeOfInstituteSp = dialog.findViewById(R.id.typeOfInstituteSp);
        CustomSpinnerAdapter typesOfInsititeAdapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.institutedTypeArray))));
        typeOfInstituteSp.setAdapter(typesOfInsititeAdapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (institutionName.getText() != null && institutionName.getText().toString().isEmpty())
                    Toast.makeText(EducationalDetailsActivity.this, Constants.INSTITUTION_TEXT_ERROR, Toast.LENGTH_SHORT).show();
                else if (fromTv.getText() != null && fromTv.getText().toString().isEmpty())
                    Toast.makeText(EducationalDetailsActivity.this, "Please select start date", Toast.LENGTH_SHORT).show();
                else if (toTv.getText() != null && toTv.getText().toString().isEmpty())
                    Toast.makeText(EducationalDetailsActivity.this, "Please select end date", Toast.LENGTH_SHORT).show();
                else {
                    dialog.dismiss();

                    ExperienceDetailModel model = new ExperienceDetailModel();
                    model.setEndDate(fromTv.getText().toString());
                    model.setStartDate(toTv.getText().toString());
                    model.setorganization(institutionName.getText().toString());
                    model.setInsitituteType((String) typeOfInstituteSp.getSelectedItem());
                    model.setToDate(toDateCalender);
                    model.setFromDate(fromDateCalender);
                    model.setProfileID(profileId);
                    model.setDuration("duration");

                    APIManager.getInstance().addExperience(new APIManager.CallbackGenric() {
                        @Override
                        public void onResult(boolean z, Response response) {
                            if (((GeneralResponse) response.body()).getIsSuccess()) {
                                experianceDetailModels.add(model);
                                setExperienceRecyclerAdapter();
                            } else {
                                showToastBar(((GeneralResponse) response.body()).getMessage(), EducationalDetailsActivity.this);
                            }
                        }

                        @Override
                        public void onError(String error) {
                            showToastBar(error, EducationalDetailsActivity.this);
                        }
                    }, model);

                }
            }
        });

        fromTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(EducationalDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromTv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                fromDateCalender.set(year, monthOfYear, dayOfMonth);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });

        toTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(EducationalDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                toTv.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                toDateCalender.set(year, monthOfYear, dayOfMonth);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });

        dialog.show();
    }


    private void showTeachingDialog() {
        final List<TeachingModeModel> teachingModeModels = getTeachingList();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.li_alert_add_teaching_mode);
        final RecyclerView teachingModelRV = dialog.findViewById(R.id.teachingModelRV);
        teachingModelRV.setLayoutManager(new LinearLayoutManager(this));
        TeachingModeRecyclerAdapter teachingModeRecyclerAdapter = new TeachingModeRecyclerAdapter(teachingModeModels);
        teachingModelRV.setAdapter(teachingModeRecyclerAdapter);

        Button saveBtn = dialog.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean anyOneSelected = false;
                StringBuilder teachingModes = new StringBuilder();
                for (TeachingModeModel modeModel : teachingModeModels) {
                    if (modeModel.isChecked()) {
                        anyOneSelected = true;
                        teachingModes.append(modeModel.getTeachingMethod());
                        teachingModes.append(",");
                    }

                }
                if (anyOneSelected)
                    teachingMode.setText(teachingModes.toString().substring(0, teachingModes.lastIndexOf(",")));
                skypeIdEd.setVisibility(teachingMode.getText().toString().contains("Online") ? View.VISIBLE : View.GONE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private List<TeachingModeModel> getTeachingList() {
        List<TeachingModeModel> teachingModeModels = new ArrayList<>();
        teachingModeModels.add(new TeachingModeModel("Home", false));
        teachingModeModels.add(new TeachingModeModel("Online", false));
        teachingModeModels.add(new TeachingModeModel("Home Teacher", false));
        teachingModeModels.add(new TeachingModeModel("academy/institution", false));
        teachingModeModels.add(new TeachingModeModel("School/College", false));
        teachingModeModels.add(new TeachingModeModel("University", false));
        return teachingModeModels;
    }

    private void showAddEducationialog() {
        new AddEducationDialog(EducationalDetailsActivity.this, new AddEducationDialog.GetEducation() {
            @Override
            public void education(EducationDetailModel educationDetailModel) {
                if (!educationDetailModel.isContains(educationDetailModelList)) {

                    if (educationDetailModelList != null) {
                        addEducationRecycler.setVisibility(View.VISIBLE);
                        educationDetailModelList.add(educationDetailModel);
                        educationRecyclerAdapter.setDate(educationDetailModelList);

                        EducationModel model = new EducationModel();
                        model.setEducationLevel(educationDetailModel.getEducational());
                        model.setDegree(educationDetailModel.getDegreeName());
                        model.setGrade(educationDetailModel.getGrade());
                        model.setUniBoard(educationDetailModel.getBoardName());
                        model.setInstituteName(educationDetailModel.getInstituteName());
                        model.setProfileID(profileId);
                        model.setUserInfo(TutorApp.userInfo);
                        APIManager.getInstance().addEducationalDetails(new APIManager.CallbackGenric() {
                            @Override
                            public void onResult(boolean z, Response response) {
                                if (((GeneralResponse) Objects.requireNonNull(response.body())).getIsSuccess()) {
                                    showToastBar("Education Added", EducationalDetailsActivity.this);
                                } else {
                                    showToastBar(((GeneralResponse) response.body()).getMessage(), EducationalDetailsActivity.this);

                                }
                            }

                            @Override
                            public void onError(String error) {
                                showToastBar(error, EducationalDetailsActivity.this);
                            }
                        }, model);
                    }
                } else {
                    showToastBar("Degree already added", EducationalDetailsActivity.this);
                }
            }
        }).show();
    }

    private void setExperienceRecyclerAdapter() {

        experienceTv.setError(null);
        experienceRV.setVisibility(View.VISIBLE);
        ExperienceRecyclerAdapter adapter = new ExperienceRecyclerAdapter(experianceDetailModels);
        experienceRV.setLayoutManager(new LinearLayoutManager(this));
        experienceRV.setAdapter(adapter);
    }

    private void showTeacherCategoriesDialog() {

        APIManager.getInstance().getCategoryLovs(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    ArrayList<LOVCategoryResponseModel> arrayList = (ArrayList<LOVCategoryResponseModel>) ((GeneralResponse) response.body()).getData();
                    new AddCategporyDialog(EducationalDetailsActivity.this, new AddCategporyDialog.GetSubList() {
                        @Override
                        public void SubList(HashMap<String, List<LOVResponseModel>> listHashMap) {
                            teachCategoryHashmap = listHashMap;
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
                            subjectRecyclerView.setLayoutManager(new LinearLayoutManager(EducationalDetailsActivity.this));
                            subjectRecyclerView.setAdapter(sectionedAdapter);
                            subjectRecyclerView.setVisibility(View.VISIBLE);

                        }
                    }, arrayList).show();


                }
            }

            @Override
            public void onError(String error) {

            }
        }, Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER ? LOVsType.TEACHING_CATE_MAIN.lovsRequestModel :
                LOVsType.QURAN_TEACHING_CATE_MAIN.lovsRequestModel);


    }

    private void loadFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, PICK_FROM_GALLARY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;

        switch (requestCode) {
            case PICK_FROM_GALLARY:
                if (resultCode == Activity.RESULT_OK) {
                    //enable is it your final degree radio boxes
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        origianlBitmap = BitmapFactory.decodeStream(imageStream);
                        resizeBitmap = Constants.getScaledBitmap(origianlBitmap);
                        uploadYourFinalCerTv.setText(Constants.FINAL_DEGREE_IMAGE_UPLOADED);
                        uploadYourFinalCerTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        isSelected = true;
                        degreeFile = Constants.getScaledFile(resizeBitmap, EducationalDetailsActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", EducationalDetailsActivity.this);

                    }

                } else {
                    showToastBar("You haven't picked Image", EducationalDetailsActivity.this);
                }

                break;
            case PICK_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        origianlBitmap = (Bitmap) data.getExtras().get("data");
                        resizeBitmap = Constants.getScaledBitmap(origianlBitmap);
                        uploadYourFinalCerTv.setText(Constants.FINAL_DEGREE_IMAGE_UPLOADED);
                        uploadYourFinalCerTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        isSelected = true;
                        degreeFile = Constants.getScaledFile(resizeBitmap, EducationalDetailsActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastBar("Something went wrong", EducationalDetailsActivity.this);
                    }
                } else {
                    showToastBar("You haven't picked Image", EducationalDetailsActivity.this);
                }
        }
        dialog.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.educationTv:
                showAddEducationialog();
                break;
            case R.id.whatCatDoYouEv:
                showTeacherCategoriesDialog();
//                else if (Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER)
//                    showQuranTeacherCategoriesDialog();
                break;
            case R.id.experienceTv:
                showAddExperienceDialog();
                break;
            case R.id.uploadYourFinalCerTv:
                if (PermissionUtils.hasPermissionGranted(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})) {
                    showImagePickerDialog();
                }

                break;
            case R.id.teachingModeTV:
                showTeachingDialog();
                break;
            case R.id.backIv:
                super.onBackPressed();
            case R.id.saveChangesBtn:
                if (validate()) {
                    addEducationToServer();
                }
                break;
            case R.id.onlineIdEv:
                showTeacherSocialIdDialog();
                break;

        }
    }

    private void showTeacherSocialIdDialog() {
        new TeacherSocialIdDialog(EducationalDetailsActivity.this, new TeacherSocialIdDialog.SocialMediaInformation() {
            @Override
            public void information(String userId, String socialMediaType) {
                teachingToolType = userId + "," + socialMediaType;
                skypeIdEd.setText(userId + " ( " + socialMediaType + " ) ");
            }
        }).show();
    }


    private boolean validate() {
        if (educationDetailModelList.size() == 0) {
            educationTv.setError("Please add atleat one education");
            educationTv.requestFocus();
            showToastBar("Please add atleat one education", this);
            return false;
        } else if (!isSelected) {
            showToastBar("Please add your final degree photo", this);
            return false;
        } else if (teachCategoryHashmap == null || teachCategoryHashmap.size() == 0) {
            showToastBar("Please add atleast one teaching category", this);
            whatCatDoYouEv.requestFocus();
            return false;
        } else if (experianceDetailModels == null || experianceDetailModels.size() == 0) {
            experienceTv.setError("Please add atleast one last experience");
            experienceTv.requestFocus();
            showToastBar("Please add atleast one last experience", this);
            return false;
        } else if (teachingMode.getText().toString().isEmpty()) {
            showToastBar("Please add atleast one your teaching mode", this);
            return false;
        } else if (skypeIdEd.getVisibility() == View.VISIBLE && skypeIdEd.getText().toString().isEmpty()) {
            skypeIdEd.setError("Please enter your skypeId");
            skypeIdEd.requestFocus();
            return false;
        } else {
            educationTv.setError(null);
            whatCatDoYouEv.setError(null);
            experienceTv.setError(null);
            teachingMode.setError(null);
            skypeIdEd.setError(null);

            return true;
        }

    }


    @Override
    public void empty() {
        if (subjectRecyclerView.getAdapter() instanceof QuranSubjectAdapter) {
            subjectRecyclerView.setVisibility(View.GONE);
        }
    }


    private void addEducationToServer() {
        StringBuilder teachingCategory = new StringBuilder();
        for (Map.Entry<String, List<LOVResponseModel>> listEntry : teachCategoryHashmap.entrySet()) {
            String[] header = listEntry.getKey().split(",");
            teachingCategory.append(header[0]);
            for (LOVResponseModel val : listEntry.getValue()) {
                teachingCategory.append(",");
                teachingCategory.append(val.getId());
            }
            teachingCategory.append("|");
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("TeachingMode", teachingMode.getText().toString());
        builder.addFormDataPart("FinalDegree", degreeFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), degreeFile));
        builder.addFormDataPart("FinalDegreePath", degreeFile.getAbsolutePath());
        builder.addFormDataPart("TeachingToolType", skypeIdEd.getVisibility() == View.VISIBLE ? teachingMode.getText().toString() : "string,string");
        builder.addFormDataPart("TeachingCategory", teachingCategory.toString().substring(0, teachingCategory.lastIndexOf("|")));
        builder.addFormDataPart("ProfileID", profileId + "");
        MultipartBody requestBody = builder.build();


        APIManager.getInstance().addEducationDetailMain(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    TutorApp.userInfo.setProfileStatus("2");
                    Persister.setUser(EducationalDetailsActivity.this, TutorApp.userInfo);
                    Intent intent = new Intent(EducationalDetailsActivity.this, AccountDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("profileId", profileId);
                    startActivity(intent);

                } else
                    showToastBar(((GeneralResponse) response.body()).getMessage(), EducationalDetailsActivity.this);
            }

            @Override
            public void onError(String error) {
                showToastBar(error, EducationalDetailsActivity.this);
            }
        }, requestBody);
    }
}