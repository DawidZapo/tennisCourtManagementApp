package com.springboot.tennisCourtManagementApp.security;

import com.springboot.tennisCourtManagementApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MyTaskScheduler {
    private UserService userService;

    @Autowired
    public MyTaskScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void checkAndDisableUsers() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateToCheck = LocalDate.of(2024, 2, 22);

        if (currentDate.isEqual(dateToCheck) || currentDate.isAfter(dateToCheck)) {
            updateDatabase();
        }
    }

    private void updateDatabase() {
        userService.disableUsers();
    }
}
