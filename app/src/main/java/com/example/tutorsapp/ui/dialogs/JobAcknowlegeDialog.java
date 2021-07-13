package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;

public class JobAcknowlegeDialog extends Dialog {
    TextView dateTv;

    public JobAcknowlegeDialog(@NonNull Context context, GetJobsResponseModel responseModel) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.dialog_job_final_selection_layout);
        if (getWindow() != null) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
            getWindow().setAttributes(lp);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        findViewById(R.id.acknolegeId).setOnClickListener(view -> {
            dismiss();
        });
        dateTv = findViewById(R.id.dateTv);
        dateTv.setText("Date :  " + responseModel.getFinalSelectionDate());
    }
}
