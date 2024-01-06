package com.springboot.tennisCourtManagementApp.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class navBarController {
    @GetMapping("/courts")
    public String showCourts(Model model){
        return "courts";
    }
    @GetMapping("/daySummary")
    public String showDaySummary(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){
        if(date==null){
            date = LocalDate.now();
        }

        return "day-summary";
    }
    @GetMapping("/settings")
    public String showSettings(Model model){
        return "settings";
    }
}
