package com.ntendencia.services;

import com.ntendencia.DTO.UsuarioDTO;
import com.ntendencia.DTO.UsuarioNewDTO;
import com.ntendencia.domain.Cidade;
import com.ntendencia.domain.Endereco;
import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.repositories.EnderecoRepository;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.exceptions.DataIntegrityException;
import com.ntendencia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Usuario buscarUsuarioPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
        }
        return usuario.orElse(null);
    }

    @Transactional
    public Usuario inserirNovoUsuario(Usuario obj) {
        obj.setId(null);
        obj = usuarioRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Usuario atualizarUsuario(Usuario obj) {
        Usuario newObj = buscarUsuarioPorId(obj.getId());
        atualizaObjeto(newObj, obj);
        return usuarioRepository.save(newObj);
    }

    public void excluirUsuario(Integer id) {
        buscarUsuarioPorId(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir usuarios que contém Endereço");
        }
    }

    public List<Usuario> buscarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }


    public Page<Usuario> buscaPaginadaDeUsuario(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return usuarioRepository.findAll(pageRequest);
    }

    public Usuario inserirObjetoPeloDTO(UsuarioDTO objDto) {
        return new Usuario(objDto.getId(), objDto.getNome(), null, null, null);
    }

    public Usuario inserirObjetoPeloDTO(UsuarioNewDTO objDto) {
        Usuario user = new Usuario(null, objDto.getNome(), objDto.getcpf(), objDto.getDataNascimento(),
                objDto.getSexo());

        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), user, cid);
        user.getEnderecos().add(end);
        return user;
    }

    private void atualizaObjeto(Usuario newObj, Usuario obj) {
        newObj.setNome(obj.getNome());
    }

    public List<Usuario> buscarUsuariosFiltrados(String nome, String cpf, String dataNascimento, SexoUsuario sexo) {
        List<Usuario> usuario = usuarioRepository.findBy(nome, cpf, dataNascimento, sexo);
        if (usuario == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + nome + ", Tipo: " + Usuario.class.getName());
        }
        return usuario;
    }

}
