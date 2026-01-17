package com.warp5.warp5_construction_i.controllers;

import com.warp5.warp5_construction_i.dtos.ReservationDashboardResponse;
import com.warp5.warp5_construction_i.services.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/owner/{ownerId}/reservations")
    public List<ReservationDashboardResponse> ownerDashboard(@PathVariable Long ownerId) {
        return dashboardService.getOwnerRecentReservations(ownerId);
    }

    @GetMapping("/renter/{renterId}/reservations")
    public List<ReservationDashboardResponse> renterDashboard(@PathVariable Long renterId) {
        return dashboardService.getRenterRecentReservations(renterId);
    }
}
