package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.AMC;
import com.ads.project.repository.AMCRepository;

@Service
public class AMCService {
	
	@Autowired
	private AMCRepository repo;
	
	public void guardar(AMC a) {
		repo.save(a);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<AMC> listado(){
		return repo.findAll();
	}
	
	public AMC buscar(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public int obteneCodigo() {
		return repo.obtenerCOD();
	}
}
