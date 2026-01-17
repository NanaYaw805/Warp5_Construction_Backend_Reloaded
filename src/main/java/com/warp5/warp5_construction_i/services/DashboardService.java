package com.warp5.warp5_construction_i.services;


import com.warp5.warp5_construction_i.dtos.ReservationDashboardResponse;
import com.warp5.warp5_construction_i.model.Reservation;
import com.warp5.warp5_construction_i.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {


    private final ReservationRepository reservationRepository;

    public DashboardService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public List<ReservationDashboardResponse> getOwnerRecentReservations(Long ownerId){
        return reservationRepository.findTop10ByOwnerIdOrderByCreatedAtDesc(ownerId)
                .stream()
                .map(this::mapToDashboard)
                .toList();
    }

    public List<ReservationDashboardResponse> getRenterRecentReservations(Long renterId){
        return reservationRepository.findTop10ByRenterIdOrderByCreatedAtDesc(renterId)
                .stream()
                .map(this::mapToDashboard)
                .toList();
    }

    private ReservationDashboardResponse mapToDashboard(Reservation reservation){
        return new ReservationDashboardResponse(
                reservation.getId(),
                reservation.getEquipmentName(),
                reservation.getStatus().name()
        );
    }

}
