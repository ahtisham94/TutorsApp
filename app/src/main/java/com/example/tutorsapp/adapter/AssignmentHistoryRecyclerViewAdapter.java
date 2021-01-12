package com.example.tutorsapp.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.AssignmentHistoryModel;

import java.util.List;

public class AssignmentHistoryRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentHistoryRecyclerViewAdapter.ViewHolder> {
    List<AssignmentHistoryModel> assignmentHistoryModelList;

    public AssignmentHistoryRecyclerViewAdapter(List<AssignmentHistoryModel> ahl) {
        this.assignmentHistoryModelList = ahl;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_adapter_assignment_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssignmentHistoryModel ahm = assignmentHistoryModelList.get(position);
        holder.remarksStatusTv.setText(ahm.getRemarks());
        holder.assignmentCodeTv.setText("" + ahm.getAssignmentCode());
        holder.dateTv.setText(ahm.getDate());
        holder.paymentTv.setText(ahm.getPayment());
        holder.parentText.setText(ahm.getParentText());

        boolean isExpanded = assignmentHistoryModelList.get(position).isExpanded();
        holder.expandableListCv.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        if (ahm.isExpanded()) {
            holder.nonexpandedListIv.setVisibility(View.GONE);
            holder.expandedListIv.setVisibility(View.VISIBLE);
        } else {
            holder.nonexpandedListIv.setVisibility(View.VISIBLE);
            holder.expandedListIv.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return assignmentHistoryModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout expandableListCv;
        TextView remarksStatusTv, assignmentCodeTv, dateTv, paymentTv, parentText;
        ImageView expandedListIv;
        ImageView nonexpandedListIv;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            remarksStatusTv = itemView.findViewById(R.id.remarksStatusTv);
            parentText = itemView.findViewById(R.id.parentText);
            assignmentCodeTv = itemView.findViewById(R.id.assignmentCodeTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            paymentTv = itemView.findViewById(R.id.paymentTv);
            expandableListCv = itemView.findViewById(R.id.expandableListCv);
            expandedListIv = itemView.findViewById(R.id.expandedListIv);
            nonexpandedListIv = itemView.findViewById(R.id.nonExpandedListIv);

            parentText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AssignmentHistoryModel ahm = assignmentHistoryModelList.get(getAdapterPosition());
                    ahm.setExpanded(!ahm.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    if (ahm.isExpanded()) {
                        nonexpandedListIv.setVisibility(View.GONE);
                        expandedListIv.setVisibility(View.VISIBLE);
                    } else {
                        nonexpandedListIv.setVisibility(View.VISIBLE);
                        expandedListIv.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
