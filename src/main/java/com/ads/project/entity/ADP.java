package com.ads.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_adp")
public class ADP {
	@Id
	private Integer idadp;
	private String descrip;
	private double monto;
	private int idccp;
	
	@ManyToOne
	@JoinColumn(name = "idccp", insertable = false, updatable = false)
	private CCP cert;
}
