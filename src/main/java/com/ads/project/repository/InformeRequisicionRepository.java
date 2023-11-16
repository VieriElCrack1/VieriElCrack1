package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.project.entity.InformeRequisicion;

public interface InformeRequisicionRepository extends JpaRepository<InformeRequisicion, Integer>{
	@Query("Select coalesce(max(i.idinforequisicion), 0) +1 from InformeRequisicion i")
	int obtenerCodigo();
}
