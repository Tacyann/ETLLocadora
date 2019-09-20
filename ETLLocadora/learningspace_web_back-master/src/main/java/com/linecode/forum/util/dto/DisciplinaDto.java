package com.linecode.forum.util.dto;

public class DisciplinaDto {
	
	private long id;
	private int periodo;
	private String nome;
	
	public DisciplinaDto(long id, int periodo, String nome) {
		this.id = id;
		this.periodo = periodo;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}
	
	public int getPeriodo() {
		return periodo;
	}

	public String getNome() {
		return nome;
	}
}
