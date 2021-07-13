package com.example.tutorsapp.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.BuildConfig;
import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.adapter.ActivityGenericRecyclerAdapter;
import com.example.tutorsapp.adapter.CurrentAssignmentRecyclerAdapter;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.RequestModel;
import com.example.tutorsapp.models.ShareTecherDetailsModel;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.models.jobsModels.GetJobRequestMainResponse;
import com.example.tutorsapp.models.jobsModels.GetJobsModel;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;
import com.example.tutorsapp.models.jobsModels.JobConfirmationRequestModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.dialogs.AccountActiveDialog;
import com.example.tutorsapp.ui.dialogs.AvailableForInterviewDialog;
import com.example.tutorsapp.ui.dialogs.JobAcknowlegeDialog;
import com.example.tutorsapp.ui.dialogs.JobFilterDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DashboardActivity extends BaseActivity implements View.OnClickListener, APIManager.CallbackGenric
        , CallbackGen {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView topIv;
    private ImageView drawerIv;
    private ImageView searchIv;
    private ConstraintLayout shareAppCv;
    private ConstraintLayout rateAppCv;
    private ConstraintLayout logoutCv;
    private List<RequestModel> requestModels = new ArrayList<>();
    private CurrentAssignmentRecyclerAdapter adapter;
    RecyclerView teacherJobsNearYouRv, appliedJobsRlRv, confirmJobsRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
        searchIv = findViewById(R.id.searchIv);
        drawerIv = findViewById(R.id.drawerIv);
        shareAppCv = findViewById(R.id.shareAppCv);
        rateAppCv = findViewById(R.id.rateAppCv);
        logoutCv = findViewById(R.id.logoutCv);
        RecyclerView rvRequest = findViewById(R.id.rvAssignRequests);
        Button shareDetailBtn = findViewById(R.id.shareDetailBtn);
        shareDetailBtn.setOnClickListener(this);
        rvRequest.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrentAssignmentRecyclerAdapter(requestModels, this);
        rvRequest.setAdapter(adapter);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        topIv = findViewById(R.id.topIv);

        ClipDrawable mImageDrawable = (ClipDrawable) topIv.getDrawable();
        mImageDrawable.setLevel(10000);
        topIv.setOnClickListener(this);

        teacherJobsNearYouRv = findViewById(R.id.teacherJobsNearYouRv);
        appliedJobsRlRv = findViewById(R.id.appliedJobsRlRv);
        confirmJobsRv = findViewById(R.id.confirmJobsRv);

        findViewById(R.id.tvProfileNote).setOnClickListener(this);
        findViewById(R.id.tutionAssignmentTv).setOnClickListener(this);
        findViewById(R.id.assignmentHistoryTv).setOnClickListener(this);
        findViewById(R.id.cancelltedAssignmentTv).setOnClickListener(this);
        findViewById(R.id.filterTv).setOnClickListener(this);
        searchIv.setOnClickListener(this);
        drawerIv.setOnClickListener(this);
        shareAppCv.setOnClickListener(this);
        rateAppCv.setOnClickListener(this);
        logoutCv.setOnClickListener(this);
        if (TutorApp.userInfo.isActive())
            getSentRequest();
        else {
            showAccountActivateDialog();
        }
        findViewById(R.id.teacherJobsNearYouBtn).setOnClickListener(this);
        GetJobsModel request = new GetJobsModel();
        request.setUserId(Persister.getUser(this).getUserID());
        APIManager.getInstance().getJobs(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    GetJobRequestMainResponse response1 = (GetJobRequestMainResponse) ((GeneralResponse) response.body()).getData();
                    teacherJobsNearYouRv.setAdapter(new ActivityGenericRecyclerAdapter(response1.getJobsNearBy(),
                            Constants.AVAILABLE_JOBS, DashboardActivity.this));
                    appliedJobsRlRv.setAdapter(new ActivityGenericRecyclerAdapter(response1.getAppliedJobs(),
                            Constants.APPLIED_JOBS, DashboardActivity.this));
                    confirmJobsRv.setAdapter(new ActivityGenericRecyclerAdapter(response1.getConfirmJobs(),
                            Constants.CONFRIM_JOBS, DashboardActivity.this));

                }
            }

            @Override
            public void onError(String error) {

            }
        }, request);

    }

    private void showAccountActivateDialog() {
        new AccountActiveDialog(this).show();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.drawerIv:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.tvProfileNote:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.tutionAssignmentTv:
                intent = new Intent(DashboardActivity.this, TuitionAssignmentRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.assignmentHistoryTv:
                intent = new Intent(DashboardActivity.this, TuitionAssignmentHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelltedAssignmentTv:
                intent = new Intent(DashboardActivity.this, CancelledAssignmentActivity.class);
                startActivity(intent);
                break;
            case R.id.searchIv:
                intent = new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.logoutCv:
                Persister.setFirstLaunch(this, false);
                redirectToLogin();
                break;
            case R.id.shareAppCv:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
                break;
            case R.id.rateAppCv:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;
            case R.id.shareDetailBtn:
                showDetailsDialog();
                break;
            case R.id.teacherJobsNearYouBtn:
//                startActivity(new Intent(this, JobViewDetailsActivity.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.filterTv:
                new JobFilterDialog(this).show();
                break;
            default:
                break;
        }
    }


    private void showDetailsDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.li_alert_add_notes, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(true);


        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        final EditText institutionNameEv = view.findViewById(R.id.institutionNameEv);
        final EditText universityEv = view.findViewById(R.id.universityEv);
        final EditText levelEv = view.findViewById(R.id.levelEv);
        final EditText subjectEv = view.findViewById(R.id.subjectEv);
        final EditText youHaveR1 = view.findViewById(R.id.youHaveR1);
        Button saveBtn = view.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ShareTecherDetailsModel techerDetailsModel = new ShareTecherDetailsModel();
                techerDetailsModel.setInstituteName(institutionNameEv.getText().toString());
                techerDetailsModel.setBoard(universityEv.getText().toString());
                techerDetailsModel.setEducationLevel(levelEv.getText().toString());
                techerDetailsModel.setSubject(subjectEv.getText().toString());
                techerDetailsModel.setSharedBy("");
                techerDetailsModel.setDescription(youHaveR1.getText().toString());
                APIManager.getInstance().shareTeacherDetails(DashboardActivity.this, techerDetailsModel);

            }
        });

        dialog.show();
    }

    UserInfo userInfo;

    private void getSentRequest() {
        SharedPreferences mPrefs = getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
        String userStr = mPrefs.getString(Constants.userLoginKey, "");
        Gson gson = new Gson();
        if (!userStr.isEmpty()) {
            userInfo = gson.fromJson(userStr, UserInfo.class);
        }
        RequestModel requestModel = new RequestModel();
        requestModel.setRequestTo(userInfo.getUserID());
        APIManager.getInstance().getTeacherSentRequests(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    requestModels = (List<RequestModel>) ((GeneralResponse) response.body()).getData();
                    if (requestModels != null && !requestModels.isEmpty()) {
                        adapter.updateList(requestModels);
                    } else {

                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        }, requestModel);
    }

    @Override
    public void onResult(boolean z, Response response) {
        if (((GeneralResponse) response.body()).getIsSuccess()) {
            DialogHelper.showMessageDialog(this, "Information", "Your Informations has been saved");
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void returnCall(Object o, int type) {
        if (o instanceof GetJobsResponseModel) {
            if (type == Constants.VIEW_JOB) {
                Intent viewDetailsIntent = new Intent(this, JobViewDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                viewDetailsIntent.putExtra(Constants.datePassey, (Serializable) o);
                startActivity(viewDetailsIntent);
            } else if (type == Constants.AVAILABLE_JOBS) {
                Intent applyForJobIntent = new Intent(this, ApplyForJobActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                applyForJobIntent.putExtra(Constants.datePassey, (Serializable) o);
                startActivity(applyForJobIntent);
            } else if (type == Constants.APPLIED_JOBS) {
                new AvailableForInterviewDialog(this, this, (GetJobsResponseModel) o).show();
            } else if (type == Constants.CONFRIM_JOBS) {
                new JobAcknowlegeDialog(this,  (GetJobsResponseModel) o).show();
            } else if (type == Constants.JOB_CONFIATION) {
                JobConfirmationRequestModel requestModel = new JobConfirmationRequestModel();
                requestModel.setAvailability(true);
                requestModel.setJobId(((GetJobsResponseModel) o).getJobId());
                requestModel.setTeacherId(Integer.parseInt(Persister.getUser(DashboardActivity.this).getUserID()));
                APIManager.getInstance().confirmJob(new APIManager.CallbackGenric() {
                    @Override
                    public void onResult(boolean z, Response response) {
                        if (((GeneralResponse) response.body()).getIsSuccess()) {
                            DialogHelper.showMessageDialog(DashboardActivity.this,
                                    "Job Application",
                                    ((GeneralResponse) response.body()).getMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                }, requestModel);
            }
        }

    }
}
