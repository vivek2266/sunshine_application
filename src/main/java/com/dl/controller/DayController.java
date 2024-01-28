package com.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.entity.Day;
import com.dl.service.AttributesService;
import com.dl.service.DayService;
import com.dl.service.GroupsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DayController {

	@Autowired
	private DayService dayService;

	@Operation(summary = "add a new day to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "day added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = DayService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addDay")
	public Day addDay(@RequestBody Day day) {
		return dayService.getActivitys(day);
	}

	@Operation(summary = "Fetch all days object")

	@GetMapping("/getAllDays")
	public List<Day> getAllDays() {
		return dayService.getAllActivitys();
	}

	@Operation(summary = "Delete day by dayNo")
	@DeleteMapping("/deleteDay/{dayNo}")
	public String deleteDayById(@PathVariable int dayNo) {
		dayService.deleteDayById(dayNo);
		return "deleted successfully";
	}

	@Operation(summary = "add a updateDay to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updateDay added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = DayService.class)) }),
			@ApiResponse(responseCode = "400", description = "day not found with given details") })
	@PutMapping("/updateDay/{dayNo}")
	public Day updateDay(@PathVariable int dayNo, @RequestBody Day day) {
		return dayService.updateGroups(dayNo, day);
	}
}
