package com.ads.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.project.entity.Tipo;
 
public interface TipoRepository  extends JpaRepository<Tipo, Integer >{ 
	
}
