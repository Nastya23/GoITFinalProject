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
import ua.goit.finall.model.Role;
import ua.goit.finall.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/roles")
@Secured({"ROLE_ADMIN"})
@Api(value="roles", description="Operations pertaining to roles")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "View a list of available roles",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Wrong arguments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    /** GET ALL ROLE
     *
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = this.roleService.getAll();

        if (roleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    /** GET ROLE
     *
     */
    @ApiOperation(value = "Search a role with an ID",response = Role.class)
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("roleId") Long roleId) {
        if (roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    /** SAVE ROLE
     *
     */
    @ApiOperation(value = "Save a role")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody @Valid Role role, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.roleService.save(role);
        headers.setLocation(builder.path("/api/roles/{roleId}").buildAndExpand(role.getId()).toUri());
        return new ResponseEntity<>(role, headers, HttpStatus.CREATED);
    }

    /** DELETE ROLE
     *
     */
    @ApiOperation(value = "Delete a role")
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> deleteRole(@PathVariable("roleId") Long roleId) {
        Role role = this.roleService.getById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.roleService.delete(roleId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
