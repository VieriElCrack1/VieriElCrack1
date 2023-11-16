package com.ads.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_roles")
public class Rol {
	@Id
	private Integer idrol;
	private String descripcion;
	
	@JsonIgnore
	@OneToMany(mappedBy = "rol")
	private List<Usuario> listaU;
	
	@JsonIgnore
	@OneToMany(mappedBy = "rol")
	private List<RolEnlace> listarolEnlace;

}
