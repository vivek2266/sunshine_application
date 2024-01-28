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

import com.dl.annotation.TrackExecutionTime;
import com.dl.entity.StoreAttributeDetails;
import com.dl.service.StoreAttributeDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class StoreAttributeDetailsController {

	@Autowired
	private StoreAttributeDetailsService storeAttributeDetailsService;

	 @TrackExecutionTime
	@Operation(summary = "add a new StoreAttributeDetails to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "StoreAttributeDetails added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StoreAttributeDetailsService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addStoreAttributeDetails")
	public StoreAttributeDetails addStoreAttributeDetails(@RequestBody StoreAttributeDetails attributeDetails) {
		return storeAttributeDetailsService.addStoreAttributeDetails(attributeDetails);
	}

	 @TrackExecutionTime
	@Operation(summary = "Fetch all StoreAttributeDetails object")

	@GetMapping("/getAllStoreAttributeDetails")
	public List<StoreAttributeDetails> getAllStoreAttributeDetails() {
		return storeAttributeDetailsService.getAllActivitys();
	}

	 @TrackExecutionTime
    @Operation(summary = "Delete StoreAttributeDetails by StoreAttributeDetailsId")
	@DeleteMapping("/deleteStoreAttributeDetails/{storeAttributeId}")
	public String deleteStoreAttributeDetailsById(@PathVariable int storeAttributeId) {
		storeAttributeDetailsService.deleteGroupsById(storeAttributeId);
		return "deleted successfully";
	}

	 @TrackExecutionTime
	@Operation(summary = "updateStoreAttributeDetails into the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "StoreAttributeDetails added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StoreAttributeDetailsService.class)) }),
			@ApiResponse(responseCode = "400", description = "StoreAttributeDetails not found with given details") })
	@PutMapping("/updateStoreAttributeDetails/{storeAttributeId}")
	public StoreAttributeDetails updateStoreAttributeDetails(@PathVariable int storeAttributeId,
			@RequestBody StoreAttributeDetails storeAttributeDetails) {
		return storeAttributeDetailsService.updateGroups(storeAttributeId, storeAttributeDetails);
	}
}
