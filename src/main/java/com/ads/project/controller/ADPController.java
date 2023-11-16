package com.ads.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ads.project.entity.ADP;
import com.ads.project.entity.CCP;
import com.ads.project.service.ADPService;
import com.ads.project.service.CCPService;


@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class ADPController {
	
	@Autowired
	private ADPService serADP;
	
	@Autowired
	private CCPService serCCP;
	
	@GetMapping("/adp/")
	public String adp(Model model) {
		List<ADP> lista = serADP.listado();
		model.addAttribute("list", lista);
		return "adp";
	}
	
	@GetMapping("/registrarADP/")
	public String registrarADP(Model model) {
		int cod = serADP.obtenerCodigo();
		model.addAttribute("codigo", cod);
		List<CCP> listado = serCCP.listado();
		model.addAttribute("ccp", listado);
		return "registrarADP";
	}
	
	@PostMapping("/registrarADP/")
	public String registrarADP(@RequestParam(name = "idadp", required = false) int cod,
			@RequestParam(name = "descrip", required = false) String descrip,
			@RequestParam(name = "monto", required = false) double monto,
			@RequestParam(name = "idccp", required = false) int idccp) {
		try {
			ADP a = new ADP();
			a.setIdadp(cod);
			a.setDescrip(descrip);
			a.setMonto(monto);
			a.setIdccp(idccp);
			serADP.guardar(a);
		} catch (Exception e) {
			System.out.println("Erro en : " + e.getLocalizedMessage());
		}
		return "redirect:/adp/";
	}
	
	@GetMapping("/actualizarADP/{idadp}")
	public String actualizarADP(@PathVariable Integer idadp,Model model) {
		ADP a = serADP.buscar(idadp);
		model.addAttribute("codigo", a);
		List<CCP> listado = serCCP.listado();
		model.addAttribute("ccp", listado);
		return "actualizarADP";
	}
	
	@PostMapping("/actualizarADP/{idadp}")
	public String actualizarADP(@PathVariable Integer idadp, @ModelAttribute("adp") ADP adp) {
		try {
			ADP a = serADP.buscar(idadp);
			a.setIdadp(adp.getIdadp());
			a.setDescrip(adp.getDescrip());
			a.setMonto(adp.getMonto());
			a.setIdccp(adp.getIdccp());
			serADP.guardar(a);
		} catch (Exception e) {
			System.out.println("Error : " + e.getLocalizedMessage());
		}
		return "redirect:/adp/";
	}
	
	@GetMapping("/adp/eliminar/{idadp}")
	public String eliminarADP(@PathVariable Integer idadp) {
		serADP.eliminar(idadp);
		return "redirect:/adp/";
	}
	
}
