package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ads.project.entity.EnlaceMenu;
import com.ads.project.entity.Usuario;
import com.ads.project.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public void guadar(Usuario u) {
		repo.save(u);
	}
	
	public Usuario loginUsuario(String vLogin) {
		return repo.iniciarSesion(vLogin);
	}
	
	public List<EnlaceMenu> enlaceUsuario(int rol){
		return repo.traerEnlaceUsuario(rol);
	}
	
	public void eliminar(int id) {
		repo.deleteById(id);
	}
	
	public Usuario buscar(int cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Usuario> listado(){
		return repo.findAll();
	}
	
	public int obtenerCodigo() {
		return repo.obtenerId();
	}
}
