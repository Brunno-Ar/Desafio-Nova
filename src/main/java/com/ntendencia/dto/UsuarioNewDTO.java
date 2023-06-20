package com.ntendencia.dto;

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

    private EnderecoDTO enderecoDTO;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
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

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
