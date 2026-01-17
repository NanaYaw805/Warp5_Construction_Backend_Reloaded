package com.warp5.warp5_construction_i.repositories;

import com.warp5.warp5_construction_i.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findTop10ByOwnerIdOrderByCreatedAtDesc(Long ownerId);

    List<Reservation> findTop10ByRenterIdOrderByCreatedAtDesc(Long renterId);

}
