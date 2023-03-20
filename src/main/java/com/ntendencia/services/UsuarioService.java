package com.ntendencia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntendencia.domain.Usuario;
import com.ntendencia.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario buscar(Integer id) {
		Optional<Usuario> user = repository.findById(id);
		return user.orElse(null);
	}
}
