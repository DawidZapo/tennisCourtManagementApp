package com.springboot.tennisCourtManagementApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "price_schedule")
public class PriceSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "discount_name")
    private String discountName;
    @Column(name = "workday_morning_singles")
    private Double workdayMorningSingles;
    @Column(name = "workday_afternoon_singles")
    private Double workdayAfternoonSingles;
    @Column(name = "offday_morning_singles")
    private Double offdayMorningSingles;
    @Column(name = "offday_afternoon_singles")
    private Double offdayAfternoonSingles;
    @Column(name = "workday_morning_doubles")
    private Double workdayMorningDoubles;
    @Column(name = "workday_afternoon_doubles")
    private Double workdayAfternoonDoubles;
    @Column(name = "offday_morning_doubles")
    private Double offdayMorningDoubles;
    @Column(name = "offday_afternoon_doubles")
    private Double offdayAfternoonDoubles;

    public PriceSchedule() {
    }

    public PriceSchedule(Integer id, String discountName, Double workdayMorningSingles, Double workdayAfternoonSingles, Double offdayMorningSingles, Double offdayAfternoonSingles, Double workdayMorningDoubles, Double workdayAfternoonDoubles, Double offdayMorningDoubles, Double offdayAfternoonDoubles) {
        this.id = id;
        this.discountName = discountName;
        this.workdayMorningSingles = workdayMorningSingles;
        this.workdayAfternoonSingles = workdayAfternoonSingles;
        this.offdayMorningSingles = offdayMorningSingles;
        this.offdayAfternoonSingles = offdayAfternoonSingles;
        this.workdayMorningDoubles = workdayMorningDoubles;
        this.workdayAfternoonDoubles = workdayAfternoonDoubles;
        this.offdayMorningDoubles = offdayMorningDoubles;
        this.offdayAfternoonDoubles = offdayAfternoonDoubles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Double getWorkdayMorningSingles() {
        return workdayMorningSingles;
    }

    public void setWorkdayMorningSingles(Double workdayMorningSingles) {
        this.workdayMorningSingles = workdayMorningSingles;
    }

    public Double getWorkdayAfternoonSingles() {
        return workdayAfternoonSingles;
    }

    public void setWorkdayAfternoonSingles(Double workdayAfternoonSingles) {
        this.workdayAfternoonSingles = workdayAfternoonSingles;
    }

    public Double getOffdayMorningSingles() {
        return offdayMorningSingles;
    }

    public void setOffdayMorningSingles(Double offdayMorningSingles) {
        this.offdayMorningSingles = offdayMorningSingles;
    }

    public Double getOffdayAfternoonSingles() {
        return offdayAfternoonSingles;
    }

    public void setOffdayAfternoonSingles(Double offdayAfternoonSingles) {
        this.offdayAfternoonSingles = offdayAfternoonSingles;
    }

    public Double getWorkdayMorningDoubles() {
        return workdayMorningDoubles;
    }

    public void setWorkdayMorningDoubles(Double workdayMorningDoubles) {
        this.workdayMorningDoubles = workdayMorningDoubles;
    }

    public Double getWorkdayAfternoonDoubles() {
        return workdayAfternoonDoubles;
    }

    public void setWorkdayAfternoonDoubles(Double workdayAfternoonDoubles) {
        this.workdayAfternoonDoubles = workdayAfternoonDoubles;
    }

    public Double getOffdayMorningDoubles() {
        return offdayMorningDoubles;
    }

    public void setOffdayMorningDoubles(Double offdayMorningDoubles) {
        this.offdayMorningDoubles = offdayMorningDoubles;
    }

    public Double getOffdayAfternoonDoubles() {
        return offdayAfternoonDoubles;
    }

    public void setOffdayAfternoonDoubles(Double offdayAfternoonDoubles) {
        this.offdayAfternoonDoubles = offdayAfternoonDoubles;
    }

    @Override
    public String toString() {
        return "PriceSchedule{" +
                "id=" + id +
                ", discountName='" + discountName + '\'' +
                ", workdayMorningSingles=" + workdayMorningSingles +
                ", workdayAfternoonSingles=" + workdayAfternoonSingles +
                ", offdayMorningSingles=" + offdayMorningSingles +
                ", offdayAfternoonSingles=" + offdayAfternoonSingles +
                ", workdayMorningDoubles=" + workdayMorningDoubles +
                ", workdayAfternoonDoubles=" + workdayAfternoonDoubles +
                ", offdayMorningDoubles=" + offdayMorningDoubles +
                ", offdayAfternoonDoubles=" + offdayAfternoonDoubles +
                '}';
    }
}
