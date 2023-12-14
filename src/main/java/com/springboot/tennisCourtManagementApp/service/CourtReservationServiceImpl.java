package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.dao.CourtReservationRepository;
import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourtReservationServiceImpl implements CourtReservationService{
    private CourtReservationRepository courtReservationRepository;

    @Autowired
    public CourtReservationServiceImpl(CourtReservationRepository courtReservationRepository){
        this.courtReservationRepository = courtReservationRepository;
    }
    @Override
    public List<CourtReservation> findAll() {
        return courtReservationRepository.findAll();
    }

    @Override
    public CourtReservation findById(int id) {
        Optional<CourtReservation> result = courtReservationRepository.findById(id);
        CourtReservation courtReservation = null;

        if(result.isPresent()){
            courtReservation = result.get();
        }
        else{
            throw new RuntimeException("Did not find courtReservation id: " + id);
        }
        return courtReservation;
    }

    @Override
    public CourtReservation save(CourtReservation courtReservation) {
        return courtReservationRepository.save(courtReservation);
    }

    @Override
    public void deleteById(int id) {
        courtReservationRepository.deleteById(id);
    }

    @Override
    public void delete(CourtReservation courtReservation) {
        courtReservationRepository.delete(courtReservation);
    }

    @Override
    public List<CourtReservation> findAllByReservationDate(LocalDate date) {
        return courtReservationRepository.findAllByReservationDate(date);
    }
}
