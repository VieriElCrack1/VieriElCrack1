package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.CP;

public interface CPRepository extends JpaRepository<CP, Integer>{
	@Query("Select coalesce(MAX(c.idcp), 0) + 1 From CP c")
	int obtenerCOD();
}
