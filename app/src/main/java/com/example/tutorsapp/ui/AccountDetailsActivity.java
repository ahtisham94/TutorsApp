package com.example.tutorsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.adapter.GenericCustomSpinnerAdapter;
import com.example.tutorsapp.enumerationss.LOVsType;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.AccountDetails;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.example.tutorsapp.ui.customview.TimeLineCustomView;
import com.example.tutorsapp.utils.Validations;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Response;

public class AccountDetailsActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    EditText accountTitleTv, accountNumberTv, branchCodeTv;
    View cityRl, bankNamesRl;
    int accountType = 0;
    private Spinner accountTypeSp, citySp, bankNamesSp;
    private CustomSpinnerAdapter serviceProvideAdapter, accountTypeAdapter;
    GenericCustomSpinnerAdapter citysAdapter, bankNamesAdapter;
    private AccountDetails accountDetails;
    private Button saveChangesBtn;
    private ImageView backIv;
    private int profileId = 0;
    private TimeLineCustomView timeLineCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initView();
        if (getIntent().getExtras() != null) {
            profileId = getIntent().getIntExtra("profileId", 0);
        }
        if (Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE || Constants.teacherTye == TeacherTypeEnum.BOOK_STORE) {
            timeLineCustomView.setTimeLine(5);
        }
    }

    private void initView() {
        timeLineCustomView = findViewById(R.id.timelineLayout);
        accountTypeSp = findViewById(R.id.accountTypeSpinner);
        bankNamesSp = findViewById(R.id.bankNamesSpinner);
        citySp = findViewById(R.id.citySp);
        accountTitleTv = findViewById(R.id.accountTitleTv);
        accountNumberTv = findViewById(R.id.accountNumberTv);
        branchCodeTv = findViewById(R.id.branchCodeTv);
        cityRl = findViewById(R.id.cityRl);
        bankNamesRl = findViewById(R.id.bankNameRl);
        serviceProvideAdapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.servi_providers))));
        accountTypeAdapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.account_type_arr))));
        accountTypeSp.setAdapter(accountTypeAdapter);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        accountTypeSp.setOnItemSelectedListener(this);
        saveChangesBtn.setOnClickListener(this);
        backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(this);
        APIManager.getInstance().getLovs(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    ArrayList<LOVResponseModel> arrayList = (ArrayList<LOVResponseModel>) ((GeneralResponse) response.body()).getData();
                    bankNamesAdapter = new GenericCustomSpinnerAdapter(AccountDetailsActivity.this, R.id.spinner_item_tv, R.id.spinner_item_tv, arrayList);
                    bankNamesSp.setAdapter(bankNamesAdapter);
                } else {
                    accountTypeSp.setSelection(0, true);
                }
            }

            @Override
            public void onError(String error) {
                accountTypeSp.setSelection(0, true);
            }
        }, LOVsType.BANKS_LOVS.lovsRequestModel);

        APIManager.getInstance().getLovs(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    ArrayList<LOVResponseModel> arrayList = (ArrayList<LOVResponseModel>) ((GeneralResponse) response.body()).getData();
                    citysAdapter = new GenericCustomSpinnerAdapter(AccountDetailsActivity.this, R.id.spinner_item_tv, R.id.spinner_item_tv, arrayList);
                    citySp.setAdapter(citysAdapter);
                } else {
                    accountTypeSp.setSelection(0, true);
                }
            }

            @Override
            public void onError(String error) {
                accountTypeSp.setSelection(0, true);
            }
        }, LOVsType.CITES_LOVS.lovsRequestModel);
    }

    private void updateUI() {
        accountTitleTv.setHint(accountType == 0 ? "Account Title" : "Title");
        accountNumberTv.setHint(accountType == 0 ? "Account No" : "Mobile No");
        if (accountType == 0) {
            accountNumberTv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(24)});
            accountNumberTv.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            bankNamesSp.setAdapter(bankNamesAdapter);
        } else if (accountType == 1) {
            accountNumberTv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
            accountNumberTv.setImeOptions(EditorInfo.IME_ACTION_DONE);
            bankNamesSp.setAdapter(serviceProvideAdapter);
        }


        branchCodeTv.setVisibility(accountType == 0 ? View.VISIBLE : View.GONE);
