package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.PriceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    private CourtReservationService courtReservationService;
    private PriceScheduleService priceScheduleService;

    @Autowired
    public MainController(CourtReservationService courtReservationService, PriceScheduleService priceScheduleService) {
        this.courtReservationService = courtReservationService;
        this.priceScheduleService = priceScheduleService;
    }

    @GetMapping("/")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        List<CourtReservation> reservations = courtReservationService.findAllByReservationDate(LocalDate.now());
        model.addAttribute("reservations", reservations);

        return "home";
    }

    @GetMapping("/reservation")
    public String showReservation(@RequestParam("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        CourtReservation courtReservation = courtReservationService.findById(id);
        PriceSchedule priceSchedule = priceScheduleService.findById(courtReservation.getPriceSchedule());
        model.addAttribute("reservation", courtReservation);
        model.addAttribute("priceSchedule", priceSchedule);
        return "reservation-detail-look";
    }
}
