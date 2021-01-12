package com.example.tutorsapp.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.ui.BaseActivity;

public class ImagesPickerDialog extends Dialog implements View.OnClickListener {
    TextView galleryTv, captureTv;
    BaseActivity baseActivity;

    public ImagesPickerDialog(@NonNull Context context, BaseActivity activity) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        setCancelable(true);
        setContentView(R.layout.li_capture_image_alert);
        galleryTv = findViewById(R.id.galleryTv);
        galleryTv.setOnClickListener(this);
        captureTv = findViewById(R.id.captureTv);
        captureTv.setOnClickListener(this);
        this.baseActivity = activity;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.captureTv) {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            baseActivity.startActivityForResult(intent, Constants.TAKE_PIC_CAMERA);
            dismiss();
        } else if (view.getId() == R.id.galleryTv) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            baseActivity.startActivityForResult(pickPhoto, Constants.GET_PICK_GALARY);
            dismiss();
        }
    }
}
