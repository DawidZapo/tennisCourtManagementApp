package com.springboot.tennisCourtManagementApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "court_reservation")
public class CourtReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "reservation_date")
    private LocalDate reservation_date;
    @Column(name = "duration")
    private Integer duration;
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

    public CourtReservation() {
    }

    public CourtReservation(LocalDate reservation_date, Integer duration, Integer priceSchedule, Double totalPrice, boolean isDoublesMatch, boolean isPaid, String comments, Customer customer) {
        this.reservation_date = reservation_date;
        this.duration = duration;
        this.priceSchedule = priceSchedule;
        this.totalPrice = totalPrice;
        this.isDoublesMatch = isDoublesMatch;
        this.isPaid = isPaid;
        this.comments = comments;
        this.customer = customer;
    }
    public CourtReservation(LocalDate reservation_date, Integer duration, Integer priceSchedule) {
        this.reservation_date = reservation_date;
        this.duration = duration;
        this.priceSchedule = priceSchedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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

    @Override
    public String toString() {
        return "CourtReservation{" +
                "id=" + id +
                ", reservation_date=" + reservation_date +
                ", duration=" + duration +
                ", priceSchedule=" + priceSchedule +
                ", totalPrice=" + totalPrice +
                ", isDoublesMatch=" + isDoublesMatch +
                ", isPaid=" + isPaid +
                ", comments='" + comments + '\'' +
                '}';
    }
}
