package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.PriceScheduleService;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    private CourtReservationService courtReservationService;
    private PriceScheduleService priceScheduleService;

    @Autowired
    public MainController(CourtReservationService courtReservationService, PriceScheduleService priceScheduleService) {
        this.courtReservationService = courtReservationService;
        this.priceScheduleService = priceScheduleService;
    }

    @GetMapping("/")
    public String home(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        if (date == null) {
            date = LocalDate.now();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }

        String dayOfWeekPolish = getPolishDayOfWeekString(date);

        List<CourtReservation> reservations = courtReservationService.findAllByReservationDate(date);

        List<PriceSchedule> priceSchedules = priceScheduleService.findAll();


        model.addAttribute("reservations", reservations);
        model.addAttribute("dayOfWeek",dayOfWeekPolish);
        model.addAttribute("date", date.toString());

        return "home";
    }

    @GetMapping("/reservation")
    public String showReservation(@RequestParam("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        CourtReservation courtReservation = courtReservationService.findById(id);
        PriceSchedule priceSchedule = priceScheduleService.findById(courtReservation.getPriceSchedule());
        List<PriceSchedule> discounts = priceScheduleService.findAll();

        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.POLAND));
        boolean isHoliday = holidayManager.isHoliday(courtReservation.getReservationDate());
        String dayOfWeek = getPolishDayOfWeekString(courtReservation.getReservationDate());

        model.addAttribute("dayOfWeek", dayOfWeek);
        model.addAttribute("isHoliday", isHoliday);
        model.addAttribute("discounts", discounts);
        model.addAttribute("reservation", courtReservation);
        model.addAttribute("priceSchedule", priceSchedule);
        return "reservation-detail-look";
    }

    @PostMapping("reservation/updatePayment")
    public String saveReservation(@RequestParam("id") int id, @RequestParam(name = "payment", required = false) String payment){
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

    @PostMapping("reservation/updateDiscount")
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
            courtReservationService.updateTotalPrice(id,truncatedPriceToTwoDecimals);
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
        courtReservationService.updateIsPaid(id, !isPaid); // if current state is unpaid we have to invert the boolean to make any changes
        return "redirect:/reservation?id="+id;
    }

    private String getPolishDayOfWeekString(LocalDate date){
        String dayOfWeekEN = date.getDayOfWeek().toString();
        return switch(dayOfWeekEN){
            case "MONDAY" -> "Poniedziałek";
            case "TUESDAY" -> "Wtorek";
            case "WEDNESDAY" -> "Środa";
            case "THURSDAY" -> "Czwartek";
            case "FRIDAY" -> "Piątek";
            case "SATURDAY" -> "Sobota";
            case "SUNDAY" -> "Niedziela";
            default -> "BŁĄD";
        };
    }
}
