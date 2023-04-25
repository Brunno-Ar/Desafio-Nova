package com.ntendencia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query (value = "SELECT * FROM USUARIO WHERE nome LIKE :nome " +
			"or cpf = :cpf " +
			"or data_nascimento = :dataNascimento " +
			"or sexo = :sexo", nativeQuery = true)
	List<Usuario> findBy(@Param("nome") String nome, @Param("cpf") String cpf, @Param("dataNascimento") String dataNascimento, @Param("sexo") SexoUsuario sexo);

    Optional<Usuario> findByCpf(String cpf);
}
