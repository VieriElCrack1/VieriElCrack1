package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.Tipo;
import com.ads.project.repository.TipoRepository;

@Service
public class TipoService {
	@Autowired
	private TipoRepository TiRep;
	
	public void guardar(Tipo tip) {
		TiRep.save(tip); //registrar y actualizar
	}
	public void eliminar(int cod) {
		TiRep.deleteById(cod);//eliminar
	}
	public List<Tipo> Listado() {
		return TiRep.findAll();
	}
	public Tipo buscar(int idCod) {
		return TiRep.findById(idCod).orElse(null);
	}
}
