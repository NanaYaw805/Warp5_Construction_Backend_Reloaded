package com.warp5.warp5_construction_i.repositories;

import com.warp5.warp5_construction_i.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query("""
            SELECT e FROM Equipment e
            WHERE e.rating IS NOT NULL
            AND e.rating >= :minRating
            ORDER BY e.rating DESC
            """)
    List<Equipment> findHighlyRated(double minRating);

}
