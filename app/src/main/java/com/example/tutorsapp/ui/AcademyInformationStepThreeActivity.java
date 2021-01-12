package com.example.tutorsapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.AcademySpinnerAdapter;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.AcademyCategories;
import com.example.tutorsapp.models.AcademyDetailsModel;
import com.example.tutorsapp.models.AcademyFeatureModel;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.OwnerModel;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.example.tutorsapp.ui.dialogs.ClassEnvironmentDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static com.example.tutorsapp.TutorApp.getContext;

public class AcademyInformationStepThreeActivity extends BaseActivity implements View.OnClickListener, APIManager.CallbackGenric {
    private TextView classEnvTv;
    private Button saveBtn;
    private Spinner academyListSp;
    private ClassEnvironmentDialog classEnvironmentDialog;
    private boolean selected = false;
    private int ownerID = 1;
    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy_information_step_three);
        initViews();
    }

    private void initViews() {
        classEnvTv = findViewById(R.id.classEnvTv);
        classEnvTv.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            ownerID = getIntent().getIntExtra("OwnerID", 1);
        } else {
            ownerID = 1;
        }
        saveBtn = findViewById(R.id.saveBtn);
        backIv = findViewById(R.id.backIv);
        saveBtn.setOnClickListener(this);
        backIv.setOnClickListener(this);

        academyListSp = findViewById(R.id.academyListSp);
        obtainAcademies();
//        academyListSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.academiesArray)))));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.classEnvTv) {
            APIManager.getInstance().getAcademyFetures(new APIManager.CallbackGenric() {
                @Override
                public void onResult(boolean z, Response response) {
                    if (((GeneralResponse) response.body()).getIsSuccess()) {
                        ArrayList<AcademyCategories> arrayList = (ArrayList<AcademyCategories>) ((GeneralResponse) response.body()).getData();
                        showClassEnvDialog(arrayList);
                    }
                }

                @Override
                public void onError(String error) {

                }
            }, new Object());

        } else if (view.getId() == R.id.saveBtn) {
            if (selected)
                addInfo();
            else
                Toast.makeText(getContext(), "Please select facilities offered", Toast.LENGTH_SHORT).show();

            /* Intent intent = new Intent(this, AcademyInformationStepFourActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
        } else if (view.getId() == R.id.backIv) {
            super.onBackPressed();
        }
    }

    private void showClassEnvDialog(ArrayList<AcademyCategories> arrayList) {
        classEnvironmentDialog = new ClassEnvironmentDialog(this, arrayList);
        selected = true;
        classEnvironmentDialog.show();
    }

    private void addInfo() {    // API is returning usuported media stype
        //get academy features
        AcademyFeatureModel academyFeatureModel = classEnvironmentDialog.getClassEnvironment();
        try {
            SharedPreferences mPrefs = getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
            String json = mPrefs.getString(Constants.userLoginKey, "");
            JSONObject jsonUser = new JSONObject(json);
            UserInfo userInfo = new UserInfo();
            userInfo.setMsisdn(jsonUser.getString("msisdn"));
            userInfo.setSessionId(jsonUser.optString("sessionID"));
            academyFeatureModel.setUserInfo(userInfo);
            academyFeatureModel.setChannelID("0");
            academyFeatureModel.setAcademyID(((AcademyDetailsModel) academyListSp.getSelectedItem()).getAcademyID());
            APIManager.getInstance().addAcademyFeatures(this, academyFeatureModel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void obtainAcademies() {
        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setOwnerID(ownerID);
        APIManager.getInstance().getAcademiesInfo(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (response.isSuccessful()) {
                    GeneralResponse generalResponse = (GeneralResponse) response.body();
                    if (generalResponse.getIsSuccess()) {
                        ArrayList<AcademyDetailsModel> academyDetailsModels = (ArrayList<AcademyDetailsModel>) generalResponse.getData();
                        academyListSp.setAdapter(new AcademySpinnerAdapter(AcademyInformationStepThreeActivity.this, R.id.spinner_item_tv, R.id.spinner_item_tv, academyDetailsModels));
                    } else {
                        //todo show error dialog
                    }
                } else {
                    //todo show error dialog
                }
            }

            @Override
            public void onError(String error) {
                //todo show error dialog
            }
        }, ownerModel);
    }

    @Override
    public void onResult(boolean z, Response response) {
        if (((GeneralResponse) Objects.requireNonNull(response.body())).getIsSuccess()) {
            showToastBar("Academy Features Added", AcademyInformationStepThreeActivity.this);
            startActivity(new Intent(AcademyInformationStepThreeActivity.this, AcademyInformationStepFourActivity.class)
                    .putExtra("OwnerID", ownerID));
        } else {
            showToastBar(((GeneralResponse) response.body()).getMessage(), AcademyInformationStepThreeActivity.this);

        }
    }

    @Override
    public void onError(String error) {

    }
}