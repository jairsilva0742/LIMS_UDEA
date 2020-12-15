package com.lims.componentes.usuario.servicio;

import java.util.List;

import com.lims.componentes.usuario.modelo.Usuario;


public interface UsuarioServicio {

    void crearUsuario(Usuario usuario);
	
	Usuario actualizarUsuario(Usuario usuario);
	
	List<Usuario> obtenerTodosUsuario();
	
	Usuario obtenerUsuarioPorId(long usuarioId);
	
	void eliminarUsuario(long id);
}
