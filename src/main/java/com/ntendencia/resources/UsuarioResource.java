package com.ntendencia.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ntendencia.domain.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> Listar() {
		Usuario user1 = new Usuario(1, "Bruno", "123.123.123.23", "casa 10", "03/02/2003", "Masculino");
		Usuario user2 = new Usuario(2, "Luciana", "122.121.122.21", "casa 11", "08/04/1993", "Feminino");

		List<Usuario> listaUsuario = new ArrayList<>();
		listaUsuario.add(user1);
		listaUsuario.add(user2);

		return listaUsuario;
	}
}
