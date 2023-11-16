package com.ads.project.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "tb_tipos")
@Data
public class Tipo {
	@Id
	private Integer idtipo;
	private String descrip;
	
	@OneToMany(mappedBy = "tipo")
	private List<Expediente> lisExp;
}
