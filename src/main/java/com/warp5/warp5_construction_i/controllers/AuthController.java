package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.*;
import com.warp5.warp5_construction_i.model.User;
import com.warp5.warp5_construction_i.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ------------------ REGISTER ------------------
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    // ------------------ LOGIN ------------------
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return ResponseEntity.ok(user);
    }

    // ------------------ UPDATE PROFILE ------------------
    @PutMapping("/update-profile/{userId}")
    public ResponseEntity<User> updateProfile(
            @PathVariable Long userId,
            @RequestBody UpdateProfileRequest request) {
        User updatedUser = authService.updateProfile(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    // ------------------ FORGOT PASSWORD ------------------
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        String resetToken = authService.forgotPassword(request.getEmailOrPhone());
        return ResponseEntity.ok("Reset token: " + resetToken);
    }

    // ------------------ RESET PASSWORD ------------------
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        String result = authService.resetPassword(request.getResetToken(), request.getNewPassword());
        return ResponseEntity.ok(result);
    }
}
