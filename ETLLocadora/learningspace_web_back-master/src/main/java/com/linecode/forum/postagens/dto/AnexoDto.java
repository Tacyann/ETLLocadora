package com.linecode.forum.postagens.dto;

public class AnexoDto {
	
	private long idAnexo;
	private String nomeArquivo;
	
	public AnexoDto(long idAnexo, String nomeArquivo) {
		this.idAnexo = idAnexo;
		this.nomeArquivo = nomeArquivo;
	}

	public long getIdAnexo() {
		return idAnexo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
}
