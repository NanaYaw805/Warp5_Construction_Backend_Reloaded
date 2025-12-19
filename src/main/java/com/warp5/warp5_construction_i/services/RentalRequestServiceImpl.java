package com.warp5.warp5_construction_i.services;

import com.warp5.warp5_construction_i.dtos.RentalRequestCreateDto;
import com.warp5.warp5_construction_i.dtos.RentalRequestResponse;
import com.warp5.warp5_construction_i.model.RentalRequest;
import com.warp5.warp5_construction_i.repositories.RentalRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalRequestServiceImpl implements RentalRequestService {

    private final RentalRequestRepository repository;

    public RentalRequestServiceImpl(RentalRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public RentalRequestResponse create(RentalRequestCreateDto dto) {
        RentalRequest request = new RentalRequest();
        request.setEquipmentId(dto.getEquipmentId());
        request.setEquipmentName(dto.getEquipmentName());
        request.setRenterId(dto.getRenterId());
        request.setRenterName(dto.getRenterName());
        request.setOwnerId(dto.getOwnerId());
        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setRentalEmail(dto.getRentalEmail());
        request.setRentalPhoneNumber(dto.getRentalPhoneNumber());
        request.setRentalAmount(dto.getRentalAmount());

        return map(repository.save(request));
    }

    @Override
    public List<RentalRequestResponse> getRequestsForOwner(Long ownerId) {
        return repository.findByOwnerId(ownerId)
                .stream().map(this::map).toList();
    }

    @Override
    public List<RentalRequestResponse> getRequestsForRenter(Long renterId) {
        return repository.findByRenterId(renterId)
                .stream().map(this::map).toList();
    }

    @Override
    public RentalRequestResponse decide(Long requestId, String decision) {
        RentalRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!decision.equals("APPROVED") && !decision.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid decision");
        }

        request.setStatus(decision);
        request.setUpdatedAt(LocalDateTime.now());

        return map(repository.save(request));
    }

    private RentalRequestResponse map(RentalRequest r) {
        RentalRequestResponse res = new RentalRequestResponse();
        res.setId(r.getId());
        res.setEquipmentId(r.getEquipmentId());
        res.setEquipmentName(r.getEquipmentName());
        res.setRenterId(r.getRenterId());
        res.setRenterName(r.getRenterName());
        res.setOwnerId(r.getOwnerId());
        res.setStartDate(r.getStartDate());
        res.setEndDate(r.getEndDate());
        res.setStatus(r.getStatus());
        res.setCreatedAt(r.getCreatedAt());
        res.setRentalAmount(r.getRentalAmount());
        res.setRentalEmail(r.getRentalEmail());
        res.setRentalPhoneNumber(r.getRentalPhoneNumber());
        return res;
    }
}
