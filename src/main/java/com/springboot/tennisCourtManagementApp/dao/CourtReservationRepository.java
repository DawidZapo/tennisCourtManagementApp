package com.springboot.tennisCourtManagementApp.dao;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtReservationRepository extends JpaRepository<CourtReservation, Integer> {
}
