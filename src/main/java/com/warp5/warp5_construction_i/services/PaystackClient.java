package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.PaystackInitializeRequest;
import com.warp5.warp5_construction_i.dtos.PaystackInitializeResponse;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaystackClient {


    private String secretKey="sk_live_964377be3e9e01cad184b88d93547b1294b4b8e2";


    private String baseUrl="https://api.paystack.co";

    private final RestTemplate restTemplate = new RestTemplate();

    public PaystackInitializeResponse initializeTransaction(
            String email,
            double amount,
            String reference
    ) {
        PaystackInitializeRequest request = new PaystackInitializeRequest();
        request.setEmail(email);
        request.setAmount((long) (amount * 100)); // GHS → kobo
        request.setReference(reference);
        request.setChannels(List.of("card", "mobile_money"));

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        ((org.springframework.http.HttpHeaders) headers).setBearerAuth(secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaystackInitializeRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<PaystackInitializeResponse> response =
                restTemplate.postForEntity(
                        baseUrl + "/transaction/initialize",
                        entity,
                        PaystackInitializeResponse.class
                );

        return response.getBody();
    }
}
