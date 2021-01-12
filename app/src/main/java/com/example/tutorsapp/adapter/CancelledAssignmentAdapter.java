package com.example.tutorsapp.adapter;

import android.content.Context;
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
import com.example.tutorsapp.models.CancelledAssignmentModel;

import java.util.List;

public class CancelledAssignmentAdapter extends RecyclerView.Adapter<CancelledAssignmentAdapter.ViewHolder> {
    List<CancelledAssignmentModel> list;
    private Context context;

    public CancelledAssignmentAdapter(List<CancelledAssignmentModel> ahl, Context context) {
        this.list = ahl;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_adapter_cancelled_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CancelledAssignmentModel ahm = list.get(position);
        holder.dateTv.setText(ahm.getDate());
        holder.cancelledbyTv.setText(ahm.getCancelledBy());
        holder.labelText.setText(ahm.getLabelText());

        boolean isExpanded = list.get(position).isExpanded();
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
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout expandableListCv;
        TextView dateTv, cancelledbyTv, labelText;
        ImageView expandedListIv;
        ImageView nonexpandedListIv;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            expandableListCv = itemView.findViewById(R.id.expandableListCv);
            dateTv = itemView.findViewById(R.id.dateTv);
            cancelledbyTv = itemView.findViewById(R.id.cancelledbyTv);
            labelText = itemView.findViewById(R.id.labelText);
            expandedListIv = itemView.findViewById(R.id.expandedListIv);
            nonexpandedListIv = itemView.findViewById(R.id.nonExpandedListIv);

            labelText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CancelledAssignmentModel ahm = list.get(getAdapterPosition());
                    ahm.setExpanded(!ahm.isExpanded());
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