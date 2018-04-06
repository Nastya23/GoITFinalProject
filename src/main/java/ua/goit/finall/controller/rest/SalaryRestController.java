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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Department;
import ua.goit.finall.model.Salary;
import ua.goit.finall.service.SalaryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/salaries")
@Api(value="salaries", description="Operations pertaining to salaries")
public class SalaryRestController {

    @Autowired
    private SalaryService salaryService;

    @ApiOperation(value = "View a list of available salarys",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    /** GET ALL SALARY
     *
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Salary>> getAllSalaries() {
        List<Salary> salaryList = this.salaryService.getAll();

        if (salaryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(salaryList, HttpStatus.OK);
    }

    /** GET SALARY
     *
     */
    @ApiOperation(value = "Search a salary with an ID",response = Salary.class)
    @RequestMapping(value = "/{salaryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Salary> getSalary(@PathVariable("salaryId") Long salaryId) {
        if (salaryId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Salary salary = this.salaryService.getById(salaryId);

        if (salary == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    /** SAVE SALARY
     *
     */
    @ApiOperation(value = "Save a salary")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Salary> saveSalary(@RequestBody @Valid Salary salary, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (salary == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.salaryService.save(salary);
        headers.setLocation(builder.path("/api/salarys/{salaryId}").buildAndExpand(salary.getId()).toUri());
        return new ResponseEntity<>(salary, headers, HttpStatus.CREATED);
    }

    /** DELETE SALARY
     *
     */
    @ApiOperation(value = "Delete a salary")
    @RequestMapping(value = "/{salaryId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Salary> deleteSalary(@PathVariable("salaryId") Long salaryId) {
        Salary salary = this.salaryService.getById(salaryId);

        if (salary == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.salaryService.delete(salaryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
