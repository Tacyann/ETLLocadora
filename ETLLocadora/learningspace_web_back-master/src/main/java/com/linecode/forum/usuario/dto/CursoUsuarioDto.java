package com.linecode.forum.usuario.dto;

public class CursoUsuarioDto {
	
	private String nome;
	private String instituicao;
	
	public CursoUsuarioDto(String nome, String instituicao) {
		this.nome = nome;
		this.instituicao = instituicao;
	}

	public String getNome() {
		return nome;
	}

	public String getInstituicao() {
		return instituicao;
	}
}
