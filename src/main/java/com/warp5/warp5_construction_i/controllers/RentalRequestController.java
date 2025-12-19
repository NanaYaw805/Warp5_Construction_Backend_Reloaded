package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.RentalDecisionDto;
import com.warp5.warp5_construction_i.dtos.RentalRequestCreateDto;
import com.warp5.warp5_construction_i.dtos.RentalRequestResponse;
import com.warp5.warp5_construction_i.services.RentalRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalRequestController {

    private final RentalRequestService service;

    public RentalRequestController(RentalRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RentalRequestResponse> create(
            @RequestBody RentalRequestCreateDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RentalRequestResponse>> ownerRequests(
            @PathVariable Long ownerId) {
        return ResponseEntity.ok(service.getRequestsForOwner(ownerId));
    }

    @GetMapping("/renter/{renterId}")
    public ResponseEntity<List<RentalRequestResponse>> renterRequests(
            @PathVariable Long renterId) {
        return ResponseEntity.ok(service.getRequestsForRenter(renterId));
    }

    @PatchMapping("/{id}/decision")
    public ResponseEntity<RentalRequestResponse> decide(
            @PathVariable Long id,
            @RequestBody RentalDecisionDto dto) {
        return ResponseEntity.ok(service.decide(id, dto.getDecision()));
    }
}

