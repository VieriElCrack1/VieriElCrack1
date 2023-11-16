package com.ads.project.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_enlace_menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnlaceMenu implements Serializable{
	@Id
	private Integer idenlace;
	private String descripcion;
	private String ruta;
	
	@JsonIgnore
	@OneToMany(mappedBy = "enlace")
	private List<RolEnlace> listarolenlace;
	
}
