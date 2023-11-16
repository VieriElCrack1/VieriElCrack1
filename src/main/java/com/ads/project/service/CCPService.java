package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.CCP;
import com.ads.project.repository.CCPRepository;

@Service
public class CCPService {
	
	@Autowired
	private CCPRepository repo;
	
	public void guardar(CCP c) {
		repo.save(c);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<CCP> listado(){
		return repo.findAll();
	}
	
	public CCP buscar(int id) {
		return repo.findById(id).orElse(null);
	}
	
	//obtener el id - CCP
	public int obtenerID() {
		return repo.obtenerCOD();
	}
	
}
