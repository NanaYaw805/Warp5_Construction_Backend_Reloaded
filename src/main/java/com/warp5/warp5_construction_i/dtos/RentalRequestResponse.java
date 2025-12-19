package com.warp5.warp5_construction_i.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalRequestResponse {

    private Long id;
    private Long equipmentId;
    private String equipmentName;

    private Long renterId;
    private String renterName;

    private String rentalAmount;
    private String rentalEmail;
    private String rentalPhoneNumber;

    public String getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(String rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public String getRentalEmail() {
        return rentalEmail;
    }

    public void setRentalEmail(String rentalEmail) {
        this.rentalEmail = rentalEmail;
    }

    public String getRentalPhoneNumber() {
        return rentalPhoneNumber;
    }

    public void setRentalPhoneNumber(String rentalPhoneNumber) {
        this.rentalPhoneNumber = rentalPhoneNumber;
    }

    private Long ownerId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

