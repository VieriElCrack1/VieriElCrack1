package com.ads.project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ads.project.entity.InformeRequisicion;
import com.ads.project.entity.Requerimiento;
import com.ads.project.service.InformeRequisicionService;
import com.ads.project.service.RequerimientoService;
import com.ads.project.utils.InformeRequisicionPDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;


@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class InformeRequisicionController {
	
	@Autowired
	private InformeRequisicionService infoser;
	
	@Autowired
	private RequerimientoService reqser;

	@GetMapping("/informeRequisicion/")
	public String registrar(Model model) {
		int cod = infoser.obtenerID();
		model.addAttribute("cod", cod);
		List<Requerimiento> req = reqser.listado();
		model.addAttribute("REQ", req);
		return "registrarInformeRequerimiento";
	}

	@PostMapping("/informeRequisicion/")
	public String registrar(@ModelAttribute InformeRequisicion ir) {
		infoser.guardar(ir);
		return "redirect:/informeRequisicion/";
	}
	
	//PDF - Requerimiento
	@GetMapping("/informeRequisicion/pdf")
	public void exportarListadoRequerimiento(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String fechaActual = format.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "inline;filename=requerimiento_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<InformeRequisicion> empleado = infoser.listado();
		
		InformeRequisicionPDF exporter = new InformeRequisicionPDF(empleado);
		
		exporter.exportar(response);
	}
}
