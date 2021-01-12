package com.example.tutorsapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorsapp.GeneralCallback;
import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.login.LoginRequestModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.network.BaseCallService;
import com.hbb20.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private EditText mobileNumberEv;
    private CheckBox termConditionCheckbox;
    private TextView newToThisAppTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// pretending keyboard from automatically opening
        initView();
    }

    private void initView() {
        newToThisAppTv = findViewById(R.id.newToThisAppTv);
        termConditionCheckbox = findViewById(R.id.termConditionCheckbox);
        mobileNumberEv = findViewById(R.id.mobileNumberEv);
        ImageView topIv = findViewById(R.id.topIv);
        ClipDrawable mImageDrawable = (ClipDrawable) topIv.getDrawable();
        mImageDrawable.setLevel(10000);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    addAccountToServer(mobileNumberEv.getText().toString().replaceFirst("0", "\\92"));
                }
            }
        });
        String termsCond = "I acknowledge that I have read and agreed the <b><u>term & condition, privacy policy</u></b> and <b><u>data protection act</u></b>";
        SpannableString ss = new SpannableString(Html.fromHtml(termsCond));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };

        ss.setSpan(clickableSpan1, 82, 99, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan, 45, 78, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        newToThisAppTv.setText(ss);
        newToThisAppTv.setMovementMethod(LinkMovementMethod.getInstance());
        newToThisAppTv.setHighlightColor(Color.TRANSPARENT);
    }

    private boolean validate() {
        if (!termConditionCheckbox.isChecked()) {
            showToastBar("Please check the below check", this);
            return false;
        } else if (mobileNumberEv.getText().toString().isEmpty()) {
            mobileNumberEv.setError("Please enter number");
            mobileNumberEv.requestFocus();
            return false;
        } else if (mobileNumberEv.getText().toString().length() > 11 || !Constants.phoneRegex(mobileNumberEv.getText().toString())) {
            mobileNumberEv.setError("Please enter valid number");
            mobileNumberEv.requestFocus();
            return false;
        } else {
            mobileNumberEv.setError(null);
            return true;
        }
    }

    private void addAccountToServer(String msisdn) {
        APIManager.getInstance().loginGenerateOTp(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                assert response.body() != null;
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    Intent intent = new Intent(LoginActivity.this, VerifyOtpActvity.class);
                    intent.putExtra("msisdn", msisdn);
                    intent.putExtra("otp", (double) ((GeneralResponse) response.body()).getData());
                    startActivity(intent);
                } else {
                    DialogHelper.showMessageDialog(LoginActivity.this, "ErrorCode:" + ((GeneralResponse) response.body()).getCode(), ((GeneralResponse) response.body()).getMessage());
                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(LoginActivity.this, "ErrorCode", error);

            }
        }, new LoginRequestModel(msisdn));
    }

}
