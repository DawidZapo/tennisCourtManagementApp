package com.springboot.tennisCourtManagementApp.service.setting;

import com.springboot.tennisCourtManagementApp.dto.SettingManagerDto;
import com.springboot.tennisCourtManagementApp.entity.Setting;

import java.util.List;

public interface SettingService {
    Setting findById(int id);
    List<Setting> findALl();
    Setting findByName(String name);
    Setting save(Setting setting);
    void changeSettings(SettingManagerDto settingManagerDto);
    List<Setting> findAllByNameContaining(String keyword);
    List<Setting> findAllByNameContainingOrNameContaining(String keyword1, String keyword2);
    SettingManagerDto getCurrentSettings();
}
