package com.ads.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_expediente")
public class Expediente {
	@Id
	private Integer idexpediente;
	private String nomexpediente;
	private int cantexpediente;
	private int idrequerimiento;
	private int idtipo;
	private int idestado;
	
	@ManyToOne
	@JoinColumn(name = "idestado", insertable = false, updatable = false)
	private Estado est;
	
	@ManyToOne
	@JoinColumn(name = "idrequerimiento", insertable = false, updatable = false)
	private Requerimiento req;
	
	@ManyToOne
	@JoinColumn(name = "idtipo", insertable = false, updatable = false)
	private Tipo tipo;
}
