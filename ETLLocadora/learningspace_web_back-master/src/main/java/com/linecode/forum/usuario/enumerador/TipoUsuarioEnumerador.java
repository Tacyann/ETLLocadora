package com.linecode.forum.usuario.enumerador;

public enum TipoUsuarioEnumerador {
	
	ADMIN("ADMIN"),
	ALUNO("ALUNO"),
	PROFESSOR("PROFESSOR"),
	MONITOR("MONITOR");
	
	private String tipo;
	
	private TipoUsuarioEnumerador(String tipo) {
		this.tipo = tipo;
	}
}
