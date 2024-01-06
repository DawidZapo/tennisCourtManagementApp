package com.springboot.tennisCourtManagementApp.service.priceSchedule;

import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;

import java.util.List;

public interface PriceScheduleService {
    PriceSchedule findById(int id);
    List<PriceSchedule> findAll();
    PriceSchedule save(PriceSchedule priceSchedule);
}
