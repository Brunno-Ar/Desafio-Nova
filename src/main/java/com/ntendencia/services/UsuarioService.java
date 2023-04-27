package com.ntendencia.services;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {

    public Usuario buscarUsuarioPorId(Integer id);

    public Usuario inserirNovoUsuario(Usuario obj);

    public Usuario atualizarUsuario(Usuario obj);

    public void excluirUsuario(Integer id);

    public List<Usuario> buscarTodosOsUsuarios();

    public Page<Usuario> buscaPaginadaDeUsuario(Integer page, Integer linesPerPage, String orderBy, String direction);

    public List<Usuario> buscarUsuariosFiltrados(String nome, String cpf, String dataNascimento, SexoUsuario sexo);
}
