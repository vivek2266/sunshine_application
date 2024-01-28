package com.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dl.entity.Roles;
import com.dl.service.RolesService;
import com.dl.service.StoreAttributeDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController

public class RolesController {

	@Autowired
	private RolesService rolesService;

	@Operation(summary = "add a new roles to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "roles added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolesService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addRole")
	public Roles saveRoles(@RequestBody Roles roles) {
		return rolesService.addRoles(roles);
	}

	@Operation(summary = "Fetch all roles object")

	@GetMapping("/getAllRolles")
	public List<Roles> getAllRolles() {
		return rolesService.getAllRoles();
	}

	@Operation(summary = "Delete roles by rolesId")

	@DeleteMapping("/roleDelete/{rolesId}")
	public String deleteRoleById(int rolesId) {

		rolesService.deleteRolesById(rolesId);
		return "deleted sucessfully";
	}

	@Operation(summary = "updateRoles into the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updateRoles added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolesService.class)) }),
			@ApiResponse(responseCode = "400", description = "updateRoles not found with given details") })

	@PutMapping("/updateRoles/{rolesId}")
	public Roles updateRole(int rolesId, Roles roles) {

		return rolesService.updateRoles(rolesId, roles);
	}

	@Operation(summary = "add assignGroupsToRoles to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "assignGroupsToRoles added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolesService.class)) }),
			@ApiResponse(responseCode = "400", description = "GroupsOrRoles not found with given details") })

	@PutMapping("/{roleId}/assignGroupsToRoles")
	public Roles assignGroupsToRoles(@PathVariable int roleId, @RequestParam List<Integer> groups) {

		return rolesService.assignGroupsToRoles(roleId, groups);
	}

	@Operation(summary = " get RolesGroupsPermissionsByRolesId to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "RolesGroupsPermissionsByRolesId get successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RolesService.class)) }),
			@ApiResponse(responseCode = "400", description = "RolesGroupsPermissionsByRolesId not found with given details") })

	@GetMapping("/getRolesGroupsPermissionsByRolesId/{rolesId}")
	public List<Object[]> getRolesGroupsPermissionsByRolesId(@PathVariable int rolesId) {
		return rolesService.getRolesGroupsPermissionsByRolesId(rolesId);
	}

}
