package com.springboot.tennisCourtManagementApp;

import com.springboot.tennisCourtManagementApp.service.CourtReservationService;
import com.springboot.tennisCourtManagementApp.service.CustomerService;
import com.springboot.tennisCourtManagementApp.service.PriceInfoService;
import com.springboot.tennisCourtManagementApp.service.PriceScheduleService;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TennisCourtManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisCourtManagementAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourtReservationService courtReservationService, CustomerService customerService, PriceInfoService priceInfoService, PriceScheduleService priceScheduleService) {

		return runner -> {
			HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.POLAND));
			System.out.println("24.12.2023: " + holidayManager.isHoliday(LocalDate.of(2023, 12, 24)));
			System.out.println("25.12.2023: " + holidayManager.isHoliday(LocalDate.of(2023, 12, 25)));
			System.out.println("26.12.2023: " + holidayManager.isHoliday(LocalDate.of(2023, 12, 26)));
			System.out.println("27.12.2023: " + holidayManager.isHoliday(LocalDate.of(2023, 12, 27)));
			System.out.println("01.05.2024: " + holidayManager.isHoliday(LocalDate.of(2024, 5, 1)));
			System.out.println("02.05.2024: " + holidayManager.isHoliday(LocalDate.of(2024, 5, 2)));
			System.out.println("03.05.2024: " + holidayManager.isHoliday(LocalDate.of(2024, 5, 3)));
			System.out.println("04.05.2024: " + holidayManager.isHoliday(LocalDate.of(2024, 5, 4)));
			System.out.println("30.05.2024: " + holidayManager.isHoliday(LocalDate.of(2024, 5, 30)));
		};
	}

}
