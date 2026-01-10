package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.PaymentRequest;
import com.warp5.warp5_construction_i.dtos.PaymentResponse;
import com.warp5.warp5_construction_i.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponse> initiatePayment(
            @RequestBody PaymentRequest request
    ) {
        return ResponseEntity.ok(
                paymentService.initiatePayment(
                        request.getOwnerId(),
                        request.getRenterId(),
                        request.getEmail(),
                        request.getRenterName(),
                        request.getAmount()
                )
        );
    }
}


