package com.linecode.forum.postagens.comando;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CriarComentarioCmd {
	
	@Min(value = 1, message = "Id da postagem inválido")
	private long postagem;
	
	@Min(value = 1, message = "Id do usuário inválido")
	private long usuario;
	
	@NotEmpty(message = "Informe o conteúdo do comentário")
	private String comentario;

	public long getPostagem() {
		return postagem;
	}
	
	public long getUsuario() {
		return usuario;
	}

	public String getComentario() {
		return comentario;
	}	
	
	public void setUsuario(long usuario) {
		this.usuario = usuario;
	}
}
