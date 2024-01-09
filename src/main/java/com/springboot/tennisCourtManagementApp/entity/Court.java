package com.springboot.tennisCourtManagementApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "court")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "court_number")
    private Integer courtNumber;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_functional")
    private Boolean isFunctional;
    @Column(name = "is_flooded")
    private Boolean isFlooded;

    public Court() {
    }

    public Court(Integer id, Integer courtNumber, Boolean isActive, Boolean isFunctional, Boolean isFlooded) {
        this.id = id;
        this.courtNumber = courtNumber;
        this.isActive = isActive;
        this.isFunctional = isFunctional;
        this.isFlooded = isFlooded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourtNumber() {
        return courtNumber;
    }

    public void setCourtNumber(Integer courtNumber) {
        this.courtNumber = courtNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    public Boolean getFunctional() {
        return isFunctional;
    }

    public void setFunctional(Boolean functional) {
        isFunctional = functional;
    }

    public Boolean getFlooded() {
        return isFlooded;
    }

    public void setFlooded(Boolean flooded) {
        isFlooded = flooded;
    }
}
