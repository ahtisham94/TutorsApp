package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.TimeSlotAdapter;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.TimeSlotModel;

import java.util.ArrayList;

public class TimeSlotDialog extends Dialog implements View.OnClickListener {
    RecyclerView timeSlotRecycler;
    ArrayList<TimeSlotModel> timeSlotModelsArray;
    TimeSlotAdapter timeSlotAdapter;
    TextView doneTv, topHeading;
    TimeSlot timeSlot;

    public TimeSlotDialog(@NonNull Context context, String[] arr, String title, TimeSlot timeSlot) {
        super(context, R.style.full_screen_dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.time_slot_dialog);
        if (getWindow() != null) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
            lp.height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.70);
            getWindow().setAttributes(lp);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        timeSlotModelsArray = new ArrayList<>();
        for (String s : arr) {
            timeSlotModelsArray.add(new TimeSlotModel(false, s));
        }
        this.timeSlot = timeSlot;
        timeSlotAdapter = new TimeSlotAdapter(timeSlotModelsArray);
        timeSlotRecycler = findViewById(R.id.timeSlotRecycler);
        doneTv = findViewById(R.id.doneTv);
        doneTv.setOnClickListener(this);
        timeSlotRecycler.setLayoutManager(new LinearLayoutManager(context));
        timeSlotRecycler.setAdapter(timeSlotAdapter);
        topHeading = findViewById(R.id.topHeading);
        topHeading.setText(title);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.doneTv) {
            ArrayList<TimeSlotModel> tempArray = new ArrayList<>();
            for (TimeSlotModel model : timeSlotModelsArray) {
                if (model.isSelected()) {
                    tempArray.add(model);
                }
            }
            if (tempArray.size() != 0) {
                timeSlot.timeSlot(tempArray);
                dismiss();
            } else
                Toast.makeText(getContext(), "No option selected", Toast.LENGTH_SHORT).show();
        }
    }


    public interface TimeSlot {
        public void timeSlot(ArrayList<TimeSlotModel> timeSlotModels);
    }
}
