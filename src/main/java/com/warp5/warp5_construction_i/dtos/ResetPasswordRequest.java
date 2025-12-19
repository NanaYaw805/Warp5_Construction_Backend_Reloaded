package com.warp5.warp5_construction_i.dtos;

public class ResetPasswordRequest {

    private String resetToken;   // Token generated during forgot password
    private String newPassword;  // New password to set

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
