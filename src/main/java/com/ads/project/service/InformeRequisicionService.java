package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.InformeRequisicion;
import com.ads.project.repository.InformeRequisicionRepository;

@Service
public class InformeRequisicionService {
	
	@Autowired
	private InformeRequisicionRepository repo;
		
	public void guardar(InformeRequisicion ir) {
		repo.save(ir);
	}
	public List<InformeRequisicion> listado(){
		return repo.findAll();
	}
	
	public int obtenerID() {
		return repo.obtenerCodigo();
	}
}
