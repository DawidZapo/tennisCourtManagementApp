package com.springboot.tennisCourtManagementApp.dto;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;

import java.util.List;

public class CourtReservationDto {
    private List<CourtReservation> reservations;

    public List<CourtReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<CourtReservation> reservations) {
        this.reservations = reservations;
    }
}
