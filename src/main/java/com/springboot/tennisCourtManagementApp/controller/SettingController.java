package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.dto.SettingManagerDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingController {
    @PostMapping("/settings/save")
    public String saveSettings(@ModelAttribute("settingManagerDto")SettingManagerDto settingManagerDto){
        System.out.println(settingManagerDto);
        return "redirect:/settings";
    }
}
