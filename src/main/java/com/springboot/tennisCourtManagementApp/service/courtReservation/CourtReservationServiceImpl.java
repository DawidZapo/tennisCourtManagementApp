package com.springboot.tennisCourtManagementApp.service.courtReservation;

import com.springboot.tennisCourtManagementApp.dao.CourtReservationRepository;
import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import com.springboot.tennisCourtManagementApp.service.priceSchedule.PriceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourtReservationServiceImpl implements CourtReservationService {
    private CourtReservationRepository courtReservationRepository;
    private PriceScheduleService priceScheduleService;

    @Autowired
    public CourtReservationServiceImpl(CourtReservationRepository courtReservationRepository, PriceScheduleService priceScheduleService) {
        this.courtReservationRepository = courtReservationRepository;
        this.priceScheduleService = priceScheduleService;
    }

    @Override
    public List<CourtReservation> findAll() {
        return courtReservationRepository.findAll();
    }

    @Override
    public CourtReservation findById(int id) {
        Optional<CourtReservation> result = courtReservationRepository.findById(id);
        CourtReservation courtReservation = null;

        if(result.isPresent()){
            courtReservation = result.get();
        }
        else{
            throw new RuntimeException("Did not find courtReservation id: " + id);
        }
        return courtReservation;
    }

    @Override
    public CourtReservation save(CourtReservation courtReservation) {
        return courtReservationRepository.save(courtReservation);
    }

    @Override
    public void deleteById(int id) {
        courtReservationRepository.deleteById(id);
    }

    @Override
    public void delete(CourtReservation courtReservation) {
        courtReservationRepository.delete(courtReservation);
    }

    @Override
    public List<CourtReservation> findAllByReservationDate(LocalDate date) {
        return courtReservationRepository.findAllByReservationDate(date);
    }

    @Override
    public CourtReservation createNewCourtReservation(Integer courtNumber, LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd, Integer priceSchedule, boolean isDoublesMatch, String acceptedBy) {
        PriceSchedule price = priceScheduleService.findById(priceSchedule);

        Double totalPrice = CourtReservation.getCalculatedPrice(reservationDate,timeStart,timeEnd,isDoublesMatch,price);

        return new CourtReservation(courtNumber,reservationDate,timeStart,timeEnd,priceSchedule,totalPrice,isDoublesMatch,false,acceptedBy,true);
    }
    @Override
    public void updatePayment(int id, Boolean isCash) {
        courtReservationRepository.updateIsCashById(id, isCash);
    }

    @Override
    public void updateDiscount(int id, Integer priceSchedule, Boolean isDoublesMatch) {
        Optional<CourtReservation> result = courtReservationRepository.findById(id);
        CourtReservation courtReservation = null;

        if(result.isPresent()){
            courtReservation = result.get();
            courtReservation.setPriceSchedule(priceSchedule);
            courtReservation.setDoublesMatch(isDoublesMatch);
            PriceSchedule price = priceScheduleService.findById(priceSchedule);
            Double newTotalPrice = CourtReservation.getCalculatedPrice(
                    courtReservation.getReservationDate(),
                    courtReservation.getTimeStart(),
                    courtReservation.getTimeEnd(),
                    courtReservation.isDoublesMatch(),
                    price);
            courtReservation.setTotalPrice(newTotalPrice);
            courtReservationRepository.save(courtReservation);
        }
        else{
            throw new RuntimeException("Did not find courtReservation id: " + id);
        }
    }

    @Override
    public void updateTotalPrice(int id, Double newTotalPrice) {
        courtReservationRepository.updateTotalPrice(id,newTotalPrice);
    }

    @Override
    public void updateComments(int id, String comments) {
        courtReservationRepository.updateComments(id, comments);
    }

    @Override
    public void updateIsPaid(int id, Boolean isPaid) {
        courtReservationRepository.updateIsPaid(id,isPaid);
    }

    @Override
    public List<CourtReservation> findByReservationDateAndValidForFinanceSummary(LocalDate date, Boolean valid) {
        return courtReservationRepository.findByReservationDateAndValidForFinanceSummary(date,valid);
    }

    @Override
    public boolean existsByIsCashIsNotNullAndIsPaidTrueAndReservationDate(LocalDate date) {
        return courtReservationRepository.existsByIsCashIsNotNullAndIsPaidTrueAndReservationDate(date);
    }
}
