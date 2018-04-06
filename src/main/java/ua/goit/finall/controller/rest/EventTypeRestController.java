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
import ua.goit.finall.model.EventType;
import ua.goit.finall.service.EventTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/eventTypes")
@Api(value="eventTypes", description="Operations pertaining to event types")
public class EventTypeRestController {

    @Autowired
    private EventTypeService eventTypeService;

    @ApiOperation(value = "View a list of available event type",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    /** GET ALL EVENT TYPE
     *
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<EventType>> getAllEventTypes() {
        List<EventType> eventTypeList = this.eventTypeService.getAll();

        if (eventTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventTypeList, HttpStatus.OK);
    }

    /** GET EVENT TYPE
     *
     */
    @ApiOperation(value = "Search an event type with an ID",response = EventType.class)
    @RequestMapping(value = "/{eventTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> getEventType(@PathVariable("eventTypeId") Long eventTypeId) {
        if (eventTypeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        EventType eventType = this.eventTypeService.getById(eventTypeId);

        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventType, HttpStatus.OK);
    }

    /** SAVE EVENT TYPE
     *
     */
    @ApiOperation(value = "Save an event type")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> saveEventType(@RequestBody @Valid EventType eventType, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.eventTypeService.save(eventType);
        headers.setLocation(builder.path("/api/eventTypes/{eventTypeId}").buildAndExpand(eventType.getId()).toUri());
        return new ResponseEntity<>(eventType, headers, HttpStatus.CREATED);
    }

    /** DELETE EVENT TYPE
     *
     */
    @ApiOperation(value = "Delete an event type")
    @RequestMapping(value = "/{eventTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> deleteEventType(@PathVariable("eventTypeId") Long eventTypeId) {
        EventType eventType = this.eventTypeService.getById(eventTypeId);

        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.eventTypeService.delete(eventTypeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
