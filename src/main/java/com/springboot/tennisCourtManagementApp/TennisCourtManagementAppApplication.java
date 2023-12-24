package com.springboot.tennisCourtManagementApp;

import com.springboot.tennisCourtManagementApp.entity.CourtReservation;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourtReservationService courtReservationService, CustomerService customerService) {

		return runner -> {
//			Customer customerDawid = customerService.findCustomerByIdJoinFetch(1);
//			Customer customerHania = customerService.findCustomerByIdJoinFetch(2);
//			customerDawid.addReservations(List.of(
//					new CourtReservation(1, LocalDate.of(2023,12,24), LocalTime.of(8,0), LocalTime.of(10,30),1,false,false),
//					new CourtReservation(1, LocalDate.of(2023,12,24), LocalTime.of(11,0), LocalTime.of(13,30),1,false,false),
//					new CourtReservation(1, LocalDate.of(2023,12,24), LocalTime.of(13,30), LocalTime.of(16,0),1,false,false),
//					new CourtReservation(2, LocalDate.of(2023,12,24), LocalTime.of(10,0), LocalTime.of(12,30),1,false,false),
//					new CourtReservation(2, LocalDate.of(2023,12,24), LocalTime.of(16,0), LocalTime.of(17,0),1,false,false),
//					new CourtReservation(2, LocalDate.of(2023,12,24), LocalTime.of(17,0), LocalTime.of(18,30),1,false,false)
//			));
//			customerHania.addReservations(List.of(
//					new CourtReservation(3, LocalDate.of(2023,12,24), LocalTime.of(11,0), LocalTime.of(13,0),1,false,false),
//					new CourtReservation(3, LocalDate.of(2023,12,24), LocalTime.of(13,0), LocalTime.of(16,30),1,false,false),
//					new CourtReservation(3, LocalDate.of(2023,12,24), LocalTime.of(17,0), LocalTime.of(18,0),1,false,false),
//					new CourtReservation(4, LocalDate.of(2023,12,24), LocalTime.of(12,0), LocalTime.of(18,30),1,false,false),
//					new CourtReservation(5, LocalDate.of(2023,12,24), LocalTime.of(16,0), LocalTime.of(17,3),1,false,false),
//					new CourtReservation(5, LocalDate.of(2023,12,24), LocalTime.of(17,3), LocalTime.of(20,30),1,false,false)
//			));
//
//
//			customerService.save(customerDawid);
//			customerService.save(customerHania);

			List<CourtReservation> reservations = courtReservationService.findAllByReservationDate(LocalDate.now());
			for(var res : reservations){
				System.out.println(res);
			}

		};
	}

}
