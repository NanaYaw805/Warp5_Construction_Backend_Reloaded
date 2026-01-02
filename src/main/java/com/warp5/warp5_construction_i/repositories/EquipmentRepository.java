package com.warp5.warp5_construction_i.repositories;

import com.warp5.warp5_construction_i.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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



    @Query(
            value = """
    SELECT e FROM Equipment e
    WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')))
    AND (:location IS NULL OR LOWER(e.location) = LOWER(:location))
    AND (:minPrice IS NULL OR e.price IS NOT NULL AND e.price >= :minPrice)
    AND (:maxPrice IS NULL OR e.price IS NOT NULL AND e.price <= :maxPrice)
    AND (:minRating IS NULL OR e.rating IS NOT NULL AND e.rating >= :minRating)
    """,
            countQuery = """
    SELECT COUNT(e) FROM Equipment e
    WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')))
    AND (:location IS NULL OR LOWER(e.location) = LOWER(:location))
    AND (:minPrice IS NULL OR e.price IS NOT NULL AND e.price >= :minPrice)
    AND (:maxPrice IS NULL OR e.price IS NOT NULL AND e.price <= :maxPrice)
    AND (:minRating IS NULL OR e.rating IS NOT NULL AND e.rating >= :minRating)
    """
    )
    Page<Equipment> searchEquipment(
            @Param("name") String name,
            @Param("location") String location,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minRating") Double minRating,
            Pageable pageable
    );

}





