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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Department;
import ua.goit.finall.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/departments")
@Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
@Api(value="departments", description="Operations pertaining to departments")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "View a list of available departmets",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    /** GET ALL DEPARTMENT
     *
     */

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentList = this.departmentService.getAll();

        if (departmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    /** GET DEPARTMENT
     *
     */
    @ApiOperation(value = "Search a department with an ID",response = Department.class)
    @RequestMapping(value = "/{departmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> getDepartment(@PathVariable("departmentId") Long departmentId) {
        if (departmentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = this.departmentService.getById(departmentId);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    /** SAVE DEPARTMENT
     *
     */
    @ApiOperation(value = "Save a department")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> saveDepartment(@RequestBody @Valid Department department, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.departmentService.save(department);
        headers.setLocation(builder.path("/api/departments/{departmentId}").buildAndExpand(department.getId()).toUri());
        return new ResponseEntity<>(department, headers, HttpStatus.CREATED);
    }

    /** DELETE DEPARTMENT
     *
     */
    @ApiOperation(value = "Delete a department")
    @RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        Department department = this.departmentService.getById(departmentId);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.departmentService.delete(departmentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
