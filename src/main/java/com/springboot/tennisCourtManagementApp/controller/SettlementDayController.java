package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.SettlementDay;
import com.springboot.tennisCourtManagementApp.service.settlementDay.SettlementDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/settlementDay")
public class SettlementDayController {
    private SettlementDayService settlementDayService;

    @Autowired
    public SettlementDayController(SettlementDayService settlementDayService) {
        this.settlementDayService = settlementDayService;
    }

    @PostMapping("/save")
    public String saveSettlementDay(@ModelAttribute("settlementDay")SettlementDay settlementDay){
        if(settlementDay.getCashBox() ==0 || settlementDay.getCashBox() >= 0.01){
            double truncatedPriceToTwoDecimals = Math.floor(settlementDay.getCashBox()*100)/100;
            settlementDay.setCashBox(truncatedPriceToTwoDecimals);
        }

        if(settlementDay.getCardTerminal() ==0 || settlementDay.getCardTerminal() >= 0.01){
            double truncatedPriceToTwoDecimals = Math.floor(settlementDay.getCardTerminal()*100)/100;
            settlementDay.setCardTerminal(truncatedPriceToTwoDecimals);
        }
        Boolean isCorrect = false;
        if(settlementDay.getCashTotal().equals(settlementDay.getCashBox()) && settlementDay.getCardTotal().equals(settlementDay.getCardTerminal())){
            isCorrect = true;
        }
        settlementDay.setCorrect(isCorrect);
        String acceptedAt = LocalDate.now().toString() + " " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        settlementDay.setAcceptedAt(acceptedAt);

        settlementDayService.save(settlementDay);
        return "redirect:/daySummary?date="+settlementDay.getSummaryDate()+"&recentlyUpdated="+true;
    }
}
