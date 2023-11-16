package com.ads.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_informe_requisicion")
public class InformeRequisicion {
	@Id
	private Integer idinforequisicion;
	private String nomrequisicion;
	private String descripcion;
	private int idrequerimiento;
	
	@ManyToOne
	@JoinColumn(name = "idrequerimiento", insertable = false, updatable = false)
	private Requerimiento reque;
}
