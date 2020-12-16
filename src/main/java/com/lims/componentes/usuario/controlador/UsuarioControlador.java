package com.lims.componentes.usuario.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lims.componentes.usuario.modelo.Usuario;
import com.lims.componentes.usuario.servicio.UsuarioServicio;



@Controller
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;	
	
	@GetMapping("/")
	public String vistaPaginaWeb(Model modelo) {
		modelo.addAttribute("listaUsuarios", usuarioServicio.obtenerTodosUsuario());
		return "index.html";
	}
	
	@GetMapping("/vistaNuevoUsuario")
	public String vistaNuevoUsuario(Model modelo) {
		Usuario usuario=new Usuario();
		modelo.addAttribute("usuario", usuario);
		return "nuevo_usuario.html";
	}
	
	@PostMapping("/GuardarUsuario")
	public String GuardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		usuarioServicio.crearUsuario(usuario);
		return "redirect:/";
	}

	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(Model model, @PathVariable long id) {
		usuarioServicio.eliminarUsuario(id);
		return "redirect:/";
	}
	
	@GetMapping("/EditarUsuario/{id}")
	public String vistaEditarUsuario(@PathVariable (value="id") long id, Model model) {
		
		Usuario usuario=usuarioServicio.obtenerUsuarioPorId(id);
		model.addAttribute("usuario", usuario);
		return "editar_usuario.html";
	}

}
