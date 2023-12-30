package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CourtReservationRepository extends JpaRepository<CourtReservation, Integer> {
    @Query("SELECT cr FROM CourtReservation cr WHERE cr.reservationDate = :date")
    List<CourtReservation> findAllByReservationDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("UPDATE CourtReservation cr SET cr.isCash = :isCash WHERE cr.id = :id")
    void updateIsCashById(@Param("id") int id, @Param("isCash") Boolean isCash);

    @Transactional
    @Modifying
    @Query("UPDATE CourtReservation cr SET cr.priceSchedule = :priceSchedule, cr.isDoublesMatch = :isDoublesMatch WHERE cr.id = :id")
    void updateDiscount(@Param("id") int id, @Param("priceSchedule") Integer priceSchedule, @Param("isDoublesMatch") Boolean isDoublesMatch);

    @Transactional
    @Modifying
    @Query("UPDATE CourtReservation cr SET cr.totalPrice = :totalPrice WHERE cr.id = :id")
    void updateTotalPrice(@Param("id") int id, @Param("totalPrice") Double totalPrice);
}
