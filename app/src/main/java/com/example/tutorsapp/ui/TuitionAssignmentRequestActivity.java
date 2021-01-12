package com.example.tutorsapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.AssignmentDetails;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.RequestModel;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.utils.Validations;

import java.util.List;

import retrofit2.Response;

public class TuitionAssignmentRequestActivity extends BaseActivity implements View.OnClickListener {
    public static final String JOB_ID = "JOB_ID";
    private TextView assignmentTitleTv, groupOfStudentsTv, schoolsTv, levelTv, subjectsTv, teacherRequiredTv, distanceTv, daysInWeekTv, locationTv, feeTentativeTv, modeTv,
            acceptTv, FeeFinalizeTv, rejectedByParentTv, feeCollectedTv, demoTv;
    private Button proceedBtn;
    private Integer jobId = -1;
    private AssignmentDetails assignmentDetailModel;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = Persister.getUser(this);
        setContentView(R.layout.activity_tuition_assignment_request);
        jobId = getIntent().getIntExtra(JOB_ID, -1);
        initView();
        if(jobId != -1) {
            getJobFromServer();
        }
    }

    private void initView() {
        assignmentTitleTv = findViewById(R.id.assignmentTitleTv);
        groupOfStudentsTv = findViewById(R.id.groupOfStudentsTv);
        schoolsTv = findViewById(R.id.schoolsTv);
        levelTv = findViewById(R.id.levelTv);
        subjectsTv = findViewById(R.id.subjectsTv);
        teacherRequiredTv = findViewById(R.id.teacherRequiredTv);
        distanceTv = findViewById(R.id.distanceTv);
        daysInWeekTv = findViewById(R.id.daysInWeekTv);
        locationTv = findViewById(R.id.locationTv);
        feeTentativeTv = findViewById(R.id.feeTentativeTv);
        modeTv = findViewById(R.id.modeTv);
        acceptTv = findViewById(R.id.acceptTv);
        demoTv = findViewById(R.id.demoTv);
        rejectedByParentTv = findViewById(R.id.rejectedByParentTv);
        feeCollectedTv = findViewById(R.id.feeCollectedTv);
        FeeFinalizeTv = findViewById(R.id.FeeFinalizeTv);
        proceedBtn = findViewById(R.id.proceedBtn);
        findViewById(R.id.backIv).setOnClickListener(this);
        acceptTv.setOnClickListener(this);
        demoTv.setOnClickListener(this);
        rejectedByParentTv.setOnClickListener(this);
        feeCollectedTv.setOnClickListener(this);
        FeeFinalizeTv.setOnClickListener(this);
        proceedBtn.setOnClickListener(this);

    }

    private void getJobFromServer() {
        RequestModel requestModel = new RequestModel();
        requestModel.setRequestID(jobId);
        APIManager.getInstance().getRequestDetails(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if(((GeneralResponse) response.body()).getIsSuccess()) {
                    List<AssignmentDetails> assignmentDetails = (List<AssignmentDetails>) ((GeneralResponse) response.body()).getData();
                    if(Validations.isNotEmpty(assignmentDetails)) {
                        assignmentDetailModel = assignmentDetails.get(0);
                        updateData();
                    } else {
                        Toast.makeText(tutorApp, "You dont have any assigment yet!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(tutorApp, "Something went wrong please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(tutorApp, error, Toast.LENGTH_SHORT).show();
            }
        }, requestModel);
    }

    private void updateData() {
        if(assignmentDetailModel != null) {
            assignmentTitleTv.setText(assignmentDetailModel.getStudentName());
            groupOfStudentsTv.setText(assignmentDetailModel.getNumberOfStudent() == null ? "" : String.valueOf(assignmentDetailModel.getNumberOfStudent()));
            levelTv.setText(assignmentDetailModel.getLevelSubjectID() == null ? "" : String.valueOf(assignmentDetailModel.getLevelSubjectID()));
            subjectsTv.setText(assignmentDetailModel.getLevelSubjectID() == null ? "" : String.valueOf(assignmentDetailModel.getLevelSubjectID()));
            teacherRequiredTv.setText(assignmentDetailModel.getTeacherRequireFor() == null ? "" : String.valueOf(assignmentDetailModel.getTeacherRequireFor()));
            distanceTv.setText(assignmentDetailModel.getLatLong());
            daysInWeekTv.setText(assignmentDetailModel.getNumberOfDaysInWeek());
            locationTv.setText(assignmentDetailModel.getLatLong());
            feeTentativeTv.setText(assignmentDetailModel.getMaxFee() == null ? "" : String.valueOf(assignmentDetailModel.getMaxFee()));
            modeTv.setText(assignmentDetailModel.getTeachingMode());
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case  R.id.backIv:
                super.onBackPressed();
                break;
            case R.id.proceedBtn:
                updateRequestStatus("2");
                break;
            case R.id.acceptTv:
                updateRequestStatus("1");
                break;
            case R.id.demoTv:
                updateRequestStatus("3");
                break;
            case R.id.rejectedByParentTv:
                updateRequestStatus("4");
                break;
            case R.id.FeeFinalizeTv:
                updateRequestStatus("5");
                break;
            case R.id.feeCollectedTv:
                updateRequestStatus("6");
                break;
        }
    }

    private void updateRequestStatus(String status) {
        RequestModel requestModel = new RequestModel();
        requestModel.setUserInfo(userInfo);
        requestModel.setRequestID(assignmentDetailModel.getRequestFormID());
        requestModel.setChannelID(0);
        requestModel.setRequestStatus(status);
        APIManager.getInstance().acceptRejectRequest(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                Toast.makeText(tutorApp, "Status updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(tutorApp, "Status updated", Toast.LENGTH_SHORT).show();
            }
        }, requestModel);
    }

}