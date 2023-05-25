package com.ntendencia.services.exceptions;

public class ErroAoCarregarJsonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroAoCarregarJsonException(String msg) {
		super(msg);
	}

	public ErroAoCarregarJsonException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
