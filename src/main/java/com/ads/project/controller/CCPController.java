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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ads.project.entity.CCP;
import com.ads.project.entity.Estado;
import com.ads.project.entity.Requerimiento;
import com.ads.project.service.CCPService;
import com.ads.project.service.EstadoServices;
import com.ads.project.service.RequerimientoService;

@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class CCPController {
	
	@Autowired
	private CCPService ser;
	
	@Autowired
	private RequerimientoService serreq;
	
	@Autowired
	private EstadoServices serest;
	
	@GetMapping("/ccp/")
	public String ccp(Model model) {
		List<CCP> lista = ser.listado();
		model.addAttribute("CCP", lista);
		return "ccp";
	}
	
	@GetMapping("/registrarCCP/")
	public String registrarCCP(Model model) {
		int cod = ser.obtenerID();
		List<Requerimiento> lista = serreq.listado();
		List<Estado> lista2 = serest.listado();
		model.addAttribute("LIST", lista);
		model.addAttribute("est", lista2);
		model.addAttribute("codigo", cod);		
		return "registrarCCP";
	}
	
	@PostMapping("/registrarCCP/")
	public String registrarCCP(@RequestParam(name = "idccp", required = false) int cod, 
			@RequestParam(name = "fechaDoc", required = false) String fechaDoc,
			@RequestParam(name = "just", required = false) String just,
			@RequestParam(name = "descrip", required = false) String des,
			@RequestParam(name = "fechaApro", required = false) String fechaApro,
			@RequestParam(name = "monto", required = false) double monto,
			@RequestParam(name = "total", required = false) double total,
			@RequestParam(name = "cboReq", required = false) int idreq,
			@RequestParam(name = "cboEst", required = false) int idest, RedirectAttributes redirect) {
		try {
			CCP c = new CCP();
			if(c !=  null) {
				c.setIdccp(cod);
				c.setFech_doc(fechaDoc);
				c.setJustificacion(just);
				c.setDescrip(des);
				c.setFech_aprob(fechaApro);
				c.setMonto(monto);
				c.setTotal(total);
				c.setIdrequerimiento(idreq);
				c.setIdestado(idest);
				ser.guardar(c);
			}
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/ccp/";
	}
	
	@GetMapping("/actualizarCCP/{idccp}")
	public String actualizarCCP(@PathVariable Integer idccp, Model model) {
		CCP c = ser.buscar(idccp);
		List<Requerimiento> lista = serreq.listado();
		List<Estado> lista2 = serest.listado();
		model.addAttribute("LIST", lista);
		model.addAttribute("est", lista2);
		model.addAttribute("codigo", c);
		return "actualizarCCP";
	}
	
	@PostMapping("/actualizarCCP/{idccp}")
	public String actualizarCCP(@PathVariable Integer idccp, @ModelAttribute("cp") CCP ccp, Model model) {
		try {
			CCP c = ser.buscar(idccp);
			c.setIdccp(idccp);
			c.setFech_doc(ccp.getFech_doc());
			c.setJustificacion(ccp.getJustificacion());
			c.setDescrip(ccp.getDescrip());
			c.setFech_aprob(ccp.getFech_aprob());
			c.setMonto(ccp.getMonto());
			c.setTotal(ccp.getTotal());
			c.setIdrequerimiento(ccp.getIdrequerimiento());
			c.setIdestado(ccp.getIdestado());
			ser.guardar(c);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/ccp/";
	}
	
	@GetMapping("/ccp/eliminar/{idccp}")
	public String eliminarCCP(@PathVariable Integer idccp, Model model) {
		ser.eliminar(idccp);
		return "redirect:/ccp/";
	}
}
