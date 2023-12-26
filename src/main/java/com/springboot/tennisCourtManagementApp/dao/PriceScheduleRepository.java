package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceScheduleRepository extends JpaRepository<PriceSchedule, Integer> {
}
