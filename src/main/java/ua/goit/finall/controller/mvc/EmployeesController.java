package ua.goit.finall.controller.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.finall.model.Employee;
import ua.goit.finall.model.User;
import ua.goit.finall.service.EmployeeService;
import ua.goit.finall.service.UserService;

import java.util.Map;

import static ua.goit.finall.utils.Utils.getLoggedinPrincipal;
import static ua.goit.finall.utils.Utils.getLoggedinUserName;

@Controller
public class EmployeesController {

    private Logger logger = LoggerFactory.getLogger(EmployeesController.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/employeeInfo")
    public String employeeInfo(Map<String, Object> model) {
        String userName = getLoggedinUserName();
        User user = userService.findUserByUsername(userName);
        Employee employee = employeeService.findEmployeeByUser(user);
        model.put("employee", employee);
        UserDetails principal = getLoggedinPrincipal();
        model.put("principal", principal);
        logger.info("Empn - " + userName);
        logger.info("Event - " + employee.getEventList());

        return "employeeInfo";
    }

}
