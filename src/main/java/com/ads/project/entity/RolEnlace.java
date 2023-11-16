package com.ads.project.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_rol_enlace")
public class RolEnlace {
	@EmbeddedId
	private RolEnlacePK pk;
	
	@ManyToOne
	@JoinColumn(name = "idrol", insertable = false, updatable = false)
	private Rol rol;
	
	@ManyToOne
	@JoinColumn(name = "idenlace", insertable = false, updatable = false)
	private EnlaceMenu enlace;
	
}
