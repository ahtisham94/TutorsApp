package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.AcademyTeacherModel;
import com.example.tutorsapp.ui.BaseActivity;

public class AttachFacalityDetailsDialog extends Dialog implements View.OnClickListener {
    TextView uploadTeacherPicTv;
    Button saveBtn;
    boolean isFaculityPic = false;
    EditText teacherNameEd, qualificationEd, subjectNameEd, achievementsEd;
    private CallbackGen<AcademyTeacherModel> callbackGen;
    private AcademyTeacherModel academyTeacherModel;

    public AttachFacalityDetailsDialog(@NonNull Context context, AcademyTeacherModel academyTeacherModel, CallbackGen<AcademyTeacherModel> callbackGen) {
        super(context);
        this.callbackGen = callbackGen;
        this.academyTeacherModel = academyTeacherModel;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.dialog_attach_facality_details_layout);
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
        uploadTeacherPicTv = findViewById(R.id.uploadTeacherPicTv);
        uploadTeacherPicTv.setOnClickListener(this);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        teacherNameEd = findViewById(R.id.teacherNameEd);
        qualificationEd = findViewById(R.id.qualificationEd);
        subjectNameEd = findViewById(R.id.subjectNameEd);
        achievementsEd = findViewById(R.id.achievementsEd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.uploadTeacherPicTv:
                new ImagesPickerDialog(getContext(), (BaseActivity) TutorApp.getInstance().getmCurrentActivity()).show();
                break;
            case R.id.saveBtn:
                if(validate()) {
                    obtainData();
                    dismiss();
                }
                break;
        }
    }

    public void teacherImageUploaded() {
        isFaculityPic = true;
        uploadTeacherPicTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
        uploadTeacherPicTv.setText("Teacher Image selected");
    }

    private boolean validate() {
        if (teacherNameEd.getText().toString().isEmpty()) {
            teacherNameEd.setError("Please enter teacher name");
            teacherNameEd.requestFocus();
            return false;
        } else if (qualificationEd.getText().toString().isEmpty()) {
            qualificationEd.setError("Please enter qualifications");
            qualificationEd.requestFocus();
            return false;
        } else if (subjectNameEd.getText().toString().isEmpty()) {
            subjectNameEd.setError("Please enter subject name");
            subjectNameEd.requestFocus();
            return false;
        } else if (!isFaculityPic) {
            Toast.makeText(getContext(), "Please upload faculity picture", Toast.LENGTH_SHORT).show();
            return false;
        } else if (achievementsEd.getText().toString().isEmpty()) {
            achievementsEd.setError("Please enter your acheivements");
            achievementsEd.requestFocus();
            return false;

        } else {
            teacherNameEd.setError(null);
            qualificationEd.setError(null);
            subjectNameEd.setError(null);
            achievementsEd.setError(null);
            return true;
        }
    }

    private void obtainData() {
        academyTeacherModel.setTeacherName(teacherNameEd.getText().toString());
        academyTeacherModel.setQualification(qualificationEd.getText().toString());
        academyTeacherModel.setTeacherSubject(subjectNameEd.getText().toString());
        academyTeacherModel.setAchievements(achievementsEd.getText().toString());
        callbackGen.returnCall(academyTeacherModel);
    }

}
