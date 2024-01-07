package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.SettlementDay;
import com.springboot.tennisCourtManagementApp.service.settlementDay.SettlementDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        settlementDayService.save(settlementDay);
        return "redirect:/daySummary?date="+settlementDay.getSummaryDate();
    }
}
