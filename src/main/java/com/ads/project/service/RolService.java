package com.ads.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.project.entity.Rol;
import com.ads.project.repository.RolRepository;

@Service
public class RolService {
	@Autowired
	private RolRepository repo;
	
	public List<Rol> listado(){
		return repo.findAll();
	}
}
