package com.warp5.warp5_construction_i.dtos;

public class ReservationDashboardResponse {

    private Long orderId;
    private String equipment;
    private String status;

    public ReservationDashboardResponse(Long orderId, String equipment, String status) {
        this.orderId = orderId;
        this.equipment = equipment;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
