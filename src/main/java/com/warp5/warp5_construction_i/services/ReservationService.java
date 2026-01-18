package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.CreateReservationRequest;
import com.warp5.warp5_construction_i.dtos.ReservationDashboardResponse;
import com.warp5.warp5_construction_i.enums.ReservationStatus;
import com.warp5.warp5_construction_i.model.Reservation;
import com.warp5.warp5_construction_i.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(CreateReservationRequest request) {

        Reservation reservation = new Reservation();
        reservation.setEquipmentId(request.getEquipmentId());
        reservation.setEquipmentName(request.getEquipmentName());
        reservation.setOwnerId(request.getOwnerId());
        reservation.setRenterId(request.getRenterId());
        reservation.setRentalAmount(request.getRentalAmount());
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());

        reservation.setStatus(ReservationStatus.PENDING);

        return reservationRepository.save(reservation);
    }




}
