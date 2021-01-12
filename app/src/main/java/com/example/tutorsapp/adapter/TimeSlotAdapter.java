package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.TimeSlotModel;

import java.util.ArrayList;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyTimesSlotsHolder> {
    ArrayList<TimeSlotModel> timeSlotModelArrayList;

    public TimeSlotAdapter(ArrayList<TimeSlotModel> timeSlotModelArrayList) {
        this.timeSlotModelArrayList = timeSlotModelArrayList;
    }

    @NonNull
    @Override
    public MyTimesSlotsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_item_layout, parent, false);
        return new MyTimesSlotsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTimesSlotsHolder holder, int position) {
        holder.checkBox.setChecked(timeSlotModelArrayList.get(holder.getAdapterPosition()).isSelected());
        holder.checkBox.setText(timeSlotModelArrayList.get(holder.getAdapterPosition()).getTimeSlot());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                timeSlotModelArrayList.get(holder.getAdapterPosition()).setSelected(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlotModelArrayList.size();
    }

    public class MyTimesSlotsHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public MyTimesSlotsHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.itemTimeSlotCheckbox);
        }
    }
}
