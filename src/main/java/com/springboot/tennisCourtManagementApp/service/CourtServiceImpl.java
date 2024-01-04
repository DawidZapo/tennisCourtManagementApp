package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.dao.CourtRepository;
import com.springboot.tennisCourtManagementApp.entity.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtServiceImpl implements CourtService{
    private CourtRepository courtRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Court> findAll() {
        return courtRepository.findAll();
    }
}
