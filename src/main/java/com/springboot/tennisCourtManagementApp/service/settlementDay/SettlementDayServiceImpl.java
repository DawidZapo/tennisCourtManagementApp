package com.springboot.tennisCourtManagementApp.service.settlementDay;

import com.springboot.tennisCourtManagementApp.dao.SettlementDayRepository;
import com.springboot.tennisCourtManagementApp.entity.SettlementDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SettlementDayServiceImpl implements SettlementDayService{

    private SettlementDayRepository settlementDayRepository;

    @Autowired
    public SettlementDayServiceImpl(SettlementDayRepository settlementDayRepository) {
        this.settlementDayRepository = settlementDayRepository;
    }

    @Override
    public SettlementDay findById(int id) {
        Optional<SettlementDay> result = settlementDayRepository.findById(id);
        SettlementDay settlementDay = null;

        if(result.isPresent()){
            settlementDay = result.get();
        }
        else{
            throw new RuntimeException("Did not find settlementDay id: " + id);
        }
        return settlementDay;
    }

    @Override
    public List<SettlementDay> findAll() {
        return settlementDayRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        settlementDayRepository.deleteById(id);
    }

    @Override
    public void delete(SettlementDay settlementDay) {
        settlementDayRepository.delete(settlementDay);
    }

    @Override
    public SettlementDay save(SettlementDay settlementDay) {
        return settlementDayRepository.save(settlementDay);
    }

    @Override
    public SettlementDay findBySummaryDate(LocalDate date) {
        return settlementDayRepository.findBySummaryDate(date);
    }
}
