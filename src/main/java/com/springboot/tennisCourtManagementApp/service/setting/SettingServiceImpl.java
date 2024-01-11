package com.springboot.tennisCourtManagementApp.service.setting;

import com.springboot.tennisCourtManagementApp.dao.SettingRepository;
import com.springboot.tennisCourtManagementApp.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingServiceImpl implements SettingService{
    private SettingRepository settingRepository;

    @Autowired
    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public Setting findById(int id) {
        Optional<Setting> result = settingRepository.findById(id);
        Setting setting = null;

        if(result.isPresent()){
            setting = result.get();
        }
        else{
            throw new RuntimeException("Did not find setting id: " + id);
        }
        return setting;
    }

    @Override
    public List<Setting> findALl() {
        return settingRepository.findAll();
    }

    @Override
    public Setting findByName(String name) {
        return settingRepository.findByName(name);
    }
}
