package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.ADP;
import com.ads.project.repository.ADPRepository;

@Service
public class ADPService {
	
	@Autowired
	private ADPRepository repo;
	
	public void guardar(ADP a) {
		repo.save(a);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<ADP> listado(){
		return repo.findAll();
	}
	
	public ADP buscar(int cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public int obtenerCodigo() {
		return repo.obtenerCOD();
	}
}
