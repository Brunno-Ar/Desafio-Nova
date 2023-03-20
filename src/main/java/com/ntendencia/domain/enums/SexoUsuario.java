package com.ntendencia.domain.enums;

public enum SexoUsuario {

	MASCULINO(1, "Masculino"), 
	FEMININO(2, "Feminino");

	private int cod;
	private String descricao;

	SexoUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static SexoUsuario toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (SexoUsuario x : SexoUsuario.values()) {
			if (cod.equals(x.getCod()))
				;
			return x;
		}
		throw new IllegalArgumentException("Id invalido" + cod);
	}
}
