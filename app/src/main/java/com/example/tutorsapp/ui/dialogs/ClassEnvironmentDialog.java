package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.CheckboxSpinnerApater;
import com.example.tutorsapp.adapter.GenericCustomSpinnerAdapter;
import com.example.tutorsapp.models.AcademyCategories;
import com.example.tutorsapp.models.AcademyFeatureModel;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.models.TeachingModeModel;
import com.example.tutorsapp.models.TimeSlotModel;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassEnvironmentDialog extends Dialog implements View.OnClickListener {
    Spinner noOfRoomsSp, classRommSp, facalitySp, teacherStudentRadioSp, liboratorySp, parkingSP, librarySp, canteenSp;
    Button saveBtn;
    TextView securityGuradTv, examsTv, specialFeatureTv;
    ArrayList<LOVResponseModel> examArray, securityArray, feateruesArray;

    public ClassEnvironmentDialog(@NonNull Context context, ArrayList<AcademyCategories> data) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.class_environment_dialog_layout);
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

        initViews(data);
    }

    private void initViews(ArrayList<AcademyCategories> arrayList) {
        examArray = new ArrayList<>();
        feateruesArray = new ArrayList<>();
        securityArray = new ArrayList<>();
//        Log.d("data", s);
        classRommSp = findViewById(R.id.classRommSp);
        facalitySp = findViewById(R.id.facalitySp);
        teacherStudentRadioSp = findViewById(R.id.teacherStudentRadioSp);
        liboratorySp = findViewById(R.id.liboratorySp);
        parkingSP = findViewById(R.id.parkingSP);
        saveBtn = findViewById(R.id.saveBtn);
        noOfRoomsSp = findViewById(R.id.noOfRoomsSp);
        librarySp = findViewById(R.id.librarySp);
        canteenSp = findViewById(R.id.canteenSp);
        specialFeatureTv = findViewById(R.id.specialFeatureTv);
        specialFeatureTv.setOnClickListener(this);
        examsTv = findViewById(R.id.examsTv);
        examsTv.setOnClickListener(this);
        securityGuradTv = findViewById(R.id.securityGuradTv);
        securityGuradTv.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        noOfRoomsSp.setAdapter(new CustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv,
                new ArrayList<>(Arrays.asList(getContext().getResources().getStringArray(R.array.noOfRoomsArray)))));
        try {
            Type type = new TypeToken<ArrayList<LOVResponseModel>>() {
            }.getType();
//            JSONArray rootArray = new JSONArray(s);
//            JSONObject rootObj = rootArray.getJSONObject(0);
            AcademyCategories academyCategories = arrayList.get(0);
            ArrayList<LOVResponseModel> cafateriaArray = academyCategories.getAcademyCafeteriaType();
            canteenSp.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, cafateriaArray));
            ArrayList<LOVResponseModel> academyClassRoomTypes = academyCategories.getAcademyClassRoomTypes();
            classRommSp.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, academyClassRoomTypes));
            ArrayList<LOVResponseModel> academyFacultyType = academyCategories.getAcademyFacultyType();
            facalitySp.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, academyFacultyType));
            ArrayList<LOVResponseModel> academyLibraryType = academyCategories.getAcademyLibraryType();
            librarySp.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, academyLibraryType));
            ArrayList<LOVResponseModel> academyLaboratoriesType = academyCategories.getAcademyLaboratoriesType();
            liboratorySp.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, academyLaboratoriesType));
            ArrayList<LOVResponseModel> academyTeacherToStudentRatio = academyCategories.getAcademyTeacherToStudentRatio();
            teacherStudentRadioSp.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, academyTeacherToStudentRatio));
            ArrayList<LOVResponseModel> academyParkingArea = academyCategories.getAcademyParkingArea();
            parkingSP.setAdapter(new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, academyParkingArea));
            examArray = academyCategories.getAcademyExamAssessmentType();
            securityArray = academyCategories.getAcademySecurityAreaType();
            feateruesArray = academyCategories.getAcademySpecialFeature();

        } catch (Exception e) {

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.specialFeatureTv:
                if (feateruesArray != null && feateruesArray.size() > 0) {
                    String[] feacter = new String[feateruesArray.size()];
                    for (int i = 0; i < feateruesArray.size(); i++) {
                        feacter[i] = feateruesArray.get(i).getName();
                    }
                    new TimeSlotDialog(getContext(), feacter, "Academy Features", new TimeSlotDialog.TimeSlot() {
                        @Override
                        public void timeSlot(ArrayList<TimeSlotModel> timeSlotModels) {
                            StringBuilder builder = new StringBuilder();
                            if (timeSlotModels != null && timeSlotModels.size() > 0) {
                                for (TimeSlotModel modeModel : timeSlotModels) {
                                    builder.append(modeModel.getTimeSlot());
                                    builder.append(",");
                                }
                            }
                            specialFeatureTv.setText(builder.toString().substring(0, builder.lastIndexOf(",")));
                        }
                    }).show();
                }

                break;
            case R.id.examsTv:
                if (examArray != null && examArray.size() > 0) {
                    String[] feacter = new String[examArray.size()];
                    for (int i = 0; i < examArray.size(); i++) {
                        feacter[i] = examArray.get(i).getName();
                    }
                    new TimeSlotDialog(getContext(), feacter, "Exams Assessment", new TimeSlotDialog.TimeSlot() {
                        @Override
                        public void timeSlot(ArrayList<TimeSlotModel> timeSlotModels) {
                            StringBuilder builder = new StringBuilder();
                            if (timeSlotModels != null && timeSlotModels.size() > 0) {
                                for (TimeSlotModel modeModel : timeSlotModels) {
                                    builder.append(modeModel.getTimeSlot());
                                    builder.append(",");
                                }
                            }
                            examsTv.setText(builder.toString().substring(0, builder.lastIndexOf(",")));
                        }
                    }).show();
                }

                break;
            case R.id.securityGuradTv:
                if (securityArray != null && securityArray.size() > 0) {
                    String[] feacter = new String[securityArray.size()];
                    for (int i = 0; i < securityArray.size(); i++) {
                        feacter[i] = securityArray.get(i).getName();
                    }
                    new TimeSlotDialog(getContext(), feacter, "Security level", new TimeSlotDialog.TimeSlot() {
                        @Override
                        public void timeSlot(ArrayList<TimeSlotModel> timeSlotModels) {
                            StringBuilder builder = new StringBuilder();
                            if (timeSlotModels != null && timeSlotModels.size() > 0) {
                                for (TimeSlotModel modeModel : timeSlotModels) {
                                    builder.append(modeModel.getTimeSlot());
                                    builder.append(",");
                                }
                            }
                            securityGuradTv.setText(builder.toString().substring(0, builder.lastIndexOf(",")));
                        }
                    }).show();
                }

                break;
            case R.id.saveBtn:
                if (validate())
                    dismiss();
                break;
        }
    }

    private boolean validate() {
        if (securityGuradTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select your academy security level", Toast.LENGTH_SHORT).show();
            return false;
        } else if (examsTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select your academy exams type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (specialFeatureTv.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select your academy features", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public AcademyFeatureModel getClassEnvironment() {
        AcademyFeatureModel academyFeatureModel = new AcademyFeatureModel(((LOVResponseModel)classRommSp.getSelectedItem()).getName(),
                (String) noOfRoomsSp.getSelectedItem(),
                ((LOVResponseModel)facalitySp.getSelectedItem()).getName(),
                ((LOVResponseModel)teacherStudentRadioSp.getSelectedItem()).getName(),
                ((LOVResponseModel)liboratorySp.getSelectedItem()).getName(),
                ((LOVResponseModel)librarySp.getSelectedItem()).getName(),
                ((LOVResponseModel)parkingSP.getSelectedItem()).getName(),
                securityGuradTv.getText().toString(),
                ((LOVResponseModel)canteenSp.getSelectedItem()).getName(),
                examsTv.getText().toString(),
                specialFeatureTv.getText().toString());
        return academyFeatureModel;
    }
}
