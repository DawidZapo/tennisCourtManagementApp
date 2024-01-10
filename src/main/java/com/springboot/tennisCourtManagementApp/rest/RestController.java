package com.springboot.tennisCourtManagementApp.rest;

import com.springboot.tennisCourtManagementApp.entity.Court;
import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.Customer;
import com.springboot.tennisCourtManagementApp.service.court.CourtService;
import com.springboot.tennisCourtManagementApp.service.courtReservation.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private CustomerService customerService;
    private CourtReservationService courtReservationService;
    private CourtService courtService;

    @Autowired
    public RestController(CustomerService customerService, CourtReservationService courtReservationService, CourtService courtService) {
        this.customerService = customerService;
        this.courtReservationService = courtReservationService;
        this.courtService = courtService;
    }

    @GetMapping("/customer/search")
    public List<Customer> searchByLastName(@RequestParam("lastName") String lastName){
        List<Customer> customers = customerService.findByLastName(lastName);
        return customers;
    }

    @GetMapping("/customer/searchContaining")
    public List<Customer> searchByLastNameContaining(@RequestParam("lastName") String pattern){
        List<Customer> customers = customerService.findByLastNameContaining(pattern);
        //List<Customer> customersFirstName = customerService.findByFirstNameContaining(pattern);
        return customers;
    }
    @GetMapping("/courtReservations/current")
    public List<CourtReservation> getCurrentCourtReservations() {
        List<CourtReservation> todayReservations = courtReservationService.findAllByReservationDate(LocalDate.now());
        List<CourtReservation> currentReservations = new ArrayList<>();
        LocalTime currentTime = LocalTime.now();

        for (CourtReservation reservation : todayReservations) {
            if (currentTime.isAfter(reservation.getTimeStart()) && currentTime.isBefore(reservation.getTimeEnd())) {
                currentReservations.add(reservation);
            }
        }

        return currentReservations;
    }

    @GetMapping("/courtReservations/upcomingAndOngoing")
    public List<CourtReservation> getUpcomingReservations() {
        List<CourtReservation> todayReservations = courtReservationService.findAllByReservationDate(LocalDate.now());
        List<CourtReservation> upcomingAndOngoingReservations = new ArrayList<>();
        LocalTime currentTime = LocalTime.now();

        for (CourtReservation reservation : todayReservations) {
            if(reservation.getTimeStart().isAfter(currentTime)){
                upcomingAndOngoingReservations.add(reservation);
            }
            else if(currentTime.isAfter(reservation.getTimeStart()) && currentTime.isBefore(reservation.getTimeEnd())){
                upcomingAndOngoingReservations.add(reservation);
            }
        }
        CourtReservation.sortReservations(upcomingAndOngoingReservations);
        return upcomingAndOngoingReservations;
    }

    @GetMapping("/courts")
    public List<Court> getCourts(){
        return courtService.findAllByOrderByCourtNumber();
    }

}
