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

import com.ads.project.entity.AMC;
import com.ads.project.entity.CCP;
import com.ads.project.service.AMCService;
import com.ads.project.service.CCPService;

@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class AMCController {
	
	@Autowired
	private AMCService serAMC;
	
	@Autowired
	private CCPService serCCP;
	
	@GetMapping("/amc/")
	public String amc(Model model) {
		List<AMC> lista = serAMC.listado();
		model.addAttribute("amc", lista);
		return "amc";
	}
	
	@GetMapping("/registrarAMC/")
	public String registrarAMC(Model model) {
		int cod = serAMC.obteneCodigo();
		model.addAttribute("codigo", cod);
		List<CCP> lista1 = serCCP.listado();
		model.addAttribute("ccp", lista1);
		return "registrarAMC";
	}
	
	@PostMapping("/registrarAMC/")
	public String registrarAMC(@RequestParam(name = "idamc", required = false) int cod,
			@RequestParam(name = "descrip", required = false) String descrip,
			@RequestParam(name = "monto", required = false) double monto,
			@RequestParam(name = "idccp", required = false) int idccp) {
		try {
			AMC a = new AMC();
			a.setIdamc(cod);
			a.setDescrip(descrip);
			a.setMonto(monto);
			a.setIdccp(idccp);
			serAMC.guardar(a);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/amc/";
	}
	
	@GetMapping("/actualizarAMC/{idamc}")
	public String actualizarAMC(@PathVariable Integer idamc, Model model) {
		AMC a = serAMC.buscar(idamc);
		model.addAttribute("idamc", a);
		List<CCP> lista1 = serCCP.listado();
		model.addAttribute("ccp", lista1);
		return "actualizarAMC";
	}
	
	@PostMapping("/actualizarAMC/{idamc}")
	public String actualizarAMC(@PathVariable Integer idamc, @ModelAttribute("amc") AMC amc) {
		try {
			AMC a = serAMC.buscar(idamc);
			a.setIdamc(amc.getIdamc());
			a.setDescrip(amc.getDescrip());
			a.setMonto(amc.getMonto());
			a.setIdccp(amc.getIdccp());
			serAMC.guardar(a);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return "redirect:/amc/";
	}
	
	@GetMapping("/amc/eliminar/{idamc}")
	public String eliminarAMC(@PathVariable Integer idamc) {
		try {
			serAMC.eliminar(idamc);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/amc/";
	}
	
}
