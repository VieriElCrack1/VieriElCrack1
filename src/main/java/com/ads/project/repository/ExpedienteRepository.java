package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Integer>{
	@Query("select coalesce(MAX(r.idexpediente), 0) + 1 From Expediente r")  
	int obtenerCod();
	
}
