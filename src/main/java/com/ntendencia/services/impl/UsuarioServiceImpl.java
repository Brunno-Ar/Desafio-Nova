package com.ntendencia.services.impl;

import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.dto.UsuarioDTO;
import com.ntendencia.dto.UsuarioNewDTO;
import com.ntendencia.repositories.UsuarioRepository;
import com.ntendencia.services.UsuarioService;
import com.ntendencia.services.Utils;
import com.ntendencia.services.exceptions.IntegridadeDeDadosException;
import com.ntendencia.services.exceptions.ObjetoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;


    private final ModelMapper modelMapper = new ModelMapper();

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario buscarPorCPF(Usuario objDTO) {
        return usuarioRepository.findByCpf(objDTO.getCPF());
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer id) {
        Optional<Usuario> usuario;
        usuario = usuarioRepository.findById(id);
        if (Objects.isNull(usuario)) throw new ObjetoNaoEncontradoException(
                Utils.getMensagemValidacao("usuario.nao.encontrado", id, Usuario.class.getName())
        );
        return usuario.orElse(null);
    }

    @Override
    @Transactional
    public Usuario inserirNovoUsuario(UsuarioNewDTO usuarioNewDTO) {
        Usuario usuario = modelMapper.map(usuarioNewDTO, Usuario.class);
        if (Objects.nonNull(buscarPorCPF(usuario))) {
            throw new IntegridadeDeDadosException(
                    Utils.getMensagemValidacao("cpf.cadastrado"));
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario obj) {
        Usuario newObj = buscarUsuarioPorId(obj.getId());
        atualizaObjeto(newObj, obj);
        usuarioRepository.save(newObj);
    }

    @Override
    public void excluirUsuario(Integer id) {
        buscarUsuarioPorId(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegridadeDeDadosException(
                    Utils.getMensagemValidacao("excluir.usuario.com.endereco"));
        }
    }

    @Override
    public List<Usuario> buscarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> buscaPaginadaDeUsuario(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return usuarioRepository.findAll(pageRequest);
    }

    @Override
    public Usuario inserirObjetoPeloDTO(UsuarioDTO objDto) {
        return modelMapper.map(objDto, Usuario.class);
    }

    private void atualizaObjeto(Usuario newObj, Usuario obj) {
        newObj.setNome(obj.getNome());
    }

    @Override
    public List<Usuario> buscarUsuariosFiltrados(String nome, String cpf, String dataNascimento, SexoUsuario sexo) {
        List<Usuario> usuario = usuarioRepository.findBy(nome, cpf, dataNascimento, sexo);
        if (Objects.isNull(usuario)) {
            throw new ObjetoNaoEncontradoException(
                    Utils.getMensagemValidacao("usuario.nao.encontrado", nome, Usuario.class.getName()));
        }
        return usuario;
    }


}
