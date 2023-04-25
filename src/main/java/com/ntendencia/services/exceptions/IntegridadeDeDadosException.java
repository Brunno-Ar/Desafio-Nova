package com.ntendencia.services.exceptions;

public class IntegridadeDeDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegridadeDeDadosException(String msg) {
		super(msg);
	}

	public IntegridadeDeDadosException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
