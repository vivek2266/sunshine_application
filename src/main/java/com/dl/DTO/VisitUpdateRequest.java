package com.dl.DTo;

import java.util.List;

import com.dl.entity.StoreAttributeDetails;
import com.dl.entity.Stores;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitUpdateRequest {

	private String visitId; // Use int instead of String if the ID is numeric
	private String status;
	private boolean isProcessed = false; 
	
	private Stores storeId;

	private String comment;


	private List<StoreAttributeDetails> storeAttributeDetailsList;
	
	
}