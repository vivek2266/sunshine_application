package com.dl.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dl.DTo.VisitDto;
import com.dl.entity.StoreAttributeDetails;
import com.dl.entity.Stores;
import com.dl.entity.Visit;
import com.dl.repository.StoreAttributeDetailsRepossitory;
import com.dl.repository.VisitRepository;

@Service
public class VisitService {
	@Autowired
	private VisitRepository visitRepo;

	@Autowired
	private StoreAttributeDetailsRepossitory storeAttributeDetailsRepo;

	public Visit addAllVisits(Visit visit) {
		return visitRepo.save(visit);
	}

	public List<Visit> getAllVisita() {

		return visitRepo.findAll();
	}

	public String deleteVisitById(int visitId) {

		visitRepo.deleteById(visitId);
		return "deleted sucessfully";
	}

	public Visit updateVisit(int visitId, Visit visit) {

		Visit existingVisit = visitRepo.findById(visitId).get();
		existingVisit.setComment(visit.getComment());

		return visitRepo.save(existingVisit);
	}

	public List<Visit> getAllVisitsForPocApproval() {
		List<String> allowedStatusList = Arrays.asList("salesmen approved", "RSD rejected", "QC rejected",
				"HO rejected");

		List<Visit> visits = visitRepo.findByStatusInAndIsProcessed(allowedStatusList, false);

		return visits;
	}

	public List<Visit> getAllVisitsForRSDApproval() {

		List<String> allowedStatusList = Arrays.asList("POC approved");

		List<Visit> visits = visitRepo.findByStatusInAndIsProcessed(allowedStatusList, false);

		return visits;

	}

	public List<Visit> getAllVisitsForQCApproval() {

		List<String> allowedStatusList = Arrays.asList("POC approved");

		List<Visit> visits = visitRepo.findByStatusInAndIsProcessed(allowedStatusList, false);

		return visits;
	}

	public List<Visit> getAllVisitsForHOApproval() {

		List<String> allowedStatusList = Arrays.asList("POC approved");

		List<Visit> visits = visitRepo.findByStatusInAndIsProcessed(allowedStatusList, false);

		return visits;
	}

	/**
	 * This method performs multiple actions related to Visit records and associated
	 * details. It handles updating and inserting records in the database.
	 * 
	 * @param visitId                   The ID of the original visit.
	 * @param status                    The status to be set for the new visit
	 *                                  record.
	 * @param storeId                   The store ID associated with the visit.
	 * @param Comment                   A comment associated with the visit records.
	 * @param storeAttributeDetailsList A list of StoreAttributeDetails to be
	 *                                  associated with the new visit.
	 */


	/**
	 * This method performs multiple actions related to Visit records and associated
	 * details. It handles updating and inserting records in the database.
	 * 
	 * @param visitId                   The ID of the original visit.
	 * @param status                    The status to be set for the new visit
	 *                                  record.
	 * @param storeId                   The store ID associated with the visit.
	 * @param Comment                   A comment associated with the visit records.
	 * @param storeAttributeDetailsList A list of StoreAttributeDetails to be
	 *                                  associated with the new visit.
	 */


	/**
	 * This method performs multiple actions related to Visit records and associated
	 * details. It handles updating and inserting records in the database.
	 * 
	 * @param visitId                   The ID of the original visit.
	 * @param status                    The status to be set for the new visit
	 *                                  record.
	 * @param storeId                   The store ID associated with the visit.
	 * @param Comment                   A comment associated with the visit records.
	 * @param storeAttributeDetailsList A list of StoreAttributeDetails to be
	 *                                  associated with the new visit.
	 */
	@Transactional
	public void updateExistingVisitAndCreateNewRecord(String originalVisitId, String newVisitStatus,
			Stores associatedStore, String visitComment, List<StoreAttributeDetails> updatedStoreAttributeDetails) {

		// Retrieve and update the original visit record, if applicable
		if (originalVisitId != null) {
			Visit visitToUpdate = visitRepo.findById(Integer.parseInt(originalVisitId))
					.orElseThrow(() -> new RuntimeException("Visit with ID " + originalVisitId + " not found"));

			visitToUpdate.setProcessed(true);
			visitRepo.save(visitToUpdate);
		}

		// Create a new visit record with the provided details
		Visit newVisit = new Visit();
		newVisit.setComment(visitComment);
		newVisit.setStatus(newVisitStatus);
		newVisit.setStoreId(associatedStore);

		// Create associated StoreAttributeDetails records for the new visit,
		// handling potential null input for updatedStoreAttributeDetails
		if (updatedStoreAttributeDetails != null) {
			for (StoreAttributeDetails attributeDetails : updatedStoreAttributeDetails) {
				StoreAttributeDetails newAttributeDetails = new StoreAttributeDetails();
				newAttributeDetails.setAttributeId(attributeDetails.getAttributeId());
				newAttributeDetails.setAttributeValue(attributeDetails.getAttributeValue());
				newAttributeDetails.setVisitId(newVisit);
				newAttributeDetails.setStoreId(associatedStore);
				newAttributeDetails.setActive(true);
				storeAttributeDetailsRepo.save(newAttributeDetails);
			}
		}

		// Commit changes to the database
		visitRepo.save(newVisit);
	}

	/**
	 * Retrieves simplified records of a particular store based on storeId, sorted
	 * in ascending order by visitId.
	 *
	 * @param storeId The identifier of the store for which visits are retrieved.
	 * @return A list of simplified Visit entities representing the records of the
	 *         specified store, sorted by visitId.
	 */
	public List<VisitDto> getAllVisitsForStore(int storeId) {
		List<Visit> visits = visitRepo.findByStoreIdStoreIdOrderByVisitId(storeId);
		if (visits.isEmpty()) {
			// Return an empty list or handle the case as needed
			return Collections.emptyList();
		}

		// Create a list to store simplified Visit entities
		List<VisitDto> simplifiedVisits = new ArrayList<>();

		for (Visit visit : visits) {
			// Create a simplified Visit entity with only necessary fields
			VisitDto simplifiedVisit = new VisitDto();
			simplifiedVisit.setVisitId(visit.getVisitId());
			simplifiedVisit.setComment(visit.getComment());
			simplifiedVisit.setStatus(visit.getStatus());
//	        simplifiedVisit.setProcessed(visit.isProcessed());

			// Add the simplified Visit entity to the list
			simplifiedVisits.add(simplifiedVisit);
		}

		return simplifiedVisits;
	}

}