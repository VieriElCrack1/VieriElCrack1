package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.Requerimiento;

public interface RequerimientoRepository extends JpaRepository<Requerimiento, Integer>{
	
	@Query("select coalesce(MAX(r.idrequerimiento), 0) + 1 From Requerimiento r")
	int obtenerCodigo();
	
}
