package com.linecode.forum.postagens.comando;

import java.util.List;

import javax.validation.constraints.Min;

public class CriarPostagemCmd {
	
	private String textoPostagem;
	private String tituloPostagem;

    @Min(value = 1, message = "id do criador inválido")
    private long idUsuario;

    @Min(value = 1, message = "id da disciplina inválido")
    private long idDisciplina;
    
    private List<Long> tags;

	public String getTextoPostagem() {
		return textoPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}
	
	public List<Long> getTags() {
		return tags;
	}
	
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
