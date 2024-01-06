package com.springboot.tennisCourtManagementApp.service.settlementDay;

import com.springboot.tennisCourtManagementApp.entity.SettlementDay;

import java.util.List;

public interface SettlementDayService {
    SettlementDay findById(int id);
    List<SettlementDay> findAll();
    void deleteById(int id);
    void delete(SettlementDay settlementDay);
    SettlementDay save(SettlementDay settlementDay);
}
