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
import ua.goit.finall.model.Status;
import ua.goit.finall.service.StatusService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/statuses")
@Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
@Api(value="statuses", description="Operations pertaining to statuses")
public class StatusRestController {

    @Autowired
    private StatusService statusService;

    @ApiOperation(value = "View a list of available status",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    /** GET ALL STATUS
     *
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statusList = this.statusService.getAll();

        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(statusList, HttpStatus.OK);
    }

    /** GET STATUS
     *
     */
    @ApiOperation(value = "Search a status with an ID",response = Status.class)
    @RequestMapping(value = "/{statusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> getStatus(@PathVariable("statusId") Long statusId) {
        if (statusId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Status status = this.statusService.getById(statusId);

        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /** SAVE STATUS
     *
     */
    @ApiOperation(value = "Save a status")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> saveStatus(@RequestBody @Valid Status status, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (status == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.statusService.save(status);
        headers.setLocation(builder.path("/api/statuss/{statusId}").buildAndExpand(status.getId()).toUri());
        return new ResponseEntity<>(status, headers, HttpStatus.CREATED);
    }

    /** DELETE STATUS
     *
     */
    @ApiOperation(value = "Delete a status")
    @RequestMapping(value = "/{statusId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> deleteStatus(@PathVariable("statusId") Long statusId) {
        Status status = this.statusService.getById(statusId);

        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.statusService.delete(statusId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
