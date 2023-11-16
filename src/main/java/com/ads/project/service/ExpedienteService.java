package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.Expediente;
import com.ads.project.repository.ExpedienteRepository;

@Service
public class ExpedienteService {
	@Autowired
	private ExpedienteRepository repo;
	
	public void guardar(Expediente e) {
		repo.save(e);
	}
	
	public void eliminar(int id) {
		repo.deleteById(id);
	}
	
	public List<Expediente> listado(){
		return repo.findAll();
	}
	
	public Expediente buscar(int cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public int obtenerId() {
		return repo.obtenerCod();
	}
}
