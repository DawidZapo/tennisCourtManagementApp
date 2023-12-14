package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourtReservationController {
    private final CourtReservationService courtReservationService;

    @Autowired
    public CourtReservationController(CourtReservationService courtReservationService) {
        this.courtReservationService = courtReservationService;
    }

    @GetMapping("/reservations")
    public List<CourtReservation> getReservationsByDate(@RequestParam LocalDate date) {
        return courtReservationService.findAllByReservationDate(date);
    }
}
