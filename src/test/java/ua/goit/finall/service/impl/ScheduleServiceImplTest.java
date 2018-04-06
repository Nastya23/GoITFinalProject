package ua.goit.finall.service.impl;

import javafx.concurrent.ScheduledService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.finall.configuration.SpringBootWebApplication;
import ua.goit.finall.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class ScheduleServiceImplTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    ScheduledService scheduledService;

}
