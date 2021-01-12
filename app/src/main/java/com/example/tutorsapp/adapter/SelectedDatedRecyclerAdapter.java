package com.example.tutorsapp.adapter;

import android.provider.ContactsContract;
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

public class SelectedDatedRecyclerAdapter extends RecyclerView.Adapter<SelectedDatedRecyclerAdapter.ViewHolder> {
    private List<String> list;

    public SelectedDatedRecyclerAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_selected_dates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        ImageView delDate;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.dateTv);
            delDate = itemView.findViewById(R.id.delDate);

        }

        void setData(String date) {
            dateTv.setText(date);
            delDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(date);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
