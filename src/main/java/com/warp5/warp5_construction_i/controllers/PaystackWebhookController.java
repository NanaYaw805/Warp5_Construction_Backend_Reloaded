package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.PaymentRequest;
import com.warp5.warp5_construction_i.dtos.PaymentResponse;
import com.warp5.warp5_construction_i.services.PaymentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HexFormat;

@RestController
@RequestMapping("/webhooks/paystack")
public class PaystackWebhookController {


    private String secretKey="sk_live_964377be3e9e01cad184b88d93547b1294b4b8e2";

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("x-paystack-signature") String signature
    ) throws JSONException {
        if (!isValidSignature(payload, signature)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Parse event
        JSONObject json = new JSONObject(payload);
        String event = json.getString("event");

        if ("charge.success".equals(event)) {
            JSONObject data = json.getJSONObject("data");
            String reference = data.getString("reference");

            // ✅ Mark payment as PAID
            // updateRentalPayment(reference)
        }

        return ResponseEntity.ok("OK");
    }

    private boolean isValidSignature(String payload, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA512"));
            byte[] hash = mac.doFinal(payload.getBytes());
            String computed = HexFormat.of().formatHex(hash);
            return computed.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }
}
