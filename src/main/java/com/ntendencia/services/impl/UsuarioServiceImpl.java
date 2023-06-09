package com.ntendencia.services.impl;

import com.ntendencia.domain.Cidade;
import com.ntendencia.domain.Endereco;
import com.ntendencia.domain.Usuario;
import com.ntendencia.domain.enums.SexoUsuario;
import com.ntendencia.dto.UsuarioDTO;
import com.ntendencia.dto.UsuarioNewDTO;
import com.ntendencia.repositories.EnderecoRepository;
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

    private final EnderecoRepository enderecoRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    private Usuario buscarPorCPF(Usuario objDTO) {
        return usuarioRepository.findByCpf(objDTO.getCPF());
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        Optional<Usuario> usuario;
        usuario = usuarioRepository.findById(id);
        if (Objects.isNull(usuario)) throw new ObjetoNaoEncontradoException(
                Utils.getMensagemValidacao("usuario.nao.encontrado", id, Usuario.class.getName())
        );
        return usuario.orElse(null);
    }

    @Transactional
    public Usuario inserirNovoUsuario(Usuario obj) {
        if (Objects.nonNull(buscarPorCPF(obj))) {
            throw new IntegridadeDeDadosException(
                    Utils.getMensagemValidacao("cpf.cadastrado"));
        }
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
            throw new IntegridadeDeDadosException(
                    Utils.getMensagemValidacao("excluir.usuario.com.endereco"));
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

        return modelMapper.map(objDto, Usuario.class);
    }

    public Usuario inserirObjetoPeloDTO(UsuarioNewDTO objDto) {
        Usuario usuario = modelMapper.map(objDto, Usuario.class);

        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), usuario, cid);

        usuario.getEnderecos().add(end);

        return usuario;
    }

    private void atualizaObjeto(Usuario newObj, Usuario obj) {
        newObj.setNome(obj.getNome());
    }

    public List<Usuario> buscarUsuariosFiltrados(String nome, String cpf, String dataNascimento, SexoUsuario sexo) {
        List<Usuario> usuario = usuarioRepository.findBy(nome, cpf, dataNascimento, sexo);
        if (Objects.isNull(usuario)) {
            throw new ObjetoNaoEncontradoException(
                    Utils.getMensagemValidacao("usuario.nao.encontrado", nome, Usuario.class.getName()));
        }
        return usuario;
    }


}
