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
import com.dl.entity.Attributes;
import com.dl.service.AttributesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class AttributeController {

	@Autowired
	private AttributesService attributesService;

	 @TrackExecutionTime
	@Operation(summary = "add a new Attribute to the system")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Attribute added successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = AttributesService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addAttributes")
	public Attributes addAttributes(@RequestBody Attributes attributes) {
		return attributesService.addAttributes(attributes);

	}

	 @TrackExecutionTime
	@Operation(summary = "Fetch all attributes object")
	@GetMapping("/getAllAttributes")
	public List<Attributes> getAllAttributes() {
		return attributesService.getAllAttributes();
	}

	 @TrackExecutionTime
	@Operation(summary = "Delete attribute by attributeId")
	@DeleteMapping("/deleteAttributes/{attributeId}")
	public String deleteAttributesById(int attributeId) {

		attributesService.deleteAttributesById(attributeId);
		return "deleted sucessfully";
	}

	 @TrackExecutionTime
	@Operation(summary = "add a updateAttributes to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "updateAttributes added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AttributesService.class)) }),
			@ApiResponse(responseCode = "400", description = "Attributes not found with given details") })
	@PutMapping("/updateAttributes/{attributeId}")

	public Attributes updateAttributes(int attributeId, Attributes attributes) {

		return attributesService.updateAttributes(attributeId, attributes);
	}

}
