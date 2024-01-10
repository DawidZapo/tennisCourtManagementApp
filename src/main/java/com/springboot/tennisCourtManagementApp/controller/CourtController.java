package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.service.court.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/courts")
@Controller
public class CourtController {
    private CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @PostMapping("/save")
    public String saveCourt(@RequestParam("id")int id, @RequestParam("option")String option){
        if(option.equals("flooded")){
            courtService.changeFlooded(id);
        }
        else if(option.equals("functional")){
            courtService.changeFunctional(id);
        }
        return "redirect:/courts";
    }

}
