package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.dto.CourtReservationDto;
import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.Customer;
import com.springboot.tennisCourtManagementApp.service.courtReservation.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.customer.CustomerService;
import com.springboot.tennisCourtManagementApp.service.priceSchedule.PriceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CourtReservationController {
    private final CourtReservationService courtReservationService;
    private final PriceScheduleService priceScheduleService;
    private final CustomerService customerService;

    @Autowired
    public CourtReservationController(CourtReservationService courtReservationService, PriceScheduleService priceScheduleService,CustomerService customerService) {
        this.courtReservationService = courtReservationService;
        this.priceScheduleService = priceScheduleService;
        this.customerService = customerService;
    }
    @PostMapping("/reservation/updatePayment")
    public String updatePayment(@RequestParam("id") int id, @RequestParam(name = "payment", required = false) String payment){
        if(payment==null){
            System.out.println("Payment == null");
        }
        else{
            Boolean isCash;
            if(payment.equalsIgnoreCase("cash")){
                isCash = true;
            }
            else if(payment.equalsIgnoreCase("card")){
                isCash = false;
            }
            else{
                isCash = null;
            }
            courtReservationService.updatePayment(id,isCash);
        }
        return "redirect:/reservation?id="+id;
    }

    @PostMapping("/reservation/updateDiscount")
    public String updateDiscount(@RequestParam("id") int id, @RequestParam(name = "discount", required = false) Integer discount, @RequestParam(name = "isDoublesMatch", required = false) String isDoublesMatch){
        if(discount==null || isDoublesMatch==null){
            System.out.println("Discount == null");
        }
        else{
            Boolean tempDoublesMatch;
            if(isDoublesMatch.equalsIgnoreCase("true")){
                tempDoublesMatch = true;
            }
            else if(isDoublesMatch.equals("false")){
                tempDoublesMatch = false;
            }
            else{
                System.out.println("tempDoublesMatch == null");
                tempDoublesMatch = null;
            }
            courtReservationService.updateDiscount(id,discount,tempDoublesMatch);
        }
        return "redirect:/reservation?id="+id;
    }

    @PostMapping("/reservation/updateTotalPrice")
    public String updateTotalPrice(@RequestParam("id") int id, @RequestParam(name="totalPrice", required = false) Double totalPrice){
        if(totalPrice ==0 || totalPrice >= 0.01){
            double truncatedPriceToTwoDecimals = Math.floor(totalPrice*100)/100;
//            courtReservationService.updateTotalPrice(id,truncatedPriceToTwoDecimals);
            CourtReservation courtReservation = courtReservationService.findById(id);
            courtReservation.setLightTotal(0.0);
            courtReservation.setTotalPrice(truncatedPriceToTwoDecimals);
            courtReservationService.save(courtReservation);
        }
        return "redirect:/reservation?id="+id;
    }

    @PostMapping("/reservation/updateLightTotal")
    public String updateLightTotal(@RequestParam("id") int id, @RequestParam(name="lightTotal", required = false) Double lightTotal){
        if(lightTotal ==0 || lightTotal >= 0.01){
            double truncatedLighTotalTwoDecimals = Math.floor(lightTotal*100)/100;
            CourtReservation courtReservation = courtReservationService.findById(id);
            courtReservation.setLightTotal(truncatedLighTotalTwoDecimals);
            courtReservation.setTotalPrice(courtReservation.getTotalPrice()+truncatedLighTotalTwoDecimals);
            courtReservationService.save(courtReservation);
        }
        return "redirect:/reservation?id="+id;
    }


    @PostMapping("/reservation/updateComments")
    public String updateComments(@RequestParam("id") int id, @RequestParam(name = "comments", required = false) String comments){
        if(comments != null){
            courtReservationService.updateComments(id,comments);
        }
        return "redirect:/reservation?id="+id;
    }

    @PostMapping("/reservation/updateIsPaid")
    public String updateIsPaid(@RequestParam("id") int id, @RequestParam("isPaid") Boolean isPaid){
        courtReservationService.updateIsPaid(id, isPaid); // if current state is unpaid we have to invert the boolean to make any changes
        return "redirect:/reservation?id="+id;
    }

    @PostMapping("/reservation/save")
    public String saveReservation(@ModelAttribute("reservationDto")CourtReservationDto reservationDto) {
        List<CourtReservation> reservations = reservationDto.getReservations();
        LocalDate date = reservations.get(0).getReservationDate();
        for(var res : reservations){
            Boolean valid;
            if(res.getValidForFinanceSummary() != null){
                valid = res.getValidForFinanceSummary();
            }
            else{
                valid = true;
            }
            // former way of saving reservation, inefficient bcoz we had to fetch customer with all his reservations in order to save one more
//            Customer customer = customerService.findCustomerByIdJoinFetch(res.getCustomer().getId());
//            customer.addReservation(courtReservationService.createNewCourtReservation(res.getCourtNumber(),res.getReservationDate(),res.getTimeStart(),res.getTimeEnd(),res.getPriceSchedule(),doubles,res.getAcceptedBy()));
//            customerService.save(customer);
            CourtReservation courtReservation = courtReservationService.createNewCourtReservation(res.getCourtNumber(),res.getReservationDate(),res.getTimeStart(),res.getTimeEnd(),res.getPriceSchedule(),false,res.getAcceptedBy(),valid);
            Customer customer = customerService.findById(res.getCustomer().getId());
            courtReservation.setCustomer(customer);
            courtReservationService.save(courtReservation);
        }
        return "redirect:/?date="+date.toString();
    }

    @PostMapping("/reservation/delete")
    public String deleteReservation(@RequestParam("id") int id, @RequestParam("date") LocalDate date){
        courtReservationService.deleteById(id);
        return "redirect:/?date="+date.toString();
    }

    @PostMapping("/reservation/updateIsValidForSummary")
    public String updateIsValidForSummary(@RequestParam("id") int id, @RequestParam("isValidForSummary") Boolean isValidForSummary){
        courtReservationService.updateIsValidForFinanceSummary(id,isValidForSummary);
        return "redirect:/reservation?id="+id;
    }

}
