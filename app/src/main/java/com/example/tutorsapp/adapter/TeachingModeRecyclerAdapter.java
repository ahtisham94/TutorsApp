package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.ExperienceDetailModel;
import com.example.tutorsapp.models.TeachingModeModel;

import java.util.List;

public class TeachingModeRecyclerAdapter extends RecyclerView.Adapter<TeachingModeRecyclerAdapter.ViewHolder> {
    List<TeachingModeModel> assignmentHistoryModelList;

    public TeachingModeRecyclerAdapter(List<TeachingModeModel> list) {
        this.assignmentHistoryModelList = list;
    }

    @NonNull
    @Override
    public TeachingModeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_teaching_mode_recycler_adapter, parent, false);
        return new TeachingModeRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachingModeRecyclerAdapter.ViewHolder holder, int position) {
        TeachingModeModel experienceDetailModel = assignmentHistoryModelList.get(position);
        holder.setData(experienceDetailModel);
    }

    @Override
    public int getItemCount() {
        return assignmentHistoryModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView teachingTV;
        CheckBox teachingCB;
        ConstraintLayout rootLayout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            teachingTV = itemView.findViewById(R.id.text2);
            teachingCB = itemView.findViewById(R.id.teachingCB);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }

        void setData(final TeachingModeModel model) {
            teachingTV.setText(model.getTeachingMethod());
            teachingCB.setChecked(model.isChecked());
            rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    teachingCB.setChecked(!teachingCB.isChecked());
                    model.setChecked(teachingCB.isChecked());
                }
            });
        }
    }
}