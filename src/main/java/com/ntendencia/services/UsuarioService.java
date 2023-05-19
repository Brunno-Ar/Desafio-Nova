package com.ntendencia.services;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {

    /**
     * Tras informações detalhada sobre o usuario
     * @param id    id do usuario
     */

    Usuario buscarUsuarioPorId(Integer id);

    /**
     * Incluir novo usuario com os seus endereços
     * @param obj   Objeto do novo usuario
     */

    Usuario inserirNovoUsuario(Usuario obj);

    /**
     * Atualiza as informações do usuario
     * @param obj   Objeto do usuario que será atualizado
     */

    Usuario atualizarUsuario(Usuario obj);

    /**
     * Deleta o usuario
     * @param id    id do usuario que será deletado
     */
    void excluirUsuario(Integer id);

    /**
     * Tras os usuarios no banco de dados
     */

    List<Usuario> buscarTodosOsUsuarios();

    /**
     * Busca todos os usuarios de forma paginada
     *
     * @param page         Quantidade de páginas
     * @param linesPerPage Quantidade de usuario que aparece por pagina
     * @param orderBy      Ordernar por nome, cpf, data de nascimento e sexo
     * @param direction    Ordenar se Ascendente ou Descendente
     */
    Page<Usuario> buscaPaginadaDeUsuario(Integer page, Integer linesPerPage, String orderBy, String direction);

    /**
     * Busca usuario filtrando pode nome, cpf, data de nascimento e sexo
     *
     * @param nome           o nome do usuario
     * @param cpf            o cpf do usuario
     * @param dataNascimento a data de nascimento do usuario
     * @param sexo           o sexo do usuario
     */
    List<Usuario> buscarUsuariosFiltrados(String nome, String cpf, String dataNascimento, SexoUsuario sexo);
}
