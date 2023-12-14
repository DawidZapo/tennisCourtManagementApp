package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;

import java.time.LocalDate;
import java.util.List;

public interface CourtReservationService {
    List<CourtReservation> findAll();
    CourtReservation findById(int id);
    CourtReservation save(CourtReservation courtReservation);
    void deleteById(int id);
    void delete(CourtReservation courtReservation);
    List<CourtReservation> findAllByReservationDate(LocalDate date);
}
