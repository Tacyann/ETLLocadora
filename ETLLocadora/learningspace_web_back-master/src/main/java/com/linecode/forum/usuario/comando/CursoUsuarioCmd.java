package com.linecode.forum.usuario.comando;

import javax.validation.constraints.NotEmpty;

public class CursoUsuarioCmd {

	@NotEmpty(message = "Informe o nome do curso")
	private String nome;

	@NotEmpty(message = "Informe a instituição")
	private String instituicao;

	public String getNome() {
		return nome;
	}

	public String getInstituicao() {
		return instituicao;
	}
}
