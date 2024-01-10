package com.springboot.tennisCourtManagementApp.service.court;

import com.springboot.tennisCourtManagementApp.dao.CourtRepository;
import com.springboot.tennisCourtManagementApp.entity.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService {
    private CourtRepository courtRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Court> findAll() {
        return courtRepository.findAll();
    }

    @Override
    public Court findById(int id) {
        Optional<Court> result = courtRepository.findById(id);
        Court court = null;

        if(result.isPresent()){
            court = result.get();
        }
        else{
            throw new RuntimeException("Did not find court id: " + id);
        }
        return court;
    }

    @Override
    public Court save(Court court) {
        return courtRepository.save(court);
    }

    @Override
    public void updateCourtAttributes(int id,boolean isActive, boolean isFlooded){
        Court court = findById(id);
        court.setActive(isActive);
        court.setFlooded(isFlooded);
        if(!court.getFunctional() || court.getFlooded()){
            court.setActive(false);
        }
        else{
            court.setActive(true);
        }
    }

    @Override
    public void changeFlooded(int id) {
        Court court = findById(id);
        court.setFlooded(!court.getFlooded());
        updateIsActive(court);
        courtRepository.save(court);

    }

    @Override
    public void changeFunctional(int id) {
        Court court = findById(id);
        court.setFunctional(!court.getFunctional());
        updateIsActive(court);
        courtRepository.save(court);

    }
    private void updateIsActive(Court court){
        if(!court.getFunctional() || court.getFlooded()){
            court.setActive(false);
        }
        else{
            court.setActive(true);
        }
    }
}
