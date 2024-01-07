package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.SettlementDay;
import com.springboot.tennisCourtManagementApp.service.settlementDay.SettlementDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class navBarController {

    private SettlementDayService settlementDayService;

    @Autowired
    public navBarController(SettlementDayService settlementDayService) {
        this.settlementDayService = settlementDayService;
    }

    @GetMapping("/courts")
    public String showCourts(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }

        return "courts";
    }
    @GetMapping("/daySummary")
    public String showDaySummary(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }

        if(date==null){
            date = LocalDate.now();
        }
        SettlementDay settlementDay = settlementDayService.findBySummaryDate(date);
        if(settlementDay == null){
            settlementDay = new SettlementDay();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        model.addAttribute("settlementDay", settlementDay);
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("date", date);

        return "day-summary";
    }
    @GetMapping("/settings")
    public String showSettings(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }

        return "settings";
    }
}
