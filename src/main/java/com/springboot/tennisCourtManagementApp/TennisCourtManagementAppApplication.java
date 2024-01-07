package com.springboot.tennisCourtManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(SettlementDayService settlementDayService) {
//
//		return runner -> {
//			LocalDate date = LocalDate.of(2024,1,6);
//			SettlementDay settlementDay = settlementDayService.findBySummaryDate(date);
//			System.out.println(settlementDay);
//
//		};
//	}

}
