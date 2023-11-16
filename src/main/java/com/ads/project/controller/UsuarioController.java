package com.ads.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ads.project.entity.EnlaceMenu;
import com.ads.project.entity.Rol;
import com.ads.project.entity.Usuario;
import com.ads.project.service.RolService;
import com.ads.project.service.UsuarioService;
import com.ads.project.springconfig.UserService;

@SessionAttributes({"ENLACES","USUARIO"})

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService ser;
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private RolService serRol;
	
	@GetMapping("/principal")
	public String menu(Authentication auth, Model model) {
		if (auth != null) {
	        String vLogin = auth.getName();
	        Usuario u = ser.loginUsuario(vLogin);
	        List<EnlaceMenu> lista = ser.enlaceUsuario(u.getRol().getIdrol());
	        model.addAttribute("ENLACES", lista);
	        model.addAttribute("USUARIO", u);
	        return "principal"; 
	    }
		return "principal"; 
	}
	
	@GetMapping("/login")
	public String inicio() {
		return "login";
	}
	
	@GetMapping("/registrarUsuario")
	public String registrar(Model model) {
	    int cod = ser.obtenerCodigo();
	    model.addAttribute("codigo", cod);
	    return "registrarUsuario";
	}
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/registrarUsuario")
	public String registrar(@RequestParam(name = "idusuario", required = false) int cod,@RequestParam(name = "nom", required = false) String nom, @RequestParam(name = "correo", required = false) String correo,
			@RequestParam(name = "contra", required = false) String contra, @RequestParam(name = "rcontra", required = false) String r_contra ,@RequestParam(name = "dni", required = false) String dni, RedirectAttributes redirect) {
		try {
			Usuario u = new Usuario();
			u.setIdusuario(cod);
			u.setNombre(nom);
			u.setCorreo(correo);
			u.setPass(contra);
			u.setRep_pass(r_contra);
			u.setDni(dni);
			u.setIdrol(2);
			if(u == null || u.getNombre().isEmpty() || u.getCorreo().isEmpty() || u.getPass().isEmpty() || u.getDni().isEmpty()) {
				redirect.addFlashAttribute("ERROR","Campos Incorrectos o Vacios");
				return "redirect:/registrarUsuario";
			}else 
				if(u.getRep_pass() != null && u.getRep_pass().equals(u.getPass())){
				userS.guardarUsuario(u);
				redirect.addFlashAttribute("MENSAJE","Usuario Registrado");
			}else {
				return "redirect:/registrarUsuario";
			}
			
		} catch (Exception e) {
			System.out.println("No Se registro : " + e.getMessage());
		}
		return "redirect:/login";
	}
	
	// pagina mantenimiento
	@GetMapping("/mantenimientos/")
	public String mantenimiento() {
		return "mantenimiento";
	}

	@GetMapping("/consultas/")
	public String consulta() {
		return "consulta";
	}

	@GetMapping("/reportes/")
	public String reporte() {
		return "reporte";
	}
	
	//Gestion Usuarios
	
	@GetMapping("/usuario/")
	public String usuario(Model model) {
		List<Usuario> lista = ser.listado();
		model.addAttribute("usuario", lista);
		return "usuario";
	}
	
	@GetMapping("/regUsuario/")
	public String regUsuario(Model model) {
		int cod = ser.obtenerCodigo();
		List<Rol> lista1 = serRol.listado();
		model.addAttribute("codigo", cod);
		model.addAttribute("rol", lista1);
		return "regUsuario";
	}
	
	@PostMapping("/regUsuario/")
	public String regUsuario(@RequestParam(name = "idusuario", required = false) int cod,
			@RequestParam(name = "nombre", required = false) String nom,
			@RequestParam(name = "correo", required = false) String correo,
			@RequestParam(name = "pass", required = false) String pass,
			@RequestParam(name = "dni", required = false) String dni,
			@RequestParam(name = "idrol", required = false) int rol) {
		try {
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			String by = encode.encode(pass);
			
			Usuario u = new Usuario();
			if(cod == 0 || nom.isEmpty() || correo.isEmpty() || pass.isEmpty() || dni.isEmpty() || rol == 0) {
				return "redirect:/regUsuario/";
			}else {
				u.setIdusuario(cod);
				u.setNombre(nom);
				u.setCorreo(correo);
				u.setPass(by);
				u.setRep_pass(pass);
				u.setDni(dni);
				u.setIdrol(rol);
				ser.guadar(u);
			}
		} catch (Exception e) {
			System.out.println("Erro en : " + e.getLocalizedMessage());
		}
		return "redirect:/usuario/";
	}
	
	@GetMapping("/actualizarUsuario/{idusuario}")
	public String actualizarUsuario(@PathVariable Integer idusuario, Model model) {
		Usuario u = ser.buscar(idusuario);
		model.addAttribute("codigo", u);
		List<Rol> lista1 = serRol.listado();
		model.addAttribute("rol", lista1);
		return "actualizarUsuario";
	}
	
	@PostMapping("/actualizarUsuario/{idusuario}")
	public String actualizarUsuario(@PathVariable Integer idusuario, @ModelAttribute("usuario") Usuario usu) {
		try {
			Usuario u = ser.buscar(idusuario);
			u.setNombre(usu.getNombre());
			u.setCorreo(u.getCorreo());
			 if (!usu.getPass().isEmpty()) {
		            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		            String pass = encoder.encode(usu.getPass());
		            u.setPass(pass);
		        }
			 if (!usu.getPass().isEmpty()) {
		            u.setRep_pass(usu.getPass());
		        }
			u.setDni(usu.getDni());
			u.setIdrol(u.getIdrol());
			ser.guadar(u);
		} catch (Exception e) {
			System.out.println("Error en : " + e.getLocalizedMessage());
		}
		return "redirect:/usuario/";
	}
	
	@GetMapping("/usuario/eliminarUsuario/{idusuario}")
	public String eliminarUsuario(@PathVariable Integer idusuario) {
		ser.eliminar(idusuario);
		return "redirect:/usuario/";
	}
}
