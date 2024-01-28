package com.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.entity.Permissions;
import com.dl.service.GroupsService;
import com.dl.service.PermissionService;
import com.dl.service.StoreAttributeDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class PermissionController {

	@Autowired
	private PermissionService permissionservice;

	@Operation(summary = "add a new Permission to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Permission added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addPermission")
	public Permissions addNewPermission(@RequestBody Permissions permission) {
		return permissionservice.addPermissions(permission);

	}

	@Operation(summary = "Fetch all permission object")

	@GetMapping("/getAllPermission")
	public List<Permissions> getAllPermission() {
		return permissionservice.getAllPermission();
	}

	@Operation(summary = "Delete permission by permissionId")
	@DeleteMapping("/deletePermission/{permissionId}")
	public String deletepermissionById(int permissionId) {

		permissionservice.deletePermissionById(permissionId);
		return "deleted sucessfully";
	}

	@Operation(summary = "add  updatePermission to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updatePermission added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PermissionService.class)) }),
			@ApiResponse(responseCode = "400", description = "Permissions not found with given details") })
	@PutMapping("/updatePermission/{permissionId}")

	public Permissions updatePermission(int permissionId, Permissions Permissions) {

		return permissionservice.updatePermission(permissionId, Permissions);
	}

}
