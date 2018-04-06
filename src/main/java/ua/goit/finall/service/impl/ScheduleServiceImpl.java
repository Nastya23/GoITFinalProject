package ua.goit.finall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.goit.finall.service.EmployeeService;

import java.time.LocalDate;

/**
 * The service's for Schedule, contains method
 */
@Component
public class ScheduleServiceImpl {
    private Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class.getName());

    /**
     * Schedule repository's is called for use it's method
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Pass letter
     */
    @Scheduled(cron = "30 15 1 * * *")
    public void passLetter() {
        employeeService.calculateSalary(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        logger.info("Email send");
    }
}