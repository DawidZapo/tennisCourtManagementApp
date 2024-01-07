package com.springboot.tennisCourtManagementApp.service.courtReservation;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CourtReservationService {
    List<CourtReservation> findAll();
    CourtReservation findById(int id);
    CourtReservation save(CourtReservation courtReservation);
    void deleteById(int id);
    void delete(CourtReservation courtReservation);
    List<CourtReservation> findAllByReservationDate(LocalDate date);
    CourtReservation createNewCourtReservation(Integer courtNumber, LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd, Integer priceSchedule, boolean isDoublesMatch, String acceptedBy);
    void updatePayment(int id, Boolean isCash);
    void updateDiscount(int id, Integer priceSchedule, Boolean isDoublesMatch);
    void updateTotalPrice(int id, Double newTotalPrice);
    void updateComments(int id, String comments);
    void updateIsPaid(int id,Boolean isPaid);
    List<CourtReservation> findByReservationDateAndValidForFinanceSummary(LocalDate date, Boolean valid);

}

