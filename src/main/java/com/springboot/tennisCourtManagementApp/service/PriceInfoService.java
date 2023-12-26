package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.entity.PriceInfo;

import java.util.List;

public interface PriceInfoService {
    PriceInfo findById(int id);
    List<PriceInfo> findAll();
    PriceInfo save(PriceInfo priceInfo);
}
