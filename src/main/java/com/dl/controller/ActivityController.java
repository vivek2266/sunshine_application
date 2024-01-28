package com.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.annotation.TrackExecutionTime;
import com.dl.entity.Activitys;
import com.dl.service.ActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ActivityController {
	@Autowired
	private ActivityService activityService;

	@Operation(summary = "add a new Activity to the system")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Activity added successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ActivityService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	 @TrackExecutionTime
	@PostMapping("/addActivitys")
	public Activitys addActivityss(@RequestBody Activitys activitys) {
		return activityService.addActivity(activitys);

	}

	@Operation(summary = "Fetch all Activity object")

	 @TrackExecutionTime
	@GetMapping("/getAllActivitys")
	public List<Activitys> getAllActivitys() {
		return activityService.getAllActivitys();
	}

	 @TrackExecutionTime
	@Operation(summary = "Delete activity by activityId")
	@DeleteMapping("/deleteActivity/{activityId}")
	public String deleteActivityById(int activityId) {

		activityService.deleteActivityById(activityId);
		return "deleted sucessfully ";
	}

	@Operation(summary = "added updateActivitys to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updateActivitys added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActivityService.class)) }),
			@ApiResponse(responseCode = "400", description = "Activitys not found with given details") })
	@PutMapping("/updateActivitys/{activityId}")

	 @TrackExecutionTime
	public Activitys updateActivity(int activityId, Activitys activitys) {

		return activityService.updateActivity(activityId, activitys);
	}
}
