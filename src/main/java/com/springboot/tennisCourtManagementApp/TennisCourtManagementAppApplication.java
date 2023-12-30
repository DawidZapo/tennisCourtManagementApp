package com.springboot.tennisCourtManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(CourtReservationService courtReservationService, CustomerService customerService, PriceInfoService priceInfoService, PriceScheduleService priceScheduleService) {
//
//		return runner -> {
//			Customer customer = customerService.findCustomerByIdJoinFetch(2);
//			customer.addReservations(List.of(
//					courtReservationService.createNewCourtReservation(1, LocalDate.of(2023,12,31), LocalTime.of(10,0), LocalTime.of(12,30),7,false,"admin"),
//					courtReservationService.createNewCourtReservation(1, LocalDate.of(2023,12,31), LocalTime.of(12,30),LocalTime.of(18,30),1,false,"admin"),
//					courtReservationService.createNewCourtReservation(1, LocalDate.of(2023,12,31), LocalTime.of(18,30),LocalTime.of(21,30),1,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,31), LocalTime.of(8,30),LocalTime.of(9,30),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,31), LocalTime.of(13,30),LocalTime.of(14,30),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(2, LocalDate.of(2023,12,31), LocalTime.of(14,30),LocalTime.of(18,30),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(3, LocalDate.of(2023,12,31), LocalTime.of(17,0),LocalTime.of(18,0),2,false,"admin"),
//					courtReservationService.createNewCourtReservation(3, LocalDate.of(2023,12,31), LocalTime.of(18,0),LocalTime.of(19,30),3,false,"admin"),
//					courtReservationService.createNewCourtReservation(3, LocalDate.of(2023,12,31), LocalTime.of(19,30),LocalTime.of(22,0),3,false,"admin"),
//					courtReservationService.createNewCourtReservation(4, LocalDate.of(2023,12,31), LocalTime.of(11,0),LocalTime.of(13,30),4,false,"admin"),
//					courtReservationService.createNewCourtReservation(4, LocalDate.of(2023,12,31), LocalTime.of(13,30),LocalTime.of(17,0),4,false,"admin"),
//					courtReservationService.createNewCourtReservation(4, LocalDate.of(2023,12,31), LocalTime.of(17,0),LocalTime.of(19,30),5,false,"admin")
//			));
//			customerService.save(customer);
//		};
//	}

}
