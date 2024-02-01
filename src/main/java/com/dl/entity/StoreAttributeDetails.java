package com.dl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoreAttributeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeAttributeId;

    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "attributeId")
    //@JsonManagedReference(value = "attribute-details")//
    private Attributes attributeId;

    private String attributeValue;

    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "visitId")
    //@JsonManagedReference(value = "visit-Attribute")
    private Visit visitId;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "storeId")
    @JsonBackReference(value = "store-Attribute-details")
    private Stores storeId;

    private boolean isActive = true;
}