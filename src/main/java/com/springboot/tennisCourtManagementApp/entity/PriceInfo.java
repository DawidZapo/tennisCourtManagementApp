package com.springboot.tennisCourtManagementApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "price_info")
public class PriceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description_info")
    private String descriptionInfo;
    @Column(name = "price")
    private Double price;

    public PriceInfo() {
    }

    public PriceInfo(Integer id, String descriptionInfo, Double price) {
        this.id = id;
        this.descriptionInfo = descriptionInfo;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptionInfo() {
        return descriptionInfo;
    }

    public void setDescriptionInfo(String descriptionInfo) {
        this.descriptionInfo = descriptionInfo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceInfo{" +
                "id=" + id +
                ", descriptionInfo='" + descriptionInfo + '\'' +
                ", price=" + price +
                '}';
    }
}
