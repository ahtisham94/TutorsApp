package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.EducationDetailModel;
import com.example.tutorsapp.models.ExperienceDetailModel;

import java.util.List;

public class ExperienceRecyclerAdapter extends RecyclerView.Adapter<ExperienceRecyclerAdapter.ViewHolder> {
    List<ExperienceDetailModel> assignmentHistoryModelList;

    public ExperienceRecyclerAdapter(List<ExperienceDetailModel> list) {
        this.assignmentHistoryModelList = list;
    }

    @NonNull
    @Override
    public ExperienceRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_experience_recycler_adapter, parent, false);
        return new ExperienceRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceRecyclerAdapter.ViewHolder holder, int position) {
        ExperienceDetailModel experienceDetailModel = assignmentHistoryModelList.get(position);
        holder.setData(experienceDetailModel);
    }

    @Override
    public int getItemCount() {
        return assignmentHistoryModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView instituteTv, fromTv, toTv, delExperienceTv, instituteTypeValueTv;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            instituteTv = itemView.findViewById(R.id.instituteTv);
            fromTv = itemView.findViewById(R.id.fromTv);
            toTv = itemView.findViewById(R.id.toTv);
            delExperienceTv = itemView.findViewById(R.id.delExperienceTv);
            instituteTypeValueTv = itemView.findViewById(R.id.instituteTypeValueTv);
        }

        void setData(ExperienceDetailModel model) {
            instituteTv.setText(model.getorganization());
            instituteTypeValueTv.setText(model.getInsitituteType());
            fromTv.setText(model.getStartDate());
            toTv.setText(model.getEndDate());
            delExperienceTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    assignmentHistoryModelList.remove(model);
                    notifyDataSetChanged();
                }
            });
        }
    }
}