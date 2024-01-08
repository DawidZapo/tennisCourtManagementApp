package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.SettlementDay;
import com.springboot.tennisCourtManagementApp.service.courtReservation.CourtReservationService;
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
import java.util.List;

@Controller
public class navBarController {

    private SettlementDayService settlementDayService;
    private CourtReservationService courtReservationService;

    @Autowired
    public navBarController(SettlementDayService settlementDayService, CourtReservationService courtReservationService) {
        this.settlementDayService = settlementDayService;
        this.courtReservationService = courtReservationService;
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
        model.addAttribute("date", date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        List<CourtReservation> reservationsValidForSummary = courtReservationService.findByReservationDateAndValidForFinanceSummary(date,true);
        List<CourtReservation> reservationsInvalidForSummary = courtReservationService.findByReservationDateAndValidForFinanceSummary(date,false);
        Double totalCashMoney = getTotalCashMoney(reservationsValidForSummary);
        Double totalCardMoney = getTotalCardMoney(reservationsValidForSummary);
        boolean isSummaryEligible = checkEligibility(reservationsValidForSummary);

        model.addAttribute("isSummaryEligible", isSummaryEligible);
        model.addAttribute("totalCashMoney", totalCashMoney);
        model.addAttribute("totalCardMoney", totalCardMoney);
        model.addAttribute("reservationsValid", reservationsValidForSummary);
        model.addAttribute("reservationsInvalid", reservationsInvalidForSummary);
        model.addAttribute("settlementDay", settlementDay);
        model.addAttribute("formattedDate", formattedDate);

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

    private Double getTotalCashMoney(List<CourtReservation> reservations){
        Double sum = 0.0;
        for(var reservation : reservations){
            if(reservation.getValidForFinanceSummary()){
                if(reservation.getCash() != null){
                    if(reservation.getCash()){
                        sum += reservation.getTotalPrice();
                    }
                }
            }
        }
        return sum;
    }
    private Double getTotalCardMoney(List<CourtReservation> reservations){
        Double sum = 0.0;
        for(var reservation : reservations){
            if(reservation.getValidForFinanceSummary()){
                if(reservation.getCash() != null){
                    if(!reservation.getCash()){
                        sum += reservation.getTotalPrice();
                    }
                }
            }
        }
        return sum;
    }
    private boolean checkEligibility(List<CourtReservation> reservations){
        for(var reservation : reservations){
            if(reservation.getValidForFinanceSummary() && (reservation.getPaid().equals(Boolean.FALSE) || reservation.getCash() == null)){
                return false;
            }
        }
        return true;
    }
}
