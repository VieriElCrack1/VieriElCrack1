package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.LP;
import com.ads.project.repository.LPRepository;

@Service
public class LPService {
	
	@Autowired
	private LPRepository repo;
	
	public void guardar(LP l) {
		repo.save(l);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<LP> listado(){
		return repo.findAll();
	}
	
	public LP buscar(int id) {
		return repo.findById(id).orElse(null);
	}
	
	//llamamos al metodo de LPRepository
	public int obtenerCodigo() {
		return repo.obtenerCOD();
	}
	
}
