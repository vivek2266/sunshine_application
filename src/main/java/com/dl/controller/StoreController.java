package com.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.annotation.TrackExecutionTime;
import com.dl.entity.Stores;
import com.dl.service.StoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class StoreController {

	@Autowired
	private StoreService storeService;

	 @TrackExecutionTime
	@Operation(summary = "add a new Store to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "store added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StoreService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addNewStore")
	public Stores saveNewStore(@RequestBody Stores store) {

		return storeService.saveNewStore(store);

	}

	 @TrackExecutionTime
	@Operation(summary = "Fetch all Stores object")

	@GetMapping("/getAllStore")
	public List<Stores> getAllStores() {
		return storeService.getAllStores();
	}

	 @TrackExecutionTime
	@Operation(summary = "updateStore into the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "updateStore found successfully ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StoreService.class)) }),
			@ApiResponse(responseCode = "400", description = "store not found with given details") })
	@PutMapping("/updateStore/{storeId}")
	public Stores updateStoresStatus(@PathVariable int storeId, @RequestBody Stores stores) {
		return storeService.updateStores(storeId, stores);
	}
}
