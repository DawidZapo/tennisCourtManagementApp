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
//			Customer customer = customerService.findCustomerByIdJoinFetch(1);
//			customer.addReservation(
//					new CourtReservation(1, LocalDate.of(2023,12,23), LocalTime.of(14,0), LocalTime.of(16,0),1,false,false)
//
//			);
//			customer.addReservation(
//					new CourtReservation(2, LocalDate.of(2023,12,23), LocalTime.of(14,0), LocalTime.of(15,3),1,false,false)
//
//			);
//
//			customerService.save(customer);
//			List<CourtReservation> reservations = courtReservationService.findAllByReservationDate(LocalDate.now());
//			System.out.println(reservations.get(0));
//
//		};
//	}

}
