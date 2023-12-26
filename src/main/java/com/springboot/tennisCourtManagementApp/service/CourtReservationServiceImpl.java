package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.dao.CourtReservationRepository;
import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourtReservationServiceImpl implements CourtReservationService{
    private CourtReservationRepository courtReservationRepository;
    private PriceScheduleService priceScheduleService;

    @Autowired
    public CourtReservationServiceImpl(CourtReservationRepository courtReservationRepository, PriceScheduleService priceScheduleService) {
        this.courtReservationRepository = courtReservationRepository;
        this.priceScheduleService = priceScheduleService;
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

    @Override
    public CourtReservation createNewCourtReservation(Integer courtNumber, LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd, Integer priceSchedule, boolean isDoublesMatch) {
        Double totalPrice = 0.0;
        PriceSchedule price = priceScheduleService.findById(priceSchedule);

        return new CourtReservation(courtNumber,reservationDate,timeStart,timeEnd,priceSchedule,totalPrice,isDoublesMatch,false);
    }
}
