package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CourtReservationRepository extends JpaRepository<CourtReservation, Integer> {
    @Query("SELECT cr FROM CourtReservation cr WHERE cr.reservationDate = :date")
    List<CourtReservation> findAllByReservationDate(@Param("date") LocalDate date);
}
