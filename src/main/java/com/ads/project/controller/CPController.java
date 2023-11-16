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
import com.ads.project.entity.CP;
import com.ads.project.service.CCPService;
import com.ads.project.service.CPService;

@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class CPController {
	
	@Autowired
	private CPService serCP;
	
	@Autowired
	private CCPService serCCP;
	
	@GetMapping("/cp/")
	public String cp(Model model) {
		List<CP> lista = serCP.listado();
		model.addAttribute("cp", lista);
		return "cp";
	}
	
	@GetMapping("/registrarCP/")
	public String registrarCP(Model model) {
		int cod = serCP.obtenerCodigo();
		model.addAttribute("codigo", cod);
		List<CCP> lista = serCCP.listado();
		model.addAttribute("ccp", lista);
		return "registrarCP";
	}
	
	@PostMapping("/registrarCP/")
	public String registrarCP(@RequestParam("idcp") int idcp,
			@RequestParam("descrip") String descrip,
			@RequestParam("monto") double monto, 
			@RequestParam("idccp") int idccp) {
		try {
			CP c = new CP();
			c.setIdcp(idcp);
			c.setDescrip(descrip);
			c.setMonto(monto);
			c.setIdccp(idccp);
			serCP.guardar(c);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/cp/";
	}
	
	@GetMapping("/actualizarCP/{idcp}")
	public String actualizarCP(@PathVariable Integer idcp, Model model) {
		CP c = serCP.buscar(idcp);
		model.addAttribute("codigo", c);
		List<CCP> lista = serCCP.listado();
		model.addAttribute("ccp", lista);
		return "actualizarCP";
	}
	
	@PostMapping("/actualizarCP/{idcp}")
	public String actualizarCP(@PathVariable Integer idcp,@ModelAttribute("cp") CP cp) {
		try {
			CP c = serCP.buscar(idcp);
			c.setDescrip(cp.getDescrip());
			c.setMonto(cp.getMonto());
			c.setIdccp(cp.getIdccp());
			serCP.guardar(c);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/cp/";
	}
	
	@GetMapping("/cp/eliminar/{idcp}")
	public String eliminarCP(@PathVariable Integer idcp) {
		serCP.elimnar(idcp);
		return "redirect:/cp/";
	}
}
