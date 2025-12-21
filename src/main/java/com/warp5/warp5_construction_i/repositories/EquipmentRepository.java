package com.warp5.warp5_construction_i.repositories;

import com.warp5.warp5_construction_i.model.Equipment;
import org.springframework.data.domain.Pageable;
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


    @Query("""
    SELECT e FROM Equipment e
    ORDER BY e.viewCount DESC
""")
    List<Equipment> findMostViewed(Pageable pageable);

    @Query("""
    SELECT e FROM Equipment e
    WHERE e.id <> :id
      AND (:location IS NULL OR e.location = :location)
      AND e.price BETWEEN :minPrice AND :maxPrice
    ORDER BY e.rating DESC
""")
    List<Equipment> findRecommended(
            Long id,
            String location,
            Double minPrice,
            Double maxPrice,
            Pageable pageable
    );

}
