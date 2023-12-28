package com.springboot.tennisCourtManagementApp;

import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.CustomerService;
import com.springboot.tennisCourtManagementApp.service.PriceInfoService;
import com.springboot.tennisCourtManagementApp.service.PriceScheduleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourtReservationService courtReservationService, CustomerService customerService, PriceInfoService priceInfoService, PriceScheduleService priceScheduleService) {

		return runner -> {
//			Customer customer = customerService.findCustomerByIdJoinFetch(1);
//			customer.addReservations(List.of(
//					courtReservationService.createNewCourtReservation(1, LocalDate.of(2023,12,28), LocalTime.of(11,0), LocalTime.of(13,30),1,false,"admin"),
//					courtReservationService.createNewCourtReservation(1, LocalDate.of(2023,12,28), LocalTime.of(16,0),LocalTime.of(18,30),1,false,"admin"),
//					courtReservationService.createNewCourtReservation(1, LocalDate.of(2023,12,28), LocalTime.of(18,30),LocalTime.of(21,30),1,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,28), LocalTime.of(8,0),LocalTime.of(9,0),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,28), LocalTime.of(9,0),LocalTime.of(10,30),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,28), LocalTime.of(10,30),LocalTime.of(12,30),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,28), LocalTime.of(16,0),LocalTime.of(18,0),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(3, LocalDate.of(2023,12,28), LocalTime.of(8,30),LocalTime.of(14,30),3,false,"admin"),
//					courtReservationService.createNewCourtReservation(3, LocalDate.of(2023,12,28), LocalTime.of(15,0),LocalTime.of(22,0),3,false,"admin"),
//					courtReservationService.createNewCourtReservation(4, LocalDate.of(2023,12,28), LocalTime.of(10,0),LocalTime.of(14,30),4,false,"admin"),
//					courtReservationService.createNewCourtReservation(5, LocalDate.of(2023,12,28), LocalTime.of(13,0),LocalTime.of(17,0),4,false,"admin"),
//					courtReservationService.createNewCourtReservation(5, LocalDate.of(2023,12,28), LocalTime.of(17,0),LocalTime.of(19,30),5,false,"admin")
//			));
//			customerService.save(customer);
		};
	}

}
