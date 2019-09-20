package com.linecode.compartilhado.dto;

public class RespostaArquivoDto {

	private String nome;
	private String tipo;
	private byte[] blob;
	
	public RespostaArquivoDto(String nome,String tipo, byte[] blob) {
		this.nome = nome;
		this.tipo = tipo;
		this.blob = blob;
	}

	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public byte[] getBlob() {
		return blob;
	}
	
	public int tamanho() {
		return blob.length;
	}
}
