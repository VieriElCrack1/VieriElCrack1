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
@Table(name = "tb_ccp")
public class CCP {
	@Id
	private Integer idccp;
	private String fech_doc;
	private String justificacion;
	private String descrip;
	private String fech_aprob;
	private double monto;
	private double total;
	private int idrequerimiento;
	private int idestado;
	
	@ManyToOne
	@JoinColumn(name = "idrequerimiento", insertable = false, updatable = false)
	private Requerimiento requeri;
	
	@ManyToOne
	@JoinColumn(name = "idestado", insertable = false, updatable = false)
	private Estado est;
	
	@OneToMany(mappedBy = "cert")
	private List<ADP> listADP;
	
	@OneToMany(mappedBy = "certi")
	private List<AMC> listAMC;
	
	@OneToMany(mappedBy = "certif")
	private List<CP> listCP;
	
	@OneToMany(mappedBy = "certifi")
	private List<LP> listLP;
}
