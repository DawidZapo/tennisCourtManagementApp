package com.springboot.tennisCourtManagementApp.dto;

import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;

import java.util.List;

public class PriceScheduleDto {
    private List<PriceSchedule> priceSchedules;

    public List<PriceSchedule> getPriceSchedules() {
        return priceSchedules;
    }

    public void setPriceSchedules(List<PriceSchedule> priceSchedules) {
        this.priceSchedules = priceSchedules;
    }
}
