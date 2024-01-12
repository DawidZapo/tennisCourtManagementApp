package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
    Setting findByName(String name);
    List<Setting> findAllByNameContaining(String keyword);
    List<Setting> findAllByNameContainingOrNameContaining(String keyword1, String keyword2);
}
