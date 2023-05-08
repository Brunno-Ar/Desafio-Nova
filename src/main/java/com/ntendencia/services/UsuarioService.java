package com.ntendencia.services;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {

    //Tras informações detalhada sobre o usuario
    public Usuario buscarUsuarioPorId(Integer id);

    //Incluir novo usuario com os seus endereços
    public Usuario inserirNovoUsuario(Usuario obj);

    //atualiza as informações do usuario
    public Usuario atualizarUsuario(Usuario obj);

    //Deleta o usuario
    public void excluirUsuario(Integer id);

    // Tras os usuarios no banco de dados
    public List<Usuario> buscarTodosOsUsuarios();

    /**
     * Busca todos os usuarios de forma paginada
     *
     * @param page Quantidade de páginas
     * @param linesPerPage Quantidade de usuario que aparece por pagina
     * @param orderBy Ordernar por nome, cpf, data de nascimento e sexo
     * @param direction Ordenar se &eacute; Ascendente ou Descendente */
    public Page<Usuario> buscaPaginadaDeUsuario(Integer page, Integer linesPerPage, String orderBy, String direction);

    /**
     * Busca usuario filtrando pode nome, cpf, data de nascimento e sexo
     *
     * @param nome o nome do usuario
     * @param cpf o cpf do usuario
     * @param dataNascimento a data de nascimento do usuario
     * @param sexo o sexo do usuario
     */
    public List<Usuario> buscarUsuariosFiltrados(String nome, String cpf, String dataNascimento, SexoUsuario sexo);
}
