package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.entity.Court;

import java.util.List;

public interface CourtService {
    List<Court> findAll();
}
