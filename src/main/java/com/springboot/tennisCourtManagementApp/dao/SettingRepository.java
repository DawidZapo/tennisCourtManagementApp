package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
    Setting findByName(String name);
}
