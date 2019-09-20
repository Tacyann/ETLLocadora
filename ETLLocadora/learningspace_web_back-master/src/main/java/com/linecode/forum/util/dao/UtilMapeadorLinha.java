package com.linecode.forum.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linecode.forum.util.dto.CursoDto;
import com.linecode.forum.util.dto.DisciplinaDto;
import com.linecode.forum.util.dto.TagDto;

public class UtilMapeadorLinha {
	
	public static final CursoDtoMapeadorLinha CURSO_DTO_MAPEADOR_LINHA = new CursoDtoMapeadorLinha();
	public static final DisciplinaDtoMapeadorLinha DISCIPLINA_DTO_MAPEADOR_LINHA = new DisciplinaDtoMapeadorLinha();
	public static final TagPostagemDtoMapeadorLinha TAG_POSTAGEM_DTO_MAPEADOR_LINHA = new TagPostagemDtoMapeadorLinha();
	
	private static class CursoDtoMapeadorLinha implements RowMapper<CursoDto> {
		@Override
		public CursoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new CursoDto(rs.getLong("ID"), rs.getString("NOME"));
		}
	}
	
	private static class DisciplinaDtoMapeadorLinha implements RowMapper<DisciplinaDto> {
		@Override
		public DisciplinaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new DisciplinaDto(rs.getLong("ID"), rs.getInt("PERIODO"), rs.getString("NOME"));
		}
		
	}
	
	private static class TagPostagemDtoMapeadorLinha implements RowMapper<TagDto> {
		@Override
		public TagDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new TagDto(rs.getLong("ID"), rs.getString("DESCRICAO"));
		}
		
	}
}
