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

import com.ads.project.entity.CCP;
import com.ads.project.entity.LP;
import com.ads.project.service.CCPService;
import com.ads.project.service.LPService;

@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class LPController {
	@Autowired
	private LPService serLP;
	
	@Autowired
	private CCPService serCCP;
	
	@GetMapping("/lp/")
	public String lp(Model model) {
		List<LP> lista = serLP.listado();	
		model.addAttribute("lp", lista);
		return "lp";
	}
	
	@GetMapping("/registrarLP/")
	public String registrarLP(Model model) {
		int cod = serLP.obtenerCodigo();
		model.addAttribute("codigo", cod);
		List<CCP> lista1 = serCCP.listado();
		model.addAttribute("ccp", lista1);
		return "registrarLP";
	}
	
	@PostMapping("/registrarLP/")
	public String registrarLP(@RequestParam(name = "idlp",  required = false) int idlp,
			@RequestParam(name = "descrip", required = false) String descrip,
			@RequestParam(name = "monto", required = false) double monto, 
			@RequestParam(name = "idccp", required = false) int idccp) {
		try {
			LP l = new LP();
			l.setIdlp(idlp);
			l.setDescrip(descrip);
			l.setMonto(monto);
			l.setIdccp(idccp);
			serLP.guardar(l);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/lp/";
	}
	
	@GetMapping("/actualizarLP/{idlp}")
	public String actualizarLP(@PathVariable Integer idlp, Model model) {
		LP l = serLP.buscar(idlp);
		model.addAttribute("codigo", l);
		List<CCP> lista1 = serCCP.listado();
		model.addAttribute("ccp", lista1);
		return "actualizarLP";
	}
	
	@PostMapping("/actualizarLP/{idlp}")
	public String actualizarLP(@PathVariable Integer idlp, @ModelAttribute("lp") LP lp, Model model) {
		try {
			LP l = serLP.buscar(idlp);
			l.setDescrip(lp.getDescrip());
			l.setMonto(lp.getMonto());
			l.setIdccp(lp.getIdccp());
			serLP.guardar(l);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/lp/";
	}
	
	@GetMapping("/lp/eliminar/{idlp}")
	public String eliminarLP(@PathVariable Integer idlp) {
		try {
			serLP.eliminar(idlp);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/lp/";
	}
}
