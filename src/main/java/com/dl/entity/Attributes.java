package com.dl.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attributes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attributeId;
	private String attributeName;
	private boolean isActive = true;

//	@OneToMany(mappedBy = "attributeId", cascade = CascadeType.ALL)
//    @JsonBackReference(value = "attribute-details") // Add this line
//    private List<StoreAttributeDetails> storeAttributeDetailsList;
	public Attributes(String attributeId) {
		this.attributeId = Integer.parseInt(attributeId);
	}
}
