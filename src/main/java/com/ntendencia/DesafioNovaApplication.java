package com.ntendencia;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ntendencia.domain.Cidade;
import com.ntendencia.domain.Endereco;
import com.ntendencia.domain.Estado;
import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.CidadeRepository;
import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.EstadoRepository;
import com.ntendencia.repositories.UsuarioRepository;

@SpringBootApplication
public class DesafioNovaApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;

	public static void main(String[] args) {
		SpringApplication.run(DesafioNovaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario user1 = new Usuario(1, "Bruno", "123.123.123.23", "03/02/2003", SexoUsuario.MASCULINO);
		Usuario user2 = new Usuario(2, "Luciana", "122.121.122.21", "08/04/1993", SexoUsuario.FEMININO);

		usuarioRepo.saveAll(Arrays.asList(user1, user2));

		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Barra da tijuca", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

		Endereco endereco1 = new Endereco(null, "Ao lado da estacao", "4817", "casa 10", "Rj", "22783127", user1, c1);
		Endereco endereco2 = new Endereco(null, "proximo a padaria", "4819", "casa 11", "Rj", "22780160", user2, c1);

		user1.getEnderecos().addAll(Arrays.asList(endereco1));
		user2.getEnderecos().addAll(Arrays.asList(endereco2));

		enderecoRepo.saveAll(Arrays.asList(endereco1, endereco2));

	}

}
