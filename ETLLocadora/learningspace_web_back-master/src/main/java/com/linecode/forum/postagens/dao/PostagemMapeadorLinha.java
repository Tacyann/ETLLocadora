package com.linecode.forum.postagens.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.linecode.forum.postagens.dto.AnexoDto;
import com.linecode.forum.postagens.dto.CriarAnexosDto;
import com.linecode.forum.postagens.dto.PostagemDto;

public class PostagemMapeadorLinha {

    public static final AnexosDtoMapeadorLinha ANEXOS_MAPEADOR_LINHA = new AnexosDtoMapeadorLinha();
    public static final PostagemDtoMapeadorLinha POSTAGEM_MAPEADOR_LINHA = new PostagemDtoMapeadorLinha();
    public static final AnexoDtoMapeadorLinha ANEXO_DTO_MAPEADOR_LINHA = new AnexoDtoMapeadorLinha();

    private PostagemMapeadorLinha() {
        //somente atributos estaticos
    }

    private static class AnexosDtoMapeadorLinha implements RowMapper<CriarAnexosDto> {

        @Override
        public CriarAnexosDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CriarAnexosDto(
                    rs.getLong("ID_POSTAGEM"),
                    rs.getLong("ID_ANEXO"),
                    rs.getString("CAMINHO_ANEXO")
            );
        }
    }

    private static class PostagemDtoMapeadorLinha implements RowMapper<PostagemDto> {
        @Override
        public PostagemDto mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
            return new PostagemDto(
                    rs.getLong("ID_POSTAGEM"),
                    rs.getLong("ID_CRIADOR"),
                    rs.getString("TEXTO_POSTAGEM"),
                    rs.getString("TIT_POSTAGEM"),
                    rs.getDate("DATA_POSTAGEM"),
                    rs.getLong("ID_DISCIPLINA")
            );
        }
    }

    private static class AnexoDtoMapeadorLinha implements RowMapper<AnexoDto> {
        @Override
        public AnexoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AnexoDto(rs.getLong("ID_ANEXO"), rs.getString("NOME_ANEXO"));
        }
    }
}
