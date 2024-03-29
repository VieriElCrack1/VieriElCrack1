package com.ads.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ads.project.entity.EnlaceMenu;
import com.ads.project.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Query("Select coalesce(MAX(u.idusuario), 0) + 1 From Usuario u")
	int obtenerId();
	
	@Query("Select u from Usuario u where u.correo=?1")
	public Usuario iniciarSesion(String vLogin);
	
	@Query("Select e from RolEnlace re join re.enlace e where re.rol.idrol=?1")
	public List<EnlaceMenu> traerEnlaceUsuario(int codRol);
	
}
