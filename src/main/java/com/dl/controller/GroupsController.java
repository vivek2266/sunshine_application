package com.dl.controller;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.entity.MyGroups;
import com.dl.service.DayService;
import com.dl.service.GroupsService;
import com.dl.service.StoreAttributeDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class GroupsController {

	@Autowired
	private GroupsService groupService;

	@Operation(summary = "add a new Groups to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Groups added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GroupsService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addGroup")
	public MyGroups saveNewGroup(@RequestBody MyGroups entity) {
		return groupService.addGroups(entity);

	}

	@Operation(summary = "Fetch all groups object")

	@GetMapping("/getAllGroups")
	public List<MyGroups> getAllGroups() {
		return groupService.getAllGroups();
	}

	@Operation(summary = "Delete groups by groupId")
	@DeleteMapping("/groupDelete/{groupId}")
	public String deleteGroupById(int groupId) {

		groupService.deleteGroupsById(groupId);
		return "deleted sucessfully";
	}

	@Operation(summary = "add a updateGroups to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updateGroups added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GroupsService.class)) }),
			@ApiResponse(responseCode = "400", description = "Groups not found with given details") })
	@PutMapping("/updateGroups/{groupId}")
	public MyGroups updateGroup(int groupId, MyGroups groups) {

		return groupService.updateGroups(groupId, groups);
	}

	@Operation(summary = "add  assignPermissionsToGroups to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "PermissionsToGroups added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GroupsService.class)) }),
			@ApiResponse(responseCode = "400", description = "PermissionsOrGroup not found with given details") })
	@PutMapping("/{groupId}/assignPermissionsToGroups")
	public MyGroups assignPermissionsToGroups(@PathVariable int groupId, @RequestParam List<Integer> permissions) {

		return groupService.assignPermissionsToGroups(groupId, permissions);
	}

}
