package com.linecode.compartilhado.error;

public class ErroNegocio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroNegocio(String msg) {
		super(msg);
	}
	
}
