package com.linecode.forum.usuario.comando;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class LoginCmd {
	
	@Min(value = 1, message = "Matricula inválida")
	private long matricula;
	
	@NotEmpty(message = "Informe a senha")
	private String senha;

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
