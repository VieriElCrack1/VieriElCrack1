package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.ADP;

public interface ADPRepository extends JpaRepository<ADP, Integer>{
	@Query("Select coalesce(MAX(a.idadp), 0) + 1 From ADP a")
	int obtenerCOD();
}
