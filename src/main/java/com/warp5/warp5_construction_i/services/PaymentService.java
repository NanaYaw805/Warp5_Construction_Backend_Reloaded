package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.PaymentResponse;
import com.warp5.warp5_construction_i.dtos.PaystackInitializeResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaystackClient paystackClient;

    public PaymentService(PaystackClient paystackClient) {
        this.paystackClient = paystackClient;
    }

    public PaymentResponse initiatePayment(
            Long ownerId,
            Long renterId,
            String renterEmail,
            String renterName,
            double rentalAmount
    ) {
        String reference = "WARP5_" + UUID.randomUUID();

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

