package com.ads.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ads.project.entity.Requerimiento;
import com.ads.project.repository.RequerimientoRepository;

@Service
public class RequerimientoService {
	@Autowired
	private RequerimientoRepository repo;

	// sirve para registrar y actualizar - merge JPA
	public void guardar(Requerimiento r) {
		repo.save(r);
	}
	
	public void eliminar(int id) {
		repo.deleteById(id);
	}

	public List<Requerimiento> listado() {
		return repo.findAll();
	}

	public Requerimiento buscar(int cod) {
		return repo.findById(cod).orElse(null);
	}

	public Requerimiento buscarPorId(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public int obtenerCodigo() {
		return repo.obtenerCodigo();
	}
	
	
}
