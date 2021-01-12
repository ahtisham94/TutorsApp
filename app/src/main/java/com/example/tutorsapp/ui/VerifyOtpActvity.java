package com.example.tutorsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorsapp.GeneralCallback;
import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.helper.FormStatusHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.models.login.LoginValidateOTPRequest;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.network.BaseCallService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

public class VerifyOtpActvity extends BaseActivity {
    private EditText otpEd;
    String userMsisdn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_verify_otp);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// pretending keyboard from automatically opening
        initView();
    }

    private void initView() {
        Button verifyBtn = findViewById(R.id.verifyBtn);
        otpEd = findViewById(R.id.mobileNumberEv);
        if (getIntent().getExtras() != null) {
            userMsisdn = getIntent().getStringExtra("msisdn");
            double d = getIntent().getDoubleExtra("otp", 0.00);
            otpEd.setText((int) d + "");
        }

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    validateOTP();
                }
            }
        });
    }

    private boolean validate() {
        if (otpEd.getText().toString().isEmpty()) {
            otpEd.setError("Please enter otp");
            otpEd.requestFocus();
            return false;
        } else if (otpEd.getText().toString().length() < 4) {
            otpEd.setError("Please enter valid otp");
            otpEd.requestFocus();
            return false;
        } else {
            otpEd.setError(null);
            return true;
        }
    }


    private void validateOTP() {
        LoginValidateOTPRequest request = new LoginValidateOTPRequest(Integer.parseInt(otpEd.getText().toString()), userMsisdn);
        APIManager.getInstance().loginValidateOTp(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getFlag().equals("yes")) {
                    GeneralResponse user = (GeneralResponse) response.body();
                    TutorApp.userInfo = new UserInfo(user.getMsisdn(), user.getSessionID(), user.getProfileStatus(), user.getProfileTypeID(),  user.getUserID());

                    Persister.setUser(VerifyOtpActvity.this, TutorApp.userInfo);

                    if(TutorApp.userInfo.getProfileStatus().equals("5")) {
                        Toast.makeText(tutorApp, "This is Parent/Student account! Please login to our parent student app.", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(FormStatusHelper.getStatusIntent(VerifyOtpActvity.this, TutorApp.userInfo).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    }

                } else {
                    DialogHelper.showMessageDialog(VerifyOtpActvity.this, "Error Code :" + ((GeneralResponse) response.body()).getCode(), ((GeneralResponse) response.body()).getMessage());
                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(VerifyOtpActvity.this, "Error", error);

            }
        }, request);
    }
}
