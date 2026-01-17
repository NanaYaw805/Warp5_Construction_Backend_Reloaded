package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.CreateReservationRequest;
import com.warp5.warp5_construction_i.enums.ReservationStatus;
import com.warp5.warp5_construction_i.model.Reservation;
import com.warp5.warp5_construction_i.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

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
        reservation.setRenterId(request.getRenterId());
        reservation.setOwnerId(request.getOwnerId());
        reservation.setStatus(ReservationStatus.PENDING);

        return reservationRepository.save(reservation);
    }
}
