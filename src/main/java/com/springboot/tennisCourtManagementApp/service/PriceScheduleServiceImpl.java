package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.dao.PriceScheduleRepository;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PriceScheduleServiceImpl implements PriceScheduleService{
    private PriceScheduleRepository priceScheduleRepository;

    @Autowired
    public PriceScheduleServiceImpl(PriceScheduleRepository priceScheduleRepository) {
        this.priceScheduleRepository = priceScheduleRepository;
    }

    @Override
    public PriceSchedule findById(int id) {
        Optional<PriceSchedule> result = priceScheduleRepository.findById(id);
        PriceSchedule priceSchedule = null;

        if(result.isPresent()){
            priceSchedule = result.get();
        }
        else{
            throw new RuntimeException("Did not find priceSchedule id: " + id);
        }
        return priceSchedule;
    }

    @Override
    public List<PriceSchedule> findAll() {
        return priceScheduleRepository.findAll();
    }

    @Override
    public PriceSchedule save(PriceSchedule priceSchedule) {
        return priceScheduleRepository.save(priceSchedule);
    }
}
