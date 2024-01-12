package com.springboot.tennisCourtManagementApp.service.setting;

import com.springboot.tennisCourtManagementApp.dao.SettingRepository;
import com.springboot.tennisCourtManagementApp.dto.SettingManagerDto;
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

    @Override
    public Setting save(Setting setting) {
        return settingRepository.save(setting);
    }

    @Override
    public List<Setting> findAllByNameContaining(String keyword) {
        return settingRepository.findAllByNameContaining(keyword);
    }

    @Override
    public List<Setting> findAllByNameContainingOrNameContaining(String keyword1, String keyword2) {
        return settingRepository.findAllByNameContainingOrNameContaining(keyword1,keyword2);
    }

    @Override
    public void changeSettings(SettingManagerDto settingManagerDto) {

        // zmiana wyswietlania imienia
        if(settingManagerDto.getNameDisplay() != null){
            List<Setting> nameDisplaySettings = findAllByNameContainingOrNameContaining("firstName","lastName");

            for(var setting : nameDisplaySettings){
                setting.setActive(false);

                if(settingManagerDto.getNameDisplay().equals(setting.getName())){
                    setting.setActive(true);
                }
                save(setting);
            }
        }

        //zmiana czy pokazac czas trwania rezerwacji
        updateSetting("showReservationDurationTime", settingManagerDto.getShowReservationDurationTime());

        //zmiana autowyszukiwanie
        updateSetting("clientAutoComplete", settingManagerDto.getClientAutoComplete());

        // zmiana czy non summary pokazywac na inny kolor
        updateSetting("showNonSummaryReservationsWithDifferentColor", settingManagerDto.getShowNonSummaryReservationsWithDifferentColor());

        //zmiana czy pokazac ikone opłaty
        updateSetting("showPaidIcon", settingManagerDto.getShowPaidIcon());

        //zmiana czy pokazac ikone karty/gotowki
        updateSetting("showIfCashOrCashIcon", settingManagerDto.getShowIfCashOrCardIcon());

        //zmiana czy pokazac ikone niewliczonych rezerewacji
        updateSetting("showNonSummaryIcon", settingManagerDto.getShowNonSummaryIcon());

        //zmiana czy pokazac ikony awarii kortów
        updateSetting("showCourtIconsInReservationTable", settingManagerDto.getShowCourtIconsInReservationTable());

        // zmiana koloru rezerwacji
        if(settingManagerDto.getTileColor() != null){
            List<Setting> tileColorSettings = findAllByNameContaining("Tile");

            for(var setting : tileColorSettings){
                setting.setActive(false);

                if(settingManagerDto.getTileColor().equals(setting.getName())){
                    setting.setActive(true);
                }
                save(setting);
            }
        }
    }

    private void updateSetting(String settingName, Boolean newValue) {
        if (newValue != null) {
            Setting setting = findByName(settingName);
            setting.setActive(newValue);
            save(setting);
        }
    }


}
