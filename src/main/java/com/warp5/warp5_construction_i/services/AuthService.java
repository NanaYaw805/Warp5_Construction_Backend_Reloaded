package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.LoginRequest;
import com.warp5.warp5_construction_i.dtos.RegisterRequest;
import com.warp5.warp5_construction_i.dtos.UpdateProfileRequest;
import com.warp5.warp5_construction_i.model.User;
import com.warp5.warp5_construction_i.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // --------------------------------------------------
    // REGISTER A NEW USER
    // --------------------------------------------------
    public User register(RegisterRequest request) {

        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Check if phone exists
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setAddressLine1(request.getAddressLine1());
        user.setAddressLine2(request.getAddressLine2());
        user.setCity(request.getCity());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDate.now());

        return userRepository.save(user);
    }

    // --------------------------------------------------
    // LOGIN USING EMAIL OR PHONE + PASSWORD
    // --------------------------------------------------
    public User login(LoginRequest request) {
        Optional<User> userOpt;

        // Determine login method
        if (request.getLoginId().contains("@")) {
            userOpt = userRepository.findByEmail(request.getLoginId());
        } else {
            userOpt = userRepository.findByPhoneNumber(request.getLoginId());
        }

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();

        // Validate password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // --------------------------------------------------
    // UPDATE PROFILE
    // --------------------------------------------------
    public User updateProfile(Long userId, UpdateProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields only if provided
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getDateOfBirth() != null) user.setDateOfBirth(request.getDateOfBirth());
        if (request.getAddressLine1() != null) user.setAddressLine1(request.getAddressLine1());
        if (request.getAddressLine2() != null) user.setAddressLine2(request.getAddressLine2());
        if (request.getCity() != null) user.setCity(request.getCity());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if (request.getEmail() != null) user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    // --------------------------------------------------
    // FORGOT PASSWORD – SEND RESET TOKEN
    // --------------------------------------------------
    public String forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        // Generate unique reset token
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);

        userRepository.save(user);

        // In real system → send via email
        return resetToken;
    }

    // --------------------------------------------------
    // RESET PASSWORD USING TOKEN
    // --------------------------------------------------
    public String resetPassword(String token, String newPassword) {

        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid reset token"));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);

        userRepository.save(user);

        return "Password reset successful";
    }
}
