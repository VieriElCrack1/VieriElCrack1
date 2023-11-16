package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.AMC;

public interface AMCRepository extends JpaRepository<AMC, Integer>{
	
	@Query("Select coalesce(MAX(a.idamc), 0) + 1 From AMC a")
	int obtenerCOD();
}
