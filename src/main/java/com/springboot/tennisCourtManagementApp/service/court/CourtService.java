package com.springboot.tennisCourtManagementApp.service.court;

import com.springboot.tennisCourtManagementApp.entity.Court;

import java.util.List;

public interface CourtService {
    List<Court> findAll();
}
