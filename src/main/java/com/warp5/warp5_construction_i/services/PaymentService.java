package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.PaymentResponse;
import com.warp5.warp5_construction_i.dtos.PaystackInitializeResponse;
import com.warp5.warp5_construction_i.enums.PaymentStatus;
import com.warp5.warp5_construction_i.model.Payment;
import com.warp5.warp5_construction_i.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaystackClient paystackClient;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaystackClient paystackClient, PaymentRepository paymentRepository) {
        this.paystackClient = paystackClient;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse initiatePayment(
            Long ownerId,
            Long renterId,
            String renterEmail,
            String renterName,
            double rentalAmount
    ) {
        String reference = "WARP5_" + UUID.randomUUID();

        // 1. Save payment first
        Payment payment = new Payment();
        payment.setOwnerId(ownerId);
        payment.setRenterId(renterId);
        payment.setAmount(rentalAmount);
        payment.setReference(reference);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentProvider("PAYSTACK");

        PaystackInitializeResponse paystackResponse =
                paystackClient.initializeTransaction(
                        renterEmail,
                        rentalAmount,
                        reference
                );

        if (!paystackResponse.isStatus()) {
            throw new RuntimeException("Payment initialization failed");
        }

        PaymentResponse response = new PaymentResponse();
        response.setAuthorizationUrl(
                paystackResponse.getData().getAuthorization_url()
        );
        response.setReference(reference);
        response.setStatus("PENDING");

        return response;
    }
}

