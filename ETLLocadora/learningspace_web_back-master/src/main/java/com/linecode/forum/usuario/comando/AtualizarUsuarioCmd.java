package com.linecode.forum.usuario.comando;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.linecode.compartilhado.regex.RegexUtil;

public class AtualizarUsuarioCmd {

	@Pattern(regexp = RegexUtil.EMAIL, message = RegexUtil.MSG_ERRO_EMAIL)
	private String email;

	@Size(min = 10, max = 11, message = "Telefone inválido.")
	private String telefone;

	private List<CursoUsuarioCmd> cursos;

	public String getEmail() {
		return email;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public List<CursoUsuarioCmd> getCursos() {
		return cursos;
	}
}
