package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.RentalRequestCreateDto;
import com.warp5.warp5_construction_i.dtos.RentalRequestResponse;

import java.util.List;

public interface RentalRequestService {

    RentalRequestResponse create(RentalRequestCreateDto dto);

    List<RentalRequestResponse> getRequestsForOwner(Long ownerId);

    List<RentalRequestResponse> getRequestsForRenter(Long renterId);

    RentalRequestResponse decide(Long requestId, String decision);
}