//        servicProviderRl.setVisibility(accountType == 0 ? View.GONE : View.VISIBLE);
        cityRl.setVisibility(accountType == 0 ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.accountTypeSpinner) {
            accountType = position;
            updateUI();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveChangesBtn) {
            if (validate()) {
                addAccountToServer();
            }
        } else if (view.getId() == R.id.backIv) {
            super.onBackPressed();
        }
    }

    private boolean validate() {
        if (Validations.isEmpty(accountTitleTv.getText())) {
            accountTitleTv.setError("Please enter account title!");
            accountTitleTv.requestFocus();
            return false;
        } else if (Validations.isEmpty(accountNumberTv.getText())) {
            accountNumberTv.setError("Please enter " + (accountType == 0 ? "Account Number!" : " Mobile Number!"));
            accountNumberTv.requestFocus();
            return false;
        } else if (accountType == 0 && accountNumberTv.getText().toString().length() < 14) {
            accountNumberTv.setError("Minimun lenght of account number is 14");
            accountNumberTv.requestFocus();
            return false;
        } else if (accountType == 1 && accountNumberTv.getText().toString().length() < 11 && !Constants.phoneRegex(accountNumberTv.getText().toString())) {
            accountNumberTv.setError("Please enter valid mobile number");
            accountNumberTv.requestFocus();
            return false;
        } else if (Validations.isEmpty(branchCodeTv.getText()) && accountType == 0) {
            branchCodeTv.setError("Please enter account account number");
            branchCodeTv.requestFocus();
            return false;
        }
        accountDetails = new AccountDetails();
        accountDetails.setAccountTitle(accountTitleTv.getText().toString());
        accountDetails.setAccountNo(accountNumberTv.getText().toString());
        accountDetails.setBranchCode(accountTypeSp.getSelectedItemPosition() == 0 ? branchCodeTv.getText().toString() : "0");
        accountDetails.setCityID(accountTypeSp.getSelectedItemPosition() == 0 ? Integer.parseInt(((LOVResponseModel) citySp.getSelectedItem()).getId()) : null);
        accountDetails.setProfileID(profileId);
        accountDetails.setBankID(accountTypeSp.getSelectedItemPosition() == 0 ? Integer.parseInt(((LOVResponseModel) bankNamesSp.getSelectedItem()).getId()) : 0);
        accountDetails.setAccounType(accountTypeSp.getSelectedItemPosition() == 0 ? "Bank" : "Mobile");

        try {
            SharedPreferences mPrefs = getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
            String json = mPrefs.getString(Constants.userLoginKey, "");
            JSONObject jsonUser = new JSONObject(json);
            UserInfo userInfo = new UserInfo();
            userInfo.setMsisdn(jsonUser.getString("msisdn"));
            userInfo.setSessionId(jsonUser.optString("sessionID"));
            accountDetails.setUserInfo(userInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addAccountToServer() {
        APIManager.getInstance().addAccountDetails(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    if (Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE)
                        startActivity(new Intent(AccountDetailsActivity.this, RegistrationFeeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    else {
                        TutorApp.userInfo.setProfileStatus("3");
                        Persister.setUser(AccountDetailsActivity.this, TutorApp.userInfo);
                        Intent intent = new Intent(AccountDetailsActivity.this, AvailabilityStatusActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("profileId", profileId);
                        startActivity(intent);
                    }
                } else {
                    DialogHelper.showMessageDialog(AccountDetailsActivity.this, "Error", ((GeneralResponse) response.body()).getMessage());
                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(AccountDetailsActivity.this, "Error", error);

            }
        }, accountDetails);
    }
}
