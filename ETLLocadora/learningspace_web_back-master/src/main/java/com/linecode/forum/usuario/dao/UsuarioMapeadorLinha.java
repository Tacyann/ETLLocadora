package com.linecode.forum.usuario.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.linecode.forum.usuario.dto.CursoUsuarioDto;
import com.linecode.forum.usuario.dto.PefilUsarioDto;
import com.linecode.forum.usuario.dto.UsuarioDto;;

public class UsuarioMapeadorLinha {

	public static final UsuarioDtoMapeadorLinha USUARIO_DTO_MAPEADOR_LINHA = new UsuarioDtoMapeadorLinha();
	public static final PerfilUsarioDtoMapeadorLinha PERFIL_USUARIO_MAPEADOR_LINHA = new PerfilUsarioDtoMapeadorLinha();

	private UsuarioMapeadorLinha() {
		// apenas atributos staticos
	}

	private static class UsuarioDtoMapeadorLinha implements ResultSetExtractor<UsuarioDto> {

		@Override
		public UsuarioDto extractData(ResultSet rs) throws SQLException {

			if (rs.next()) {
				return new UsuarioDto(rs.getLong("ID"), rs.getString("NOME"), rs.getLong("MATRICULA"),
						rs.getString("TIPO"));
			}

			return null;
		}
	}

	private static class PerfilUsarioDtoMapeadorLinha implements ResultSetExtractor<PefilUsarioDto> {

		@Override
		public PefilUsarioDto extractData(ResultSet rs) throws SQLException {

			if (rs.next()) {
				return new PefilUsarioDto(rs.getString("MATRICULA"), rs.getString("NOME"), rs.getString("EMAIL"),
						rs.getString("TELEFONE"), rs.getString("FOTO"), mapearCursos(rs));
			}

			return null;
		}
		
		private List<CursoUsuarioDto> mapearCursos(ResultSet rs) throws SQLException {
			List<CursoUsuarioDto> cursos = new ArrayList<>();
			
			do {
				cursos.add(new CursoUsuarioDto(rs.getString("NOME_CURSO"), rs.getString("INSTITUICAO_CURSO")));
			}while(rs.next());
			
			return cursos;
		}

	}
}
