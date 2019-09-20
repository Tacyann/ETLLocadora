package com.linecode.forum.util.dto;

public class TagDto {
	
	private long id;
	private String descricao;

	public TagDto(long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
