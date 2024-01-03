package com.springboot.tennisCourtManagementApp.controller;

import com.springboot.tennisCourtManagementApp.dto.CourtReservationDto;
import com.springboot.tennisCourtManagementApp.dto.CustomerDto;
import com.springboot.tennisCourtManagementApp.dto.PriceScheduleDto;
import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.entity.Customer;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.CustomerService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {
    private CourtReservationService courtReservationService;
    private PriceScheduleService priceScheduleService;
    private CustomerService customerService;

    @Autowired
    public MainController(CourtReservationService courtReservationService, PriceScheduleService priceScheduleService, CustomerService customerService) {
        this.courtReservationService = courtReservationService;
        this.priceScheduleService = priceScheduleService;
        this.customerService = customerService;
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

        List<Customer> customers = customerService.findAll();


        model.addAttribute("reservations", reservations);
        model.addAttribute("dayOfWeek",dayOfWeekPolish);
        model.addAttribute("date", date);
        model.addAttribute("customers", customers);

        return "home";
    }

    @GetMapping("/reservation")
    public String showReservationDetailLook(@RequestParam("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        CourtReservation courtReservation = courtReservationService.findById(id);
        PriceSchedule priceSchedule = priceScheduleService.findById(courtReservation.getPriceSchedule());
        List<PriceSchedule> discounts = priceScheduleService.findAll();

        LocalDate date = courtReservation.getReservationDate();
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.POLAND));
        boolean isHoliday = holidayManager.isHoliday(date);
        String dayOfWeek = getPolishDayOfWeekString(date);

        model.addAttribute("dayOfWeek", dayOfWeek);
        model.addAttribute("isHoliday", isHoliday);
        model.addAttribute("discounts", discounts);
        model.addAttribute("reservation", courtReservation);
        model.addAttribute("priceSchedule", priceSchedule);
        model.addAttribute("date", date);
        return "reservation-detail-look";
    }

    @GetMapping("/addReservation")
    public String showReservationForm(@RequestParam(value = "selectedTiles", required = false) String selectedTilesParam,
                                      @RequestParam(value = "date", required = false) LocalDate date, Model model) {

        String[] selectedTiles = selectedTilesParam != null ? selectedTilesParam.split(",") : new String[0];
        List<String> court1Tiles = new ArrayList<>();
        List<String> court2Tiles = new ArrayList<>();
        List<String> court3Tiles = new ArrayList<>();
        List<String> court4Tiles = new ArrayList<>();
        List<String> court5Tiles = new ArrayList<>();

        for (String tile : selectedTiles) {
            char lastChar = tile.charAt(tile.length()-1);
            switch(lastChar){
                case '1' -> court1Tiles.add(tile);
                case '2' -> court2Tiles.add(tile);
                case '3' -> court3Tiles.add(tile);
                case '4' -> court4Tiles.add(tile);
                case '5' -> court5Tiles.add(tile);
            }
        }

        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
            model.addAttribute("username", username);
        }

        List<List<String>> allCourtsTiles = Arrays.asList(court1Tiles, court2Tiles, court3Tiles, court4Tiles, court5Tiles);
        List<CourtReservation> reservations = getPossibleReservations(allCourtsTiles,date);
        CourtReservationDto courtReservationDto = new CourtReservationDto();
        courtReservationDto.setReservations(reservations);
        if(username != null){
            for(var res : courtReservationDto.getReservations()){
                res.setAcceptedBy(username);
            }
        }

        List<Customer> customers = customerService.findAll();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomers(customers);

        List<PriceSchedule> priceSchedules = priceScheduleService.findAll();
        PriceScheduleDto priceScheduleDto = new PriceScheduleDto();
        priceScheduleDto.setPriceSchedules(priceSchedules);

        model.addAttribute("selectedTiles", selectedTiles);
        model.addAttribute("courtReservationDto", courtReservationDto);
        model.addAttribute("customerDto", customerDto);
        model.addAttribute("priceSchedulesDto", priceScheduleDto);


        return "add-reservation";
    }
    private List<CourtReservation> getPossibleReservations(List<List<String>> allCourtsTiles, LocalDate date){
        List<CourtReservation> possibleReservations = new ArrayList<>();
        for (List<String> courtTiles : allCourtsTiles) {
            if (!courtTiles.isEmpty()) {
                Collections.sort(courtTiles); // Posortowanie listy kafelków dla danego kortu
                Integer courtNumber = Integer.parseInt(courtTiles.get(0).substring(courtTiles.get(0).length() - 1));

                LocalTime startTime = null;
                LocalTime endTime = null;

                for (String tile : courtTiles) {
                    LocalTime time = LocalTime.parse(tile.substring(0, 5)); // Pobranie godziny rozpoczęcia

                    if (startTime == null) {
                        startTime = time;
                        endTime = time.plusMinutes(30);
                    } else if (time.equals(endTime)) {
                        endTime = endTime.plusMinutes(30);
                    } else {
                        possibleReservations.add(new CourtReservation(courtNumber, date, startTime, endTime));
                        startTime = time;
                        endTime = time.plusMinutes(30);
                    }
                }

                // Dodanie ostatniej rezerwacji, jeśli istnieje
                if (startTime != null && endTime != null) {
                    possibleReservations.add(new CourtReservation(courtNumber, date, startTime, endTime));
                }
            }
        }
        return possibleReservations;
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
