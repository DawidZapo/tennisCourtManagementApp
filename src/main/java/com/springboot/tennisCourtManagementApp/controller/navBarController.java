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
import java.util.ArrayList;
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
        if(date==null){
            date = LocalDate.now();
        }
        boolean isNew = false;
        SettlementDay settlementDay = settlementDayService.findBySummaryDate(date);
        if(settlementDay == null){
            settlementDay = new SettlementDay();
            isNew = true;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
            model.addAttribute("username", username);
            settlementDay.setAcceptedBy(username);
        }


        model.addAttribute("date", date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        List<CourtReservation> reservationsValidForSummary = courtReservationService.findByReservationDateAndValidForFinanceSummary(date,true);
        List<CourtReservation> reservationsInvalidForSummary = courtReservationService.findByReservationDateAndValidForFinanceSummary(date,false);
        List<CourtReservation> allReservations = courtReservationService.findAllByReservationDate(date);

        Double totalCashMoney = getTotalCashMoney(reservationsValidForSummary);
        Double totalCardMoney = getTotalCardMoney(reservationsValidForSummary);
        Double nonSummaryTotal = getNonSummaryTotal(reservationsInvalidForSummary);

        settlementDay.setCashTotal(totalCashMoney);
        settlementDay.setCardTotal(totalCardMoney);
        settlementDay.setNonSummaryTotal(nonSummaryTotal);
        settlementDay.setNumberOfReservations(allReservations.size());

        Boolean isSummaryEligible = null;
        List<CourtReservation> invalidReservations = checkEligibility(reservationsValidForSummary);
        if(invalidReservations.isEmpty()){
            isSummaryEligible = true;
        }
        else{
            isSummaryEligible = false;
        }
        model.addAttribute("allReservations",allReservations);
        model.addAttribute("invalidReservations", invalidReservations);
        model.addAttribute("isSummaryEligible", isSummaryEligible);
        model.addAttribute("totalCashMoney", totalCashMoney);
        model.addAttribute("totalCardMoney", totalCardMoney);
        model.addAttribute("nonSummaryTotal", nonSummaryTotal);
        model.addAttribute("reservationsValid", reservationsValidForSummary);
        model.addAttribute("reservationsInvalid", reservationsInvalidForSummary);
        model.addAttribute("settlementDay", settlementDay);
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("isNew", isNew);

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
    private Double getNonSummaryTotal(List<CourtReservation> reservations){
        Double sum = 0.0;
        for(var reservation : reservations){
            if(!reservation.getValidForFinanceSummary()){
                sum += reservation.getTotalPrice();
            }
        }
        return sum;
    }
    private List<CourtReservation> checkEligibility(List<CourtReservation> reservations){
        List<CourtReservation> invalidReservations = new ArrayList<>();
        for(var reservation : reservations){
            if(reservation.getValidForFinanceSummary() && (reservation.getPaid().equals(Boolean.FALSE) || reservation.getCash() == null)){
                invalidReservations.add(reservation);
            }
        }
        return invalidReservations;
    }
}
