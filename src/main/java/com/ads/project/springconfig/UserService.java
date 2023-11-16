package com.ads.project.springconfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ads.project.entity.Usuario;
import com.ads.project.repository.UsuarioRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario guardarUsuario(Usuario u) {
		u.setPass(passwordEncoder.encode(u.getPass()));
		return repo.save(u);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails u = null;
		try {
			Usuario bean = repo.iniciarSesion(username);
			Set<GrantedAuthority> rol = new HashSet<GrantedAuthority>();
			rol.add(new SimpleGrantedAuthority(bean.getRol().getDescripcion()));
			u = new User(username, bean.getPass(), rol);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getMessage());
		}
		return u;
	}
	
	
}
