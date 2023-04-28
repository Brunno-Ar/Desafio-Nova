package com.ntendencia.DTO;

import com.ntendencia.domain.Cidade;

import javax.validation.constraints.NotEmpty;

public class EnderecoDTO {

    private String bairro;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String numero;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String cep;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String logradouro;
	private String complemento;

    private Cidade cidade;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
