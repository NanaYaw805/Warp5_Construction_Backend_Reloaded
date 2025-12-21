package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.EquipmentRequest;
import com.warp5.warp5_construction_i.dtos.EquipmentResponse;
import com.warp5.warp5_construction_i.services.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity<EquipmentResponse> create(@RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.createEquipment(request));
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponse>> getAll() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }

    @GetMapping("/highly-rated")
    public List<EquipmentResponse> getHighlyRatedEquipment(
            @RequestParam(defaultValue = "4.0") double minRating
    ) {
        return equipmentService.getHighlyRatedEquipment(minRating);
    }

    @GetMapping("/most-viewed")
    public ResponseEntity<List<EquipmentResponse>> getMostViewed(
            @RequestParam(defaultValue = "10") int limit
    ){
        return ResponseEntity.ok(equipmentService.getMostViewed(limit));
    }

    @GetMapping("/{id}/recommendations")
    public ResponseEntity<List<EquipmentResponse>> getRecommendations(
            @PathVariable Long id,
            @RequestParam(defaultValue = "6") int limit
    ) {
        return ResponseEntity.ok(equipmentService.getRecommendations(id, limit));
    }



}
