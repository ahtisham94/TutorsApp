package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.EducationDetailModel;

import java.util.List;

public class EducationRecyclerAdapter extends RecyclerView.Adapter<EducationRecyclerAdapter.ViewHolder> {
    List<EducationDetailModel> assignmentHistoryModelList;
    RemoveItem removeItem;

    public EducationRecyclerAdapter(List<EducationDetailModel> list) {
        this.assignmentHistoryModelList = list;
    }

    @NonNull
    @Override
    public EducationRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_education_recycler_adapter, parent, false);
        return new EducationRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EducationRecyclerAdapter.ViewHolder holder, int position) {
        EducationDetailModel edu = assignmentHistoryModelList.get(holder.getAdapterPosition());
        holder.setData(edu);
        holder.removeItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignmentHistoryModelList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
//                removeItem.remove(holder.getAdapterPosition());
            }
        });

    }

    public void setDate(List<EducationDetailModel> list) {
        this.assignmentHistoryModelList = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return assignmentHistoryModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView degreeNameTv;
        TextView instituteTv;
        TextView universityTv;
        TextView gradeTv, removeItemTv;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            degreeNameTv = itemView.findViewById(R.id.degreeNameTv);
            instituteTv = itemView.findViewById(R.id.instituteTv);
            universityTv = itemView.findViewById(R.id.universityTv);
            gradeTv = itemView.findViewById(R.id.gradeTv);
            removeItemTv = itemView.findViewById(R.id.delEducationImg);

        }

        void setData(EducationDetailModel model) {
            degreeNameTv.setText(model.getDegreeName());
            instituteTv.setText(model.getInstituteName());
            universityTv.setText(model.getBoardName());
            gradeTv.setText(model.getGrade());
        }
    }

    public interface RemoveItem {
        public void remove(int position);
    }
}