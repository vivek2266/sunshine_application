package com.dl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dl.entity.Stores;

public interface StoreRepository extends JpaRepository<Stores, Integer> {

	
}
