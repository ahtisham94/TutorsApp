package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.interfaces.CallbackGen;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;

public class AvailableForInterviewDialog extends Dialog {
    public AvailableForInterviewDialog(@NonNull Context context, CallbackGen callbackGen, GetJobsResponseModel o) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.dialog_available_for_interview);
        if (getWindow() != null) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
            getWindow().setAttributes(lp);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }
        findViewById(R.id.okBtn).setOnClickListener(view -> {

            callbackGen.returnCall(o, Constants.JOB_CONFIATION);
            dismiss();
        });
        findViewById(R.id.cancelBtn).setOnClickListener(view -> {
            dismiss();
        });
    }
}
