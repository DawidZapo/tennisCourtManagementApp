package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.dto.SettingManagerDto;
import com.springboot.tennisCourtManagementApp.service.setting.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingController {
    private SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping("/settings/save")
    public String saveSettings(@ModelAttribute("settingManagerDto")SettingManagerDto settingManagerDto){

        settingService.changeSettings(settingManagerDto);

        return "redirect:/settings";
    }
}
