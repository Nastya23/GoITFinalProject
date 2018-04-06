package ua.goit.finall.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Salary;
import ua.goit.finall.model.User;
import ua.goit.finall.service.EmployeeService;
import ua.goit.finall.service.SalaryService;
import ua.goit.finall.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static ua.goit.finall.utils.Utils.getLoggedinUserName;

@RestController
@RequestMapping(value = "/personalSalaries")
@Api(value="personalSalaries", description="Operations pertaining to personal salaries")
public class PersonalSalaryRestController {

    @Autowired
    SalaryService salaryService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "View a list of available personal salary",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    /** GET PERSONAL SALARIES
     *
     */
    @PreAuthorize("#username == principal.username")
    @RequestMapping(path = "/{username}/{fromYear}/{fromMonth}/{toYear}/{toMonth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Salary>> getPersonalSalaries(@PathVariable("username") String username, @PathVariable("fromYear") Integer fromYear, @PathVariable("fromMonth") Integer fromMonth,
                                                            @PathVariable("toYear") Integer toYear, @PathVariable("toMonth") Integer toMonth) {
        User user = userService.findUserByUsername(username);
        Long employeeId = employeeService.findEmployeeByUser(user).getId();
        List<Salary> salaryList = this.salaryService.getPersonalSalaries(employeeId, fromYear, fromMonth, toYear, toMonth);

        if (salaryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(salaryList, HttpStatus.OK);
    }
}
