package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.EquipmentRequest;
import com.warp5.warp5_construction_i.dtos.EquipmentResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EquipmentService {

    EquipmentResponse createEquipment(EquipmentRequest request);

    List<EquipmentResponse> getAllEquipment();


    EquipmentResponse getEquipmentById(Long id);

    List<EquipmentResponse> getHighlyRatedEquipment(double minRating);

    List<EquipmentResponse> getMostViewed(int limit);

    public List<EquipmentResponse> getRecommendations(
            Long id, int limit
    );
}
