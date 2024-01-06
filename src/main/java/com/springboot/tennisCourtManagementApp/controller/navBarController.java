package com.springboot.tennisCourtManagementApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class navBarController {
    @GetMapping("/courts")
    public String showCourts(Model model){
        return "courts";
    }
    @GetMapping("/daySummary")
    public String showDaySummary(Model model){
        return "daySummary";
    }
    @GetMapping("/settings")
    public String showSettings(Model model){
        return "settings";
    }
}
