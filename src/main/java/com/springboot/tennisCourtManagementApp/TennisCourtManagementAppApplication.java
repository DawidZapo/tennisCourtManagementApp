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
//
//		};
//	}

}
