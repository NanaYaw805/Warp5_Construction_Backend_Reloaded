package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.CreateReservationRequest;
import com.warp5.warp5_construction_i.model.Reservation;
import com.warp5.warp5_construction_i.services.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(
            @RequestBody CreateReservationRequest request
    ) {
        return reservationService.createReservation(request);
    }
}
