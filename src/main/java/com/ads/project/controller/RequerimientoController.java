package com.ads.project.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ads.project.entity.Requerimiento;
import com.ads.project.entity.Usuario;
import com.ads.project.service.RequerimientoService;
import com.ads.project.service.UsuarioService;
import com.ads.project.utils.Libreria;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class RequerimientoController {

	@Autowired
	private UsuarioService serUsu;
	
	@Autowired
	private RequerimientoService serreq;
	
	@GetMapping("/requerimiento/")
    public String requerimiento(Model model) {
		int cod = serreq.obtenerCodigo();
       model.addAttribute("CODIGO", cod);
       
       List<Usuario> lista = serUsu.listado();
       model.addAttribute("usua", lista);
       
       return "requerimiento"; 
    }
	
	@PostMapping("/requerimiento/")
	public String requerimiento(@RequestParam(name = "idreq", required = false) int idreq,
			@RequestParam(name = "nom", required = false) String nom,
			@RequestParam(name = "descrip", required = false) String descrip, 
			@RequestParam(name = "cboUsuario", required = false) int idusu, Model model) {
		try {
			Requerimiento r = new Requerimiento();
			r.setIdrequerimiento(idreq);
			r.setNomrequerimiento(nom);
			r.setDescripcion(descrip);
			r.setIdusuario(idusu);
			serreq.guardar(r);
			
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/principal";
	}
	
	// consultas
	
	@GetMapping("/consultaRequerimiento/")
	public String consulta(@RequestParam(name = "idusuario", required = false) Integer id,Model model) {
		try {
			List<Requerimiento> lista = serreq.listado();
			model.addAttribute("req", lista);
			if (id != null) {
				Requerimiento lista1 = serreq.buscar(id);
			    model.addAttribute("reque", lista1);
			}
		} catch (Exception e) {
			System.out.println("Erro en : " + e.getLocalizedMessage());
		}
		return "consultaRequerimiento";
	}
	//PDF - Requerimiento
//	@GetMapping("/requerimiento/pdf")
//	public void exportarListadoRequerimiento(HttpServletResponse response) throws DocumentException, IOException {
//		response.setContentType("application/pdf");
//		
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String fechaActual = format.format(new Date());
//		
//		String cabecera = "Content-Disposition";
//		String valor = "inline;filename=requerimiento_" + fechaActual + ".pdf";
//		
//		response.setHeader(cabecera, valor);
//		
//		List<Requerimiento> empleado = serreq.listado();
//		
//		RequerimientoPDF exporter = new RequerimientoPDF(empleado);
//		
//		exporter.exportar(response);
//	}
	
	@GetMapping("/reporte/pdf")
	public void reporte(HttpServletResponse response) {
		try {
			List<Requerimiento> data = serreq.listado();
			File file = ResourceUtils.getFile("classpath:requerimiento_reporte.jrxml");
			JRBeanCollectionDataSource info = new JRBeanCollectionDataSource(data);
			JasperPrint print = Libreria.generarReporte(file, info);
			response.setContentType("application/pdf");
			OutputStream salida=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(print, salida);
		} catch (Exception e) {
			System.out.println("Error : " + e.getLocalizedMessage());
		}
	}
	
}
