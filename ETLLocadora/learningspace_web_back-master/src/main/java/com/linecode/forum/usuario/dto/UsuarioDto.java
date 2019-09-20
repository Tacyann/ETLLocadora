package com.linecode.forum.usuario.dto;

import com.linecode.forum.usuario.enumerador.TipoUsuarioEnumerador;

public class UsuarioDto {
	
	private long id;
	private String nome;
	private long matricula;
	private TipoUsuarioEnumerador tipoUsuario;
	
	public UsuarioDto(long id, String nome, long matricula, String tipo) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.tipoUsuario = TipoUsuarioEnumerador.valueOf(tipo);
	}
	
	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public long getMatricula() {
		return matricula;
	}
	
	public TipoUsuarioEnumerador getTipoUsuario() {
		return tipoUsuario;
	}
}
