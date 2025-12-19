package com.warp5.warp5_construction_i.repositories;

import com.warp5.warp5_construction_i.model.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {

    List<RentalRequest> findByOwnerId(Long ownerId);

    List<RentalRequest> findByRenterId(Long renterId);

}
