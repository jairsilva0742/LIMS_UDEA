package com.lims.componentes.usuario.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lims.componentes.usuario.modelo.Usuario;
import com.lims.componentes.usuario.repositorio.UsuarioRepositorio;
import com.lims.excepcion.ResourceNotFoundException;

@Service

public class UsuarioServicioImpl implements UsuarioServicio{

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public void crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		this.usuarioRepositorio.save(usuario);
			}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
        Optional<Usuario> usuarioOb=this.usuarioRepositorio.findById(usuario.getId());
		
		if(usuarioOb.isPresent()) {
			Usuario usuarioActualizar= usuarioOb.get();
			usuarioActualizar.setId(usuario.getId());
			usuarioActualizar.setNombre(usuario.getNombre());
			usuarioActualizar.setTelefono(usuario.getTelefono());
			usuarioActualizar.setUser(usuario.getUser());
			usuarioActualizar.setContrasena(usuario.getContrasena());
			usuarioRepositorio.save(usuarioActualizar);
			
			return usuarioActualizar;
		}
		else {
			
			throw new ResourceNotFoundException("No encontrado con ID: "+  usuario.getId());
		}
	}

	@Override
	public List<Usuario> obtenerTodosUsuario() {
		// TODO Auto-generated method stub
		return this.usuarioRepositorio.findAll();
	}

	@Override
	public Usuario obtenerUsuarioPorId(long usuarioId) {
		// TODO Auto-generated method stub
		Optional<Usuario> usuarioOb=this.usuarioRepositorio.findById(usuarioId);
		if(usuarioOb.isPresent()) {
			return usuarioOb.get();
			
		}else {
			throw new ResourceNotFoundException("No encontrado con ID: "+  usuarioId);
		}
		
	}
	

	@Override
	public void eliminarUsuario(long usuarioId) {
		// TODO Auto-generated method stub
		
		Optional<Usuario> usuarioOb=this.usuarioRepositorio.findById(usuarioId);
		if(usuarioOb.isPresent()) {
			this.usuarioRepositorio.delete(usuarioOb.get());
					
		}else {
			throw new ResourceNotFoundException("No encontrado con ID: "+  usuarioId);
		}
		
	}
	
	
	
}
