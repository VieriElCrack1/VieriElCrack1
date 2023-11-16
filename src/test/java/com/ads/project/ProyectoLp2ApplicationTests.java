package com.ads.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ads.project.entity.Rol;
import com.ads.project.entity.Usuario;
import com.ads.project.repository.RequerimientoRepository;
import com.ads.project.service.UsuarioService;
import com.ads.project.springconfig.UserService;

@SpringBootTest
class ProyectoLp2ApplicationTests {
	
	@Autowired
	private RequerimientoRepository repo;
	
	@Test
	void contextLoads() {
		
		System.out.println(repo.obtenerCodigo());
	}

}
