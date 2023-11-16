package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.CCP;

public interface CCPRepository extends JpaRepository<CCP, Integer>{
	
	@Query("Select coalesce(MAX(c.idccp), 0) + 1 From CCP c")
	int obtenerCOD();
	
}
