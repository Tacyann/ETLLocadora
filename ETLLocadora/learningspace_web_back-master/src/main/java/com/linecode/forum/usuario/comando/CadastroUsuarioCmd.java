package com.linecode.forum.usuario.comando;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.linecode.compartilhado.regex.RegexUtil;

public class CadastroUsuarioCmd {
	
	@Min(value = 1, message = "Matrícula inválida.")
	private long matricula;
	
	@NotEmpty(message = "Informe o nome")
	private String nome;
	
	@NotEmpty(message = "Informe o email")
	@Pattern(regexp = RegexUtil.EMAIL, message = RegexUtil.MSG_ERRO_EMAIL)
	private String email;
	
	@NotEmpty(message = "Informe a senha")
	private String senha;
	
	@Min(value = 1, message = "Curso inválido.")
	private long curso;
	
	@Min(value = 1, message = "Período inválido.")
	@Max(value = 10, message = "Período inválido.")
	private int periodo;
	
	@Pattern(regexp = RegexUtil.TELEFONE, message = RegexUtil.MSG_ERRO_TELEFONE)
	private String telefone;
	
	public long getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	public long getCurso() {
		return curso;
	}
	
	public int getPeriodo() {
		return periodo;
	}
	
	public String getTelefone() {
		return telefone;
	}
}
