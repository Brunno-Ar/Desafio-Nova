package com.ntendencia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.ntendencia.domain.enums.SexoUsuario;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private String dataNascimento;
	@Enumerated(EnumType.STRING)
	private SexoUsuario sexo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	public Usuario() {

	}

	public Usuario(Integer id, String nome, String cpf, String dataNascimento, SexoUsuario sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
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
		return cpf;
	}

	public void setCPF(String cPF) {
		cpf = cPF;
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
		return this.sexo;
	}

	public void setSexo(SexoUsuario sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
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
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", dataNascimento='" + dataNascimento + '\'' +
				", sexo=" + sexo +
				", enderecos=" + enderecos +
				'}';
	}
}
