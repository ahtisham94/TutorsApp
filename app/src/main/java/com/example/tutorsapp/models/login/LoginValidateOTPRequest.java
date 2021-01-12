package com.example.tutorsapp.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginValidateOTPRequest {

    @SerializedName("Otp")
    @Expose
    private int otp;

    @SerializedName("MSISDN")
    @Expose
    private String msisdn;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public LoginValidateOTPRequest(int otp, String msisdn) {
        this.otp = otp;
        this.msisdn = msisdn;
    }


}
