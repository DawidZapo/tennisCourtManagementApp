package com.springboot.tennisCourtManagementApp.service;

import com.springboot.tennisCourtManagementApp.dao.PriceInfoRepository;
import com.springboot.tennisCourtManagementApp.entity.PriceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceInfoServiceImpl implements PriceInfoService{

    private PriceInfoRepository priceInfoRepository;

    @Autowired
    public PriceInfoServiceImpl(PriceInfoRepository priceInfoRepository) {
        this.priceInfoRepository = priceInfoRepository;
    }

    @Override
    public PriceInfo findById(int id) {
        Optional<PriceInfo> result = priceInfoRepository.findById(id);
        PriceInfo priceInfo = null;

        if(result.isPresent()){
            priceInfo = result.get();
        }
        else{
            throw new RuntimeException("Did not find priceInfo id: " + id);
        }
        return priceInfo;
    }

    @Override
    public List<PriceInfo> findAll() {
        return priceInfoRepository.findAll();
    }

    @Override
    public PriceInfo save(PriceInfo priceInfo) {
        return priceInfoRepository.save(priceInfo);
    }
}
