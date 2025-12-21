package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.EquipmentRequest;
import com.warp5.warp5_construction_i.dtos.EquipmentResponse;
import com.warp5.warp5_construction_i.model.Equipment;
import com.warp5.warp5_construction_i.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }


    @Override
    public EquipmentResponse createEquipment(EquipmentRequest request) {
        Equipment equipment = new Equipment();
        equipment.setName(request.getName());
        equipment.setOwnerId(request.getOwnerId());
        equipment.setOwnerName(request.getOwnerName());
        equipment.setLocation(request.getLocation());
        equipment.setDescription(request.getDescription());
        equipment.setPrice(request.getPrice());
        equipment.setRating(request.getRating());
        equipment.setAvailability(request.getAvailability());
        equipment.setImageOne(request.getImageOne());
        equipment.setImageTwo(request.getImageTwo());
        equipment.setImageThree(request.getImageThree());

        Equipment saved = equipmentRepository.save(equipment);

        return mapToResponse(saved);
    }

    @Override
    public List<EquipmentResponse> getAllEquipment() {
        return equipmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EquipmentResponse getEquipmentById(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        return mapToResponse(equipment);
    }

    @Override
    public List<EquipmentResponse> getHighlyRatedEquipment(double minRating) {
       return  equipmentRepository.findHighlyRated(minRating)
               .stream()
               .map(this::mapToResponse)
               .toList();

    }

    private EquipmentResponse mapToResponse(Equipment equipment) {
        EquipmentResponse response = new EquipmentResponse();
        response.setId(equipment.getId());
        response.setName(equipment.getName());
        response.setOwnerId(equipment.getOwnerId());
        response.setOwnerName(equipment.getOwnerName());
        response.setLocation(equipment.getLocation());
        response.setDescription(equipment.getDescription());
        response.setPrice(equipment.getPrice());
        response.setRating(equipment.getRating());
        response.setAvailability(equipment.isAvailability());
        response.setImageOne(equipment.getImageOne());
        response.setImageTwo(equipment.getImageTwo());
        response.setImageThree(equipment.getImageThree());
        response.setCreatedAt(equipment.getCreatedAt());
        return response;
    }
}
