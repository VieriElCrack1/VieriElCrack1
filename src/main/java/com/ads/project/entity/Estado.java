package com.ads.project.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "tb_estado")
@Data
public class Estado {
	@Id
	private Integer idestado;
	private String descrip;
	
	@OneToMany(mappedBy = "est")
	private List<Expediente> listExp;
	
	@OneToMany(mappedBy = "est")
	private List<CCP> listCCP;
}
