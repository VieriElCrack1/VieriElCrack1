package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.CP;
import com.ads.project.repository.CPRepository;

@Service
public class CPService {
	@Autowired
	private CPRepository repo;
	
	public void guardar(CP c) {
		repo.save(c);
	}
	
	public void elimnar(int id) {
		repo.deleteById(id);
	}
	
	public List<CP> listado() {
		return repo.findAll();
	}
	
	public CP buscar(int cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public int obtenerCodigo() {
		return repo.obtenerCOD();
	}
}
