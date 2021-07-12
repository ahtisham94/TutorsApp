package com.example.tutorsapp.network;

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
import com.example.tutorsapp.models.jobsModels.GetJobRequestMainResponse;
import com.example.tutorsapp.models.jobsModels.GetJobsModel;
import com.example.tutorsapp.models.jobsModels.GetJobsResponseModel;
import com.example.tutorsapp.models.login.LoginRequestModel;
import com.example.tutorsapp.models.login.LoginValidateOTPRequest;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GetDataService {
//    @Multipart
//    @POST("/Teacher/AddTeacherBasicInfo")
//    Call<GeneralResponse> addTeacherBasicInfo(
//            @Header("Content-Type") String contentType,
//            @PartMap Map<String, RequestBody> params);

    @POST("/Teacher/AddTeacherBasicInfo")
    Call<GeneralResponse<Integer>> addTeacherBasicInfo(@Body RequestBody body);


    @POST("/Teacher/AddTeacherBasicInfo")
    Call<GeneralResponse<Integer>> addTeacherBasicInfoWithoutsMultipart(
            @Header("Content-Type") String contentType,
            @Body TeacherModel body);

    @POST("/Teacher/AddEducationalDetails")
    Call<GeneralResponse> addEducationInfo(
            @Header("Content-Type") String contentType,
            @Body EducationModel body);

    @POST("/Teacher/AddAccountDetails")
    Call<GeneralResponse> addAccountDetails(
            @Header("Content-Type") String contentType,
            @Body AccountDetails body);

    @POST("/Account/GenerateOTP")
    Call<GeneralResponse> generateOTP(
            @Header("Content-Type") String contentType,
            @Body LoginRequestModel model);

    @POST("/Account/ValidateOTP")
    Call<GeneralResponse> validateOTP(@Header("Content-Type") String contentType, @Body LoginValidateOTPRequest loginValidateOTPRequest);

    @POST("/Teacher/AddExperience")
    Call<GeneralResponse> addExperience(@Header("Content-Type") String contentType, @Body ExperienceDetailModel experienceDetailModel);

    @POST("/Teacher/AddPreferedArea")
    Call<GeneralResponse> addPreferedArea(@Header("Content-Type") String contentType, @Body PreferedAreaModelRequest experienceDetailModel);

    @POST("/Teacher/GetLOVS")
    Call<GeneralResponse<ArrayList<LOVResponseModel>>> getLovs(@Header("Content-Type") String contentType, @Body LOVSRequestModel lovsRequestModel);

    @POST("/Teacher/GetTeachingCategoryLOVS")
    Call<GeneralResponse<ArrayList<LOVCategoryResponseModel>>> getCategoryLovs(@Header("Content-Type") String contentType, @Body LOVSRequestModel lovsRequestModel);

    @POST("/Teacher/AddTeacherAvailability")
    Call<GeneralResponse> addTeacherAvailability(@Header("Content-Type") String contentType, @Body AddTeacherAvailiblityModelRequest addTeacherAvailiblityModelRequest);

    @POST("/Teacher/AddEducationalDetailsMain")
    Call<GeneralResponse> addEducationMain(@Body RequestBody body);

    @POST("/Academy/GetAcademyFeatureLOVS")
    Call<GeneralResponse<ArrayList<AcademyCategories>>> getAcademyFetures(@Header("Content-Type") String contentType, @Body Object body);

    @POST("/Academy/AddAcademyOwnerInfo")
    Call<GeneralResponse<Integer>> addAcademyOwnerInfo(@Body RequestBody body);

    @POST("/Academy/AddAcademyInfo")
    Call<GeneralResponse> addAcademyInfo(@Body RequestBody body);

    @POST("/Academy/AddAcademyFeatures")
    Call<GeneralResponse> addAcademyFeatures(@Header("Content-Type") String contentType, @Body AcademyFeatureModel body);

    @POST("/Academy/AddAcademySchedule")
    Call<GeneralResponse> addAcademySchedule(@Body RequestBody body);

    @POST("/Academy/AddAcademyTeacherProfile")
    Call<GeneralResponse> addAcademyTeacher(@Body RequestBody body);

    @POST("/Teacher/ShareDetailsRequest")
    Call<GeneralResponse> shareTeacherDetails(@Header("Content-Type") String contentType, @Body ShareTecherDetailsModel body);

    @POST("/Teacher/GetTeacherSendedRequests")
    Call<GeneralResponse<List<RequestModel>>> getTeacherSentRequests(@Body RequestModel body);

    @POST("/Academy/GetAcademiesInfo")
    Call<GeneralResponse<ArrayList<AcademyDetailsModel>>> getAcademiesInfo(@Header("Content-Type") String contentType, @Body OwnerModel body);

    @POST("/Teacher/AcceptRejectAssignmentRequest")
    Call<GeneralResponse> acceptRejectRequest(@Header("Content-Type") String contentType, @Body RequestModel body);

    @POST("/Teacher/GetRequestDetails")
    Call<GeneralResponse<List<AssignmentDetails>>> getRequestDetails(@Header("Content-Type") String contentType, @Body RequestModel body);

    @POST("/Job/GetJobs")
    Call<GeneralResponse<GetJobRequestMainResponse>> getJobs(@Header("Content-Type") String contentType, @Body GetJobsModel body);
}
