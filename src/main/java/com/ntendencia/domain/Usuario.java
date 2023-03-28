package com.ntendencia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ntendencia.domain.enums.SexoUsuario;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpfOuCnpj;

	@OneToMany(mappedBy = "usuario")
	private List<Endereco> enderecos = new ArrayList<>();

	private String dataNascimento;

	private Integer sexo;

	public Usuario() {

	}

	public Usuario(Integer id, String nome, String cpfoucnpj, String dataNascimento, SexoUsuario sexo) {
		super();
		this.id = id;
		this.nome = nome;
		cpfOuCnpj = cpfoucnpj;
		this.dataNascimento = dataNascimento;
		this.sexo = (sexo == null) ? null : sexo.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpfOuCnpj;
	}

	public void setCPF(String cPF) {
		cpfOuCnpj = cPF;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public SexoUsuario getSexo() {
		return SexoUsuario.toEnum(sexo);
	}

	public void setSexo(SexoUsuario sexo) {
		this.sexo = sexo.getCod();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfOuCnpj, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpfOuCnpj, other.cpfOuCnpj) && Objects.equals(id, other.id);
	}

}
