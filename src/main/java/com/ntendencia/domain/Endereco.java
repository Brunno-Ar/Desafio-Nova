package com.ntendencia.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "endereco_bairro")
	private String bairro;
	@JoinColumn(name = "endereco_numero")
	private String numero;
	@JoinColumn(name = "endereco_cep")
	private String cep;
	@JoinColumn(name = "endereco_logradouro")
	private String logradouro;
	@JoinColumn(name = "endereco_complemento")
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Endereco(String bairro, String numero, String cep, String logradouro, String complemento, Cidade cidade) {
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
