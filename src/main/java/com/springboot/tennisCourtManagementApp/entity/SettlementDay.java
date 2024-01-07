package com.springboot.tennisCourtManagementApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "settlement_day")
public class SettlementDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "summary_date")
    private LocalDate summaryDate;
    @Column(name = "cash_total")
    private Double cashTotal;
    @Column(name = "card_total")
    private Double cardTotal;
    @Column(name = "cash_box")
    private Double cashBox;
    @Column(name = "card_terminal")
    private Double card_terminal;
    @Column(name = "non_summary_total")
    private Double nonSummaryTotal;
    @Column(name = "number_of_reservations")
    private Integer numberOfReservations;
    @Column(name = "is_correct")
    private Boolean isCorrect;
    @Column(name = "accepted_by")
    private String acceptedBy;
    @Column(name = "accepted_at")
    private String acceptedAt;

    public SettlementDay() {
    }

    public SettlementDay(Integer id, LocalDate summaryDate, Double cashTotal, Double cardTotal, Double cashBox, Double card_terminal,Double nonSummaryTotal, Integer numberOfReservations, Boolean isCorrect, String acceptedBy, String acceptedAt) {
        this.id = id;
        this.summaryDate = summaryDate;
        this.cashTotal = cashTotal;
        this.cardTotal = cardTotal;
        this.cashBox = cashBox;
        this.card_terminal = card_terminal;
        this.nonSummaryTotal = nonSummaryTotal;
        this.numberOfReservations = numberOfReservations;
        this.isCorrect = isCorrect;
        this.acceptedBy = acceptedBy;
        this.acceptedAt = acceptedAt;
    }

    public SettlementDay(LocalDate summaryDate, Double cashTotal, Double cardTotal, Double cashBox, Double card_terminal,Double nonSummaryTotal, Integer numberOfReservations, Boolean isCorrect, String acceptedBy) {
        this.summaryDate = summaryDate;
        this.cashTotal = cashTotal;
        this.cardTotal = cardTotal;
        this.cashBox = cashBox;
        this.card_terminal = card_terminal;
        this.nonSummaryTotal = nonSummaryTotal;
        this.numberOfReservations = numberOfReservations;
        this.isCorrect = isCorrect;
        this.acceptedBy = acceptedBy;
        this.acceptedAt = LocalDate.now().toString() + " " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(LocalDate summaryDate) {
        this.summaryDate = summaryDate;
    }

    public Double getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(Double cashTotal) {
        this.cashTotal = cashTotal;
    }

    public Double getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(Double cardTotal) {
        this.cardTotal = cardTotal;
    }

    public Double getCashBox() {
        return cashBox;
    }

    public void setCashBox(Double cashBox) {
        this.cashBox = cashBox;
    }

    public Double getCard_terminal() {
        return card_terminal;
    }

    public void setCard_terminal(Double card_terminal) {
        this.card_terminal = card_terminal;
    }

    public Double getNonSummaryTotal() {
        return nonSummaryTotal;
    }

    public void setNonSummaryTotal(Double nonSummaryTotal) {
        this.nonSummaryTotal = nonSummaryTotal;
    }

    public Integer getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(Integer numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(String acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    @Override
    public String toString() {
        return "SettlementDay{" +
                "id=" + id +
                ", summaryDate=" + summaryDate +
                ", cashTotal=" + cashTotal +
                ", cardTotal=" + cardTotal +
                ", cashBox=" + cashBox +
                ", card_terminal=" + card_terminal +
                ", nonSummaryTotal=" + nonSummaryTotal +
                ", numberOfReservations=" + numberOfReservations +
                ", isCorrect=" + isCorrect +
                ", acceptedBy='" + acceptedBy + '\'' +
                ", acceptedAt='" + acceptedAt + '\'' +
                '}';
    }
}
