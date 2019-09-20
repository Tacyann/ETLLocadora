package com.linecode.forum.util.dto;

public class CursoDto {
	
	private long id;
	private String nome;
	
	public CursoDto(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
