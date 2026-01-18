package com.warp5.warp5_construction_i.dtos;

import com.warp5.warp5_construction_i.enums.ReservationStatus;

import java.time.LocalDate;

public class ReservationDashboardResponse {

    private Long orderId;
    private String equipmentName;
    private Double rentalAmount;
    private ReservationStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationDashboardResponse(Long orderId, String equipmentName, Double rentalAmount, ReservationStatus status, LocalDate startDate, LocalDate endDate) {
        this.orderId = orderId;
        this.equipmentName = equipmentName;
        this.rentalAmount = rentalAmount;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReservationDashboardResponse() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Double getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(Double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
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
}
