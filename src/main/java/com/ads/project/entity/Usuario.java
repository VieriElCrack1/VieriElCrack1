package com.ads.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
    private Integer idusuario;
	private String nombre;
	private String correo;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "contraseña")
	private String pass;
	@Column(name = "rep_contra")
	private String rep_pass;
	private String dni;
	private int idrol;
	
	@JoinColumn(name = "idrol", insertable = false, updatable = false)
	@ManyToOne
	private Rol rol;
	
	@OneToMany(mappedBy = "usu")
	private List<Requerimiento> listReq;
	
}
