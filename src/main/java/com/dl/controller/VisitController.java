package com.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.DTo.VisitUpdateRequest;
import com.dl.annotation.TrackExecutionTime;
import com.dl.entity.Visit;
import com.dl.service.UserService;
import com.dl.service.VisitService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping
public class VisitController {

	@Autowired
	private VisitService visitService;

	@TrackExecutionTime
	@Operation(summary = "add a new user to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "user added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VisitService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addVisit")
	public Visit addVisit(@RequestBody Visit visit) {
		return visitService.addAllVisits(visit);
	}

	@TrackExecutionTime
	@Operation(summary = "Fetch all visit object")
	@GetMapping("/getAllVisits")
	public List<Visit> getAllVisits() {
		return visitService.getAllVisita();
	}

	@TrackExecutionTime
	@Operation(summary = "Fetch  AllVisitsForPocApproval object")
	@GetMapping("/getAllVisitaForPocAproval")
	public List<Visit> getAllVisitsForPocApproval() {
		return visitService.getAllVisitsForPocApproval();
	}

	@TrackExecutionTime
	@Operation(summary = "Delete deleteVisit by visitId")
	@DeleteMapping("/deleteVisit/{visitId}")
	public String deleteVisitById(@PathVariable int visitId) {
		visitService.deleteVisitById(visitId);
		return "deleted successfully";
	}

	@Operation(summary = "add updateRecordsAccordingToStatus record into the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updateRecordsAccordingToStatus record added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VisitService.class)) }),
			@ApiResponse(responseCode = "400", description = "updateRecordsAccordingToStatus is  not added") })
	@TrackExecutionTime
	@PostMapping("/updateRecordsAccordingToStatus")
	public ResponseEntity<String> updateRecordsAccordingToStatus(@RequestBody VisitUpdateRequest visitUpdateRequest) {

		try {
			visitService.updateExistingVisitAndCreateNewRecord(visitUpdateRequest.getVisitId(),
					visitUpdateRequest.getStatus(), visitUpdateRequest.getStoreId(), visitUpdateRequest.getComment(),
					visitUpdateRequest.getStoreAttributeDetailsList());
			return new ResponseEntity<>("Records updated successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error updating records: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@TrackExecutionTime
	@Operation(summary = "Fetch all VisitsForStore object")
	@GetMapping("/getVisitsForRequiredStoreId/{storeId}")
	public Object getVisitsForStore(@PathVariable int storeId) {
		return visitService.getAllVisitsForStore(storeId);
	}
}