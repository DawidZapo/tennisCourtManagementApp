package com.springboot.tennisCourtManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(SettlementDayService settlementDayService, CourtReservationService courtReservationService) {
//
//		return runner -> {
//
//		};
//	}

}
