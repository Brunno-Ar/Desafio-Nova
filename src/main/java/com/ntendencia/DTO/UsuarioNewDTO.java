package com.ntendencia.DTO;

import com.ntendencia.domain.enums.SexoUsuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UsuarioNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "{nome.obrigatorio}")
    @Length(min = 4, max = 120, message = "{tamanho.min.max.nome}")
    private String nome;

    @NotEmpty(message = "{cpf.obrigatorio}")
    @CPF
    private String cpf;

    @NotEmpty(message = "{dataNascimento.obrigatorio}")
    private String dataNascimento;
    private SexoUsuario sexo;
    @NotEmpty(message = "{bairro.obrigatorio}")
    private String bairro;
    private String numero;
    @NotEmpty(message = "{cep.obrigatorio}")
    private String cep;
    private String logradouro;
    private String complemento;
    private Integer cidadeId;

    public UsuarioNewDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getcpf() {
        return cpf;
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoUsuario getSexo() {
        return sexo;
    }

    public void setSexo(SexoUsuario sexo) {
        this.sexo = sexo;
    }

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

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
