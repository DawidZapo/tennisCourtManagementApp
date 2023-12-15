package com.springboot.tennisCourtManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(CourtReservationService courtReservationService, CustomerService customerService) {
//
//		return runner -> {
////			Customer customer = customerService.findCustomerByIdJoinFetch(2);
////			customer.addReservation(
////					new CourtReservation(2, LocalDate.of(2023,12,15), LocalTime.of(14,0), LocalTime.of(14,30),1,false,false)
////			);
////
////			customerService.save(customer);
//
//		};
//	}

}
