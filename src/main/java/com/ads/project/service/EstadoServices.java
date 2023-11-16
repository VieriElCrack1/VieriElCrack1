package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.Estado;
import com.ads.project.repository.EstadoRepository;

@Service 
public class EstadoServices {
	@Autowired
	private EstadoRepository estRep;
	
	public void guardar(Estado es) {
		estRep.save(es);
	}
	public void eliminar(int id) {
		estRep.deleteById(id);
	}
	public List<Estado> listado(){
		return estRep.findAll();
	}
	
	public Estado buscar(int cod) {
		return estRep.findById(cod).orElse(null);
	}
}
