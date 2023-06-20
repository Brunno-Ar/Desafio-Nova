package com.ntendencia.services;

import com.ntendencia.domain.Cidade;
import com.ntendencia.domain.Endereco;
import com.ntendencia.domain.Estado;
import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.CidadeRepository;
import com.ntendencia.repositories.EstadoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class DBService {

	private final UsuarioRepository usuarioRepo;
	private final CidadeRepository cidadeRepo;
	private final EstadoRepository estadoRepo;

	public DBService(UsuarioRepository usuarioRepo, CidadeRepository cidadeRepo, EstadoRepository estadoRepo) {
		this.usuarioRepo = usuarioRepo;
		this.cidadeRepo = cidadeRepo;
		this.estadoRepo = estadoRepo;
	}


	public void instantiateTestDataBase() {

		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Rio de Janeiro", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		Endereco endereco1 = new Endereco( "Ao lado da estacao", "4817", "casa 10",
				"Rj", "22783127", c1);

		Endereco endereco2 = new Endereco("proximo a padaria", "4819", "casa 11",
				"Rj", "22780160", c1);

		Usuario user1 = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003", SexoUsuario.MASCULINO, endereco1);
		Usuario user2 = new Usuario(2, "Luciana", "122.121.122.21", "08/04/1993", SexoUsuario.FEMININO, endereco2);


		estadoRepo.saveAll(Arrays.asList(est1,est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2, c3));
		usuarioRepo.saveAll(Arrays.asList(user1, user2));
	}
}
