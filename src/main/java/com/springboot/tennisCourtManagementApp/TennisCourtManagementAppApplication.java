package com.springboot.tennisCourtManagementApp;

import com.springboot.tennisCourtManagementApp.entity.PriceInfo;
import com.springboot.tennisCourtManagementApp.entity.PriceSchedule;
import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.CustomerService;
import com.springboot.tennisCourtManagementApp.service.PriceInfoService;
import com.springboot.tennisCourtManagementApp.service.PriceScheduleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourtReservationService courtReservationService, CustomerService customerService, PriceInfoService priceInfoService, PriceScheduleService priceScheduleService) {

		return runner -> {
//			Customer customerDawid = customerService.findCustomerByIdJoinFetch(1);
//			Customer customerHania = customerService.findCustomerByIdJoinFetch(2);
//			customerDawid.addReservations(List.of(
//					new CourtReservation(5, LocalDate.of(2023,12,24), LocalTime.of(16,30), LocalTime.of(17,30),1,false,false),
//					new CourtReservation(5, LocalDate.of(2023,12,24), LocalTime.of(17,30), LocalTime.of(19,30),1,false,false),
//					new CourtReservation(5, LocalDate.of(2023,12,24), LocalTime.of(19,30), LocalTime.of(21,0),1,false,false),
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
//			customerHania.addReservation(new CourtReservation(4, LocalDate.of(2023,12,24), LocalTime.of(8,0), LocalTime.of(8,30),1,false,false));
//			customerHania.addReservation(new CourtReservation(4, LocalDate.of(2023,12,24), LocalTime.of(8,30), LocalTime.of(9,0),1,false,false));
//			customerHania.addReservation(new CourtReservation(4, LocalDate.of(2023,12,24), LocalTime.of(9,30), LocalTime.of(10,0),1,false,false));
//
//			customerService.save(customerHania);

			PriceSchedule priceSchedule = priceScheduleService.findById(1);
			List<PriceSchedule> listSchedule = priceScheduleService.findAll();

			PriceInfo priceInfo = priceInfoService.findById(1);
			List<PriceInfo> listInfo = priceInfoService.findAll();

			System.out.println(priceSchedule);
			System.out.println(priceInfo);

			for(var schedule : listSchedule){
				System.out.println(schedule);
			}

			for(var info : listInfo){
				System.out.println(info);
			}

		};
	}

}
