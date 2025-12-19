package com.warp5.warp5_construction_i.dtos;

public class ForgotPasswordRequest {

    // User can provide email or phone number
    private String emailOrPhone;

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }
}
