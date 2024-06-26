package com.springboot.tennisCourtManagementApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @JsonIgnore
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CourtReservation> courtReservations;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CourtReservation> getCourtReservations() {
        return courtReservations;
    }

    public void setCourtReservations(List<CourtReservation> courtReservations) {
        this.courtReservations = courtReservations;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void addReservation(CourtReservation courtReservation){
        if(courtReservations == null){
            courtReservations = new ArrayList<>();
        }
        courtReservations.add(courtReservation);
        courtReservation.setCustomer(this);
    }
    public void addReservations(List<CourtReservation> listOfReservations){
        if(courtReservations == null){
            courtReservations = new ArrayList<>();
        }
        for(var courtReservation : listOfReservations){
            courtReservations.add(courtReservation);
            courtReservation.setCustomer(this);
        }
    }
}
