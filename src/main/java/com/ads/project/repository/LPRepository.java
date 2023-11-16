package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.LP;

public interface LPRepository extends JpaRepository<LP, Integer>{
	@Query("Select coalesce(MAX(l.idlp), 0) + 1 From LP l")
	int obtenerCOD();
}
