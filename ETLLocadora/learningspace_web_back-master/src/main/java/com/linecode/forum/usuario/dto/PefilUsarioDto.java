package com.linecode.forum.usuario.dto;

import java.util.ArrayList;
import java.util.List;

public class PefilUsarioDto {
	
	private String matricula;
	private String nome;
	private String email;
	private String telefone;
	private String fotoBase64;
	private List<CursoUsuarioDto> cursos;
	
	public PefilUsarioDto(String matricula, String nome, String email, String telefone, String fotoBase64, List<CursoUsuarioDto> cursos) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cursos = cursos;
		this.fotoBase64 = fotoBase64;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public List<CursoUsuarioDto> getCursos() {
		return cursos;
	}
	
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
}
