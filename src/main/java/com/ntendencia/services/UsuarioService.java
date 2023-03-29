package com.ntendencia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Usuario find(Integer id) {
		Optional<Usuario> user = repository.findById(id);
		if (user == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return user.orElse(null);
	}

	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir usuarios que contém Endereço");
		}
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), null, null, null);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNome(), objDto.getCpfOuCnpj(), objDto.getDataNascimento(),
				SexoUsuario.toEnum(objDto.getSexo()));

		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), user, cid);
		user.getEnderecos().add(end);
		return user;
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
	}
}
