package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.PaystackInitResponse;

public interface PaymentService {

    PaystackInitResponse initializePayment(Long rentalRequestId, Long renterId);


}
