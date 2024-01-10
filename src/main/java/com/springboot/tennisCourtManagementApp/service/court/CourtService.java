package com.springboot.tennisCourtManagementApp.service.court;

import com.springboot.tennisCourtManagementApp.entity.Court;

import java.util.List;

public interface CourtService {
    Court findById(int id);
    List<Court> findAll();
    Court save(Court court);
    void updateCourtAttributes(int id,boolean isActive, boolean isFlooded);
    void changeFlooded(int id);
    void changeFunctional(int id);

}
