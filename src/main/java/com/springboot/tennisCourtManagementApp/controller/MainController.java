package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    private CourtReservationService courtReservationService;
    @Autowired
    public MainController(CourtReservationService courtReservationService) {
        this.courtReservationService = courtReservationService;
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
}
