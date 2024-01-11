package com.springboot.tennisCourtManagementApp.service.setting;

import com.springboot.tennisCourtManagementApp.entity.Setting;

import java.util.List;

public interface SettingService {
    Setting findById(int id);
    List<Setting> findALl();
    Setting findByName(String name);
}
