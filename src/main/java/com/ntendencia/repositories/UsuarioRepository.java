package com.ntendencia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query (value = "SELECT * FROM USUARIO WHERE nome = ?", nativeQuery = true)
	List<Usuario> findBy(String nome, String cpf, String dataNascimento, SexoUsuario sexo);

}
