package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.GenericCustomSpinnerAdapter;
import com.example.tutorsapp.enumerationss.LOVsType;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.models.EducationDetailModel;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.models.LOVSRequestModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.adapter.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Response;

public class AddEducationDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner educationSpinner, degreeSpinner, gradesSpinner;
    EditText instituteEd, cityBoradUni;
    CustomSpinnerAdapter gradersAdapter;
    GetEducation education;
    GenericCustomSpinnerAdapter degreeAdapter;
    Button saveEducationBtn;

    public AddEducationDialog(@NonNull Context context, GetEducation getEducation) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        setCancelable(true);
        setContentView(R.layout.li_alert_add_education);
        educationSpinner = findViewById(R.id.addEducationSp);
        degreeSpinner = findViewById(R.id.degreeSp);
        gradesSpinner = findViewById(R.id.gradeSp);
        instituteEd = findViewById(R.id.institutionNameEv);
        cityBoradUni = findViewById(R.id.universityEv);
        saveEducationBtn = findViewById(R.id.saveBtn);
        saveEducationBtn.setOnClickListener(this);
        gradersAdapter = new CustomSpinnerAdapter(context, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.gradeSpinner))));
        degreeSpinner.setAdapter(degreeAdapter);
        gradesSpinner.setAdapter(gradersAdapter);
        educationSpinner.setOnItemSelectedListener(this);
        education = getEducation;
        APIManager.getInstance().getLovs(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    ArrayList<LOVResponseModel> arrayList = (ArrayList<LOVResponseModel>) ((GeneralResponse) response.body()).getData();
                    educationSpinner.setAdapter(new GenericCustomSpinnerAdapter(context, R.id.spinner_item_tv, R.id.spinner_item_tv, arrayList));
                } else {
                    dismiss();
                    DialogHelper.showMessageDialog(context, "Error", ((GeneralResponse) response.body()).getMessage());
                }
            }

            @Override
            public void onError(String error) {
                dismiss();
                DialogHelper.showMessageDialog(context, "Error", error);

            }
        }, Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER ? LOVsType.DEGREE_MAIN.lovsRequestModel :
                LOVsType.DEGREE_MAIN.lovsRequestModel);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveBtn) {
//            if (educationSpinner.getSelectedItemPosition() == 0) {
//                Toast.makeText(getContext(), "Please select your education", Toast.LENGTH_SHORT).show();
//            }
//            else
            if (instituteEd.getText().toString().isEmpty()) {
//                Toast.makeText(getContext(), "Please add your institute name", Toast.LENGTH_SHORT).show();
                instituteEd.setError("Please add your institute name");
                instituteEd.requestFocus();
            } else if (cityBoradUni.getText().toString().isEmpty()) {
                cityBoradUni.setError("Please add your city name");
                cityBoradUni.requestFocus();
            } else if (gradesSpinner.getSelectedItemPosition() == 0) {
                Toast.makeText(getContext(), "Please select your grade", Toast.LENGTH_SHORT).show();
            } else {
                EducationDetailModel educationDetails = new EducationDetailModel();
                educationDetails.setDegreeName(((LOVResponseModel) degreeSpinner.getSelectedItem()).getId());
                educationDetails.setEducation(((LOVResponseModel) educationSpinner.getSelectedItem()).getId());
                educationDetails.setGrade((String) gradesSpinner.getSelectedItem());
                educationDetails.setBoardName(cityBoradUni.getText().toString());
                educationDetails.setInstituteName(instituteEd.getText().toString());
                education.education(educationDetails);
                dismiss();
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.addEducationSp:
                APIManager.getInstance().getLovs(new APIManager.CallbackGenric() {
                    @Override
                    public void onResult(boolean z, Response response) {
                        degreeAdapter = new GenericCustomSpinnerAdapter(getContext(), R.id.spinner_item_tv, R.id.spinner_item_tv, ((ArrayList<LOVResponseModel>) ((GeneralResponse) response.body()).getData()));
                        degreeSpinner.setAdapter(degreeAdapter);
                    }

                    @Override
                    public void onError(String error) {

                    }
                }, new LOVSRequestModel(false, ((LOVResponseModel) educationSpinner.getSelectedItem()).getId(), "1903"));
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface GetEducation {
        void education(EducationDetailModel educationDetailModel);
    }
}
