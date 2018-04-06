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
import ua.goit.finall.model.Event;
import ua.goit.finall.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
@Api(value="events", description="Operations pertaining to events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @ApiOperation(value = "View a list of available events",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    /** GET ALL EVENT
     *
     */

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> eventList = this.eventService.getAll();

        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    /** GET EVENT
     *
     */
    @ApiOperation(value = "Search an event with an ID",response = Event.class)
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("eventId") Long eventId) {
        if (eventId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Event event = this.eventService.getById(eventId);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /** SAVE EVENT
     *
     */
    @ApiOperation(value = "Save an event")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid Event event, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.eventService.save(event);
        headers.setLocation(builder.path("/api/events/{eventId}").buildAndExpand(event.getId()).toUri());
        return new ResponseEntity<>(event, headers, HttpStatus.CREATED);
    }

    /** DELETE EVENT
     *
     */
    @ApiOperation(value = "Delete an event")
    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> deleteEvent(@PathVariable("eventId") Long eventId) {
        Event event = this.eventService.getById(eventId);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.eventService.delete(eventId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

