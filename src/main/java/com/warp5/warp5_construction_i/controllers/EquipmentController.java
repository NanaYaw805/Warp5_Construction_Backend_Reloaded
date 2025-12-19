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


}
