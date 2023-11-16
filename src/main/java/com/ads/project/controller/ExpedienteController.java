package com.ads.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ads.project.entity.Estado;
import com.ads.project.entity.Expediente;
import com.ads.project.entity.Requerimiento;
import com.ads.project.entity.Tipo;
import com.ads.project.service.EstadoServices;
import com.ads.project.service.ExpedienteService;
import com.ads.project.service.RequerimientoService;
import com.ads.project.service.TipoService;

@SessionAttributes({"ENLACES","USUARIO"})
@Controller
public class ExpedienteController {
	
	@Autowired
	private ExpedienteService expSer;
	
	@Autowired
	private RequerimientoService reqSer;
	
	@Autowired
	private TipoService tiSer;
	
	@Autowired
	private EstadoServices esSer;
	
	@GetMapping("/expediente/")
	public String expediente(Model mod) {
		int cod = expSer.obtenerId();
		mod.addAttribute("COD", cod);
		
		List<Requerimiento> lista = reqSer.listado(); 
		List<Tipo> lista1 = tiSer.Listado();
		List<Estado>lista2= esSer.listado();
		
		mod.addAttribute("reque", lista);
		mod.addAttribute("tip", lista1);
		mod.addAttribute("est", lista2);
		
		
		return "expediente";
	
	}
	@PostMapping("/expediente/")
	public String expediente(@RequestParam(name="idex",required = false) int idex,
			@RequestParam(name="nomex",required= false) String nomex,
			@RequestParam(name="cantex", required = false)int cantex,
			@RequestParam(name="cboReq",required = false) int cboReq, 
			@RequestParam(name="cboTi",required = false) int cboTi,
			@RequestParam(name="cboEst",required = false)int cboEst){
		try {
			Expediente e = new Expediente();
			e.setIdexpediente(idex);
			e.setNomexpediente(nomex);
			e.setCantexpediente(cantex);
			e.setIdrequerimiento(cboReq);
			e.setIdtipo(cboTi);
			e.setIdestado(cboEst);
			expSer.guardar(e);
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "redirect:/principal";
	}
	
}
