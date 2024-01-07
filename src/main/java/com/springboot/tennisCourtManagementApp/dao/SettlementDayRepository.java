package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.SettlementDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SettlementDayRepository extends JpaRepository<SettlementDay, Integer> {
    SettlementDay findBySummaryDate(LocalDate localDate);
}
