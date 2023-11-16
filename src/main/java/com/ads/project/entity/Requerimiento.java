package com.ads.project.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_requerimiento")
public class Requerimiento {
	@Id
	private Integer idrequerimiento;
	private String nomrequerimiento;
	private String descripcion;
	private int idusuario;
	
	@ManyToOne
	@JoinColumn(name = "idusuario", insertable = false, updatable = false)
	private Usuario usu;
	
	@OneToMany(mappedBy = "req")
	private List<Expediente> listExp;
	
	@OneToMany(mappedBy = "reque")
	private List<InformeRequisicion> listInfo;
	
	@OneToMany(mappedBy = "requeri")
	private List<CCP> listCCP;
}
