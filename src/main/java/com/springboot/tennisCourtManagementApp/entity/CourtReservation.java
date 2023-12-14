package com.springboot.tennisCourtManagementApp.entity;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "court_reservation")
public class CourtReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "court_number")
    private Integer courtNumber;
    @Column(name = "reservation_date")
    private LocalDate reservation_date;
    @Column(name = "time_start")
    private LocalTime timeStart;
    @Column(name = "time_end")
    private LocalTime timeEnd;
    @Column(name = "duration")
    private Double duration;
    @Column(name = "price_schedule")
    private Integer priceSchedule;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "is_doubles_match")
    private boolean isDoublesMatch;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name = "reservation_made_timestamp")
    private String reservationMadeTimestamp;

    public CourtReservation() {
    }

    public CourtReservation(Integer courtNumber,LocalDate reservation_date, LocalTime timeStart, LocalTime timeEnd, Double duration, Integer priceSchedule, Double totalPrice, boolean isDoublesMatch, boolean isPaid, String comments, Customer customer, String reservationMadeTimestamp) {
        this.courtNumber = courtNumber;
        this.reservation_date = reservation_date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.priceSchedule = priceSchedule;
        this.totalPrice = totalPrice;
        this.isDoublesMatch = isDoublesMatch;
        this.isPaid = isPaid;
        this.comments = comments;
        this.customer = customer;
        this.reservationMadeTimestamp = reservationMadeTimestamp;
    }

    public CourtReservation(Integer courtNumber, LocalDate reservation_date, LocalTime timeStart, LocalTime timeEnd, Integer priceSchedule, boolean isDoublesMatch, boolean isPaid) {
        this.courtNumber = courtNumber;
        this.reservation_date = reservation_date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = (double) Duration.between(timeStart, timeEnd).toMinutes();
        this.priceSchedule = priceSchedule;
        this.totalPrice = 0.0;
        this.isDoublesMatch = isDoublesMatch;
        this.isPaid = isPaid;
        this.reservationMadeTimestamp = LocalDate.now().toString() + " " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourtNumber() {
        return courtNumber;
    }

    public void setCourtNumber(Integer courtNumber) {
        this.courtNumber = courtNumber;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getPriceSchedule() {
        return priceSchedule;
    }

    public void setPriceSchedule(Integer priceSchedule) {
        this.priceSchedule = priceSchedule;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDoublesMatch() {
        return isDoublesMatch;
    }

    public void setDoublesMatch(boolean doublesMatch) {
        isDoublesMatch = doublesMatch;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReservationMadeTimestamp() {
        return reservationMadeTimestamp;
    }

    public void setReservationMadeTimestamp(String reservationMadeTimestamp) {
        this.reservationMadeTimestamp = reservationMadeTimestamp;
    }

    @Override
    public String toString() {
        return "CourtReservation{" +
                "id=" + id +
                ", reservation_date=" + reservation_date +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", duration=" + duration +
                ", priceSchedule=" + priceSchedule +
                ", totalPrice=" + totalPrice +
                ", isDoublesMatch=" + isDoublesMatch +
                ", isPaid=" + isPaid +
                ", comments='" + comments + '\'' +
                ", reservationMadeTimestamp='" + reservationMadeTimestamp + '\'' +
                '}';
    }
}
