package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;

import java.util.ArrayList;

public class ActivityGenericRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> arrayList;
    private int type;
    private CallbackGen callbackGen;

    public ActivityGenericRecyclerAdapter(ArrayList<T> arrayList, int type, CallbackGen callbackGen) {
        this.arrayList = arrayList;
        this.type = type;
        this.callbackGen = callbackGen;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == Constants.AVAILABLE_JOBS || type == Constants.APPLIED_JOBS || type == Constants.CONFRIM_JOBS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_item_layout, parent, false);
            return new MyJobsHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        T obj = arrayList.get(holder.getAdapterPosition());
        if (holder instanceof MyJobsHolder && obj instanceof GetJobsResponseModel && type == Constants.AVAILABLE_JOBS) {
            ((MyJobsHolder) holder).teacherTypeTv.setText(((GetJobsResponseModel) obj).getJobTitle());
            ((MyJobsHolder) holder).teacherGenderTv.setText(((GetJobsResponseModel) obj).getGender());
            ((MyJobsHolder) holder).noOfPostTv.setText(((GetJobsResponseModel) obj).getJobPosts());
            ((MyJobsHolder) holder).instituteNameTv.setText(((GetJobsResponseModel) obj).getInstituteName());
            ((MyJobsHolder) holder).levelTv.setText(((GetJobsResponseModel) obj).getLevel());
            ((MyJobsHolder) holder).addressTv.setText(((GetJobsResponseModel) obj).getJobArea());
            ((MyJobsHolder) holder).applyBeforeTv.setText(((GetJobsResponseModel) obj).getApplyBefore());
            ((MyJobsHolder) holder).salaryTv.setText(((GetJobsResponseModel) obj).getMaxSalary());
            ((MyJobsHolder) holder).applyNowBtn.setText(((GetJobsResponseModel) obj).getJobStatusName());
            ((MyJobsHolder) holder).interviewLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).contactForUpdateLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).emailLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).viewDetailsBtn.setOnClickListener(view -> {
                callbackGen.returnCall(obj, Constants.VIEW_JOB);
            });
            ((MyJobsHolder) holder).applyNowBtn.setOnClickListener(view -> {
                callbackGen.returnCall(obj, Constants.AVAILABLE_JOBS);
            });

        } else if (holder instanceof MyJobsHolder && obj instanceof GetJobsResponseModel && type == Constants.APPLIED_JOBS) {
            ((MyJobsHolder) holder).teacherTypeTv.setText(((GetJobsResponseModel) obj).getJobTitle());
            ((MyJobsHolder) holder).teacherGenderTv.setText(((GetJobsResponseModel) obj).getGender());
            ((MyJobsHolder) holder).noOfPostTv.setText(((GetJobsResponseModel) obj).getJobPosts());
            ((MyJobsHolder) holder).instituteNameTv.setText(((GetJobsResponseModel) obj).getInstituteName());
            ((MyJobsHolder) holder).levelTv.setText(((GetJobsResponseModel) obj).getLevel());
            ((MyJobsHolder) holder).addressTv.setText(((GetJobsResponseModel) obj).getJobArea());
            ((MyJobsHolder) holder).applyBeforeTv.setText(((GetJobsResponseModel) obj).getApplyBefore());
            ((MyJobsHolder) holder).salaryTv.setText(((GetJobsResponseModel) obj).getMaxSalary());
            ((MyJobsHolder) holder).applyNowBtn.setText(((GetJobsResponseModel) obj).getJobStatusName());
            ((MyJobsHolder) holder).applyNowBtn.setEnabled(false);
            ((MyJobsHolder) holder).viewDetailsBtn.setText("Confirm Availability");
            ((MyJobsHolder) holder).interviewTimeDateTv.setText(((GetJobsResponseModel) obj).getDateTime());
            ((MyJobsHolder) holder).contactDetailsTv.setText(((GetJobsResponseModel) obj).getContact());
            ((MyJobsHolder) holder).emailTv.setText(((GetJobsResponseModel) obj).getEmail());
            ((MyJobsHolder) holder).applyBeforeLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).viewDetailsBtn.setOnClickListener(view -> {
                callbackGen.returnCall(obj, Constants.APPLIED_JOBS);
            });
        } else if (holder instanceof MyJobsHolder && obj instanceof GetJobsResponseModel && type == Constants.CONFRIM_JOBS) {
            ((MyJobsHolder) holder).teacherTypeTv.setText(((GetJobsResponseModel) obj).getJobTitle());
            ((MyJobsHolder) holder).teacherGenderTv.setText(((GetJobsResponseModel) obj).getGender());
            ((MyJobsHolder) holder).noOfPostTv.setText(((GetJobsResponseModel) obj).getJobPosts());
            ((MyJobsHolder) holder).instituteNameTv.setText(((GetJobsResponseModel) obj).getInstituteName());
            ((MyJobsHolder) holder).levelTv.setText(((GetJobsResponseModel) obj).getLevel());
            ((MyJobsHolder) holder).addressTv.setText(((GetJobsResponseModel) obj).getJobArea());
            ((MyJobsHolder) holder).applyBeforeTv.setText(((GetJobsResponseModel) obj).getApplyBefore());
            ((MyJobsHolder) holder).salaryTv.setText(((GetJobsResponseModel) obj).getMaxSalary());
            ((MyJobsHolder) holder).applyNowBtn.setText(((GetJobsResponseModel) obj).getJobStatusName());
            ((MyJobsHolder) holder).viewDetailsBtn.setText("Report Documents");
            ((MyJobsHolder) holder).interviewTimeDateTv.setText(((GetJobsResponseModel) obj).getDateTime());
            ((MyJobsHolder) holder).contactDetailsTv.setText(((GetJobsResponseModel) obj).getContact());
            ((MyJobsHolder) holder).emailTv.setText(((GetJobsResponseModel) obj).getEmail());
            ((MyJobsHolder) holder).salaryLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).interviewLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).applyBeforeLL.setVisibility(View.GONE);
            ((MyJobsHolder) holder).applyNowBtn.setEnabled(false);
            ((MyJobsHolder) holder).viewDetailsBtn.setOnClickListener(view -> {
                callbackGen.returnCall(obj, Constants.CONFRIM_JOBS);
            });
        }
    }

    public static class MyJobsHolder extends RecyclerView.ViewHolder {
        TextView teacherTypeTv, teacherGenderTv, noOfPostTv, instituteNameTv, addressTv,
                levelTv, directionTv, applyBeforeTv, interviewTimeDateTv, contactDetailsTv, emailTv, salaryTv;
        Button viewDetailsBtn, applyNowBtn;

        LinearLayout interviewLL, contactForUpdateLL, emailLL, salaryLL, applyBeforeLL;

        public MyJobsHolder(@NonNull View itemView) {
            super(itemView);
            teacherTypeTv = itemView.findViewById(R.id.teacherTypeTv);
            teacherGenderTv = itemView.findViewById(R.id.teacherGenderTv);
            noOfPostTv = itemView.findViewById(R.id.noOfPostTv);
            instituteNameTv = itemView.findViewById(R.id.instituteNameTv);
            addressTv = itemView.findViewById(R.id.addressTv);
            levelTv = itemView.findViewById(R.id.levelTv);
            directionTv = itemView.findViewById(R.id.directionTv);
            applyBeforeTv = itemView.findViewById(R.id.applyBeforeTv);
            interviewTimeDateTv = itemView.findViewById(R.id.interviewTimeDateTv);
            contactDetailsTv = itemView.findViewById(R.id.contactDetailsTv);
            emailTv = itemView.findViewById(R.id.emailTv);
            salaryTv = itemView.findViewById(R.id.salaryTv);
            viewDetailsBtn = itemView.findViewById(R.id.viewDetailsBtn);
            applyNowBtn = itemView.findViewById(R.id.applyNowBtn);
            interviewLL = itemView.findViewById(R.id.interviewLL);
            contactForUpdateLL = itemView.findViewById(R.id.contactForUpdateLL);
            emailLL = itemView.findViewById(R.id.emailLL);
            salaryLL = itemView.findViewById(R.id.salaryLL);
            applyBeforeLL = itemView.findViewById(R.id.applyBeforeLL);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
