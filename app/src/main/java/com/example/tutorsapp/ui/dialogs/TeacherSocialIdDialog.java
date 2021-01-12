package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherSocialIdDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner socialMediaSpinner;
    EditText enterIdEd, otherMediaTypeEd;
    TextView doneTv;
    SocialMediaInformation socialMediaInformation;

    public TeacherSocialIdDialog(@NonNull Context context, SocialMediaInformation socialMediaInformation) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.teacher_social_id_dialog);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);

            getWindow().setAttributes(lp);

        }
        socialMediaSpinner = findViewById(R.id.socialMediaSpinner);
        socialMediaSpinner.setAdapter(new CustomSpinnerAdapter(context, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.socialMediaArra)))));
        socialMediaSpinner.setOnItemSelectedListener(this);
        enterIdEd = findViewById(R.id.enterIdEd);
        otherMediaTypeEd = findViewById(R.id.otherMediaTypeEd);
        doneTv = findViewById(R.id.doneTv);
        doneTv.setOnClickListener(this);
        this.socialMediaInformation = socialMediaInformation;
    }

    @Override
    public void onClick(View view) {
        if (validate()) {
            socialMediaInformation.information(enterIdEd.getText().toString(),
                    socialMediaSpinner.getSelectedItem().toString().equals("Others") ? otherMediaTypeEd.getText().toString() :
                            ((String) socialMediaSpinner.getSelectedItem()));
            dismiss();
        }
    }

    private boolean validate() {
        if (socialMediaSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please select media type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (enterIdEd.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please add your social id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (socialMediaSpinner.getSelectedItem().equals("Others") && otherMediaTypeEd.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please add your social media type", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.socialMediaSpinner) {
            if (socialMediaSpinner.getSelectedItem().equals("Others")) {
                otherMediaTypeEd.setVisibility(View.VISIBLE);
            } else {
                otherMediaTypeEd.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface SocialMediaInformation {
        public void information(String userId, String socialMediaType);
    }
}
