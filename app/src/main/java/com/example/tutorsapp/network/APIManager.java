package com.example.tutorsapp.network;

import android.app.Dialog;
import android.util.Log;

import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.models.AcademyCategories;
import com.example.tutorsapp.models.AcademyFeatureModel;
import com.example.tutorsapp.models.AcademyDetailsModel;
import com.example.tutorsapp.models.AccountDetails;
import com.example.tutorsapp.models.AddTeacherAvailiblityModelRequest;
import com.example.tutorsapp.models.AssignmentDetails;
import com.example.tutorsapp.models.EducationModel;
import com.example.tutorsapp.models.ExperienceDetailModel;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.models.LOVSRequestModel;
import com.example.tutorsapp.models.OwnerModel;
import com.example.tutorsapp.models.PreferedAreaModelRequest;
import com.example.tutorsapp.models.RequestModel;
import com.example.tutorsapp.models.ShareTecherDetailsModel;
import com.example.tutorsapp.models.TeacherModel;
import com.example.tutorsapp.models.login.LoginRequestModel;
import com.example.tutorsapp.models.login.LoginValidateOTPRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class APIManager {
    public static APIManager instance = new APIManager();
    public static Retrofit retrofit;

    public static APIManager getInstance() {

        return instance;
    }

    private APIManager() {
        retrofit = ClientInstance.getRetrofitInstance();
    }

    private void getMakesLOVS() {
    }

    public void loginGenerateOTp(CallbackGenric callback, LoginRequestModel requestModel) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.generateOTP("application/json", requestModel);
        sendResultGeneric(result, callback);
    }

    public void loginValidateOTp(CallbackGenric callback, LoginValidateOTPRequest requestModel) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.validateOTP("application/json", requestModel);
        sendResultGeneric(result, callback);
    }


    public void addTeacherBasicInfo(CallbackGenric callback, TeacherModel requestModel) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<Integer>> result = service.addTeacherBasicInfoWithoutsMultipart("application/json", requestModel);
        sendResultGeneric(result, callback);
    }


    public void addEducationalDetails(CallbackGenric callback, EducationModel requestModel) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addEducationInfo("application/json", requestModel);
        sendResultGeneric(result, callback);
    }


    public void addTeacherBasicInfo(CallbackGenric callback, RequestBody body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<Integer>> result = Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE ? service.addAcademyOwnerInfo(body) : service.addTeacherBasicInfo(body);
        sendResultGeneric(result, callback);
    }


    public void addExperience(CallbackGenric callback, ExperienceDetailModel body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addExperience("application/json", body);
        sendResultGeneric(result, callback);
    }


    public void addEducationDetailMain(CallbackGenric callback, RequestBody body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addEducationMain(body);
        sendResultGeneric(result, callback);
    }


    public void addAccountDetails(CallbackGenric callback, AccountDetails body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addAccountDetails("application/json", body);
        sendResultGeneric(result, callback);
    }


    public void addPreferedArea(CallbackGenric callback, PreferedAreaModelRequest body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addPreferedArea("application/json", body);
        sendResultGeneric(result, callback);
    }


    public void addTeacherAvailability(CallbackGenric callback, AddTeacherAvailiblityModelRequest body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addTeacherAvailability("application/json", body);
        sendResultGeneric(result, callback);
    }


    public void getLovs(CallbackGenric callback, LOVSRequestModel lovsRequestModel) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<ArrayList<LOVResponseModel>>> result = service.getLovs("application/json", lovsRequestModel);
        sendResultGeneric(result, callback);
    }


    public void getCategoryLovs(CallbackGenric callback, LOVSRequestModel lovsRequestModel) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<ArrayList<LOVCategoryResponseModel>>> result = service.getCategoryLovs("application/json", lovsRequestModel);
        sendResultGeneric(result, callback);
    }


    public void addAcademyInfo(CallbackGenric callback, RequestBody body) {

        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addAcademyInfo(body);
        sendResultGeneric(result, callback);
    }


    public void getAcademyFetures(CallbackGenric callback, Object o) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<ArrayList<AcademyCategories>>> result = service.getAcademyFetures("application/json", o);
        sendResultGeneric(result, callback);
    }

    public void addAcademyFeatures(CallbackGenric callback, AcademyFeatureModel body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addAcademyFeatures("application/json", body);
        sendResultGeneric(result, callback);
    }

    public void addAcademySchedule(CallbackGenric callback, RequestBody body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addAcademySchedule(body);
        sendResultGeneric(result, callback);
    }

    public void addAcademyTeacherProfile(CallbackGenric callback, RequestBody body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.addAcademyTeacher(body);
        sendResultGeneric(result, callback);
    }

    public void getTeacherSentRequests(CallbackGenric callback, RequestModel body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<List<RequestModel>>> result = service.getTeacherSentRequests(body);
        sendResultGeneric(result, callback);
    }

    public void getAcademiesInfo(CallbackGenric callback, OwnerModel body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<ArrayList<AcademyDetailsModel>>> result = service.getAcademiesInfo("application/json", body);
        sendResultGeneric(result, callback);
    }

    public void acceptRejectRequest(CallbackGenric callback, RequestModel body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.acceptRejectRequest("application/json", body);
        sendResultGeneric(result, callback);
    }

    public void getRequestDetails(CallbackGenric callback, RequestModel body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse<List<AssignmentDetails>>> result = service.getRequestDetails("application/json", body);
        sendResultGeneric(result, callback);
    }

    public void shareTeacherDetails(CallbackGenric callback, ShareTecherDetailsModel body) {
        GetDataService service = retrofit.create(GetDataService.class);
        Call<GeneralResponse> result = service.shareTeacherDetails("application/json", body);
        sendResultGeneric(result, callback);
    }

    private <T> void sendResultGeneric(Call<T> call, final CallbackGenric result) {
        Dialog dialog = null;
        if (TutorApp.getInstance().getmCurrentActivity() != null && !TutorApp.getInstance().getmCurrentActivity().isFinishing()) {
            dialog = DialogHelper.showLoadingDialog(TutorApp.getInstance().getmCurrentActivity());
        }

        if (dialog != null)
            dialog.show();
        Dialog finalDialog = dialog;
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    if (finalDialog != null)
                        finalDialog.dismiss();
                } catch (Exception e) {

                }

                if (response.isSuccessful()) {
                    result.onResult(true, response);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        result.onError(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.i("ex", "API Manager Exception");
                        result.onError(e.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                try {

                    if (finalDialog != null)
                        finalDialog.dismiss();
                } catch (Exception e) {

                }
                result.onError(t.getMessage());
            }
        });
    }


    public interface Callback {
        void onResult(boolean z, String response);
    }

    public interface CallbackGenric<T> {
        void onResult(boolean z, Response response);

        void onError(String error);
    }
}