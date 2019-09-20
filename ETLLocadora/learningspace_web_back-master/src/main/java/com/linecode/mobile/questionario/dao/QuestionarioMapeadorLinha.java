package com.linecode.mobile.questionario.dao;

import com.linecode.mobile.questionario.dto.EscolhaDto;
import com.linecode.mobile.questionario.dto.PerguntaDto;
import com.linecode.mobile.questionario.dto.QuizDto;
import com.linecode.mobile.questionario.dto.TopicoDto;
import com.sun.rowset.internal.Row;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionarioMapeadorLinha {

    public static final TopicoDtoMapeadorLinha TOPICO_MAPEADOR_LINHA = new TopicoDtoMapeadorLinha();
    public static final QuizDtoMapeadorLinha QUIZ_MAPEADOR_LINHA = new QuizDtoMapeadorLinha();
    public static final PerguntaDtoMapeadorLinha PERGUNTA_MAPEADOR_LINHA = new PerguntaDtoMapeadorLinha();
    public static final EscolhaDtoMapeadorLinha ESCOLHA_MAPEADOR_LINHA = new EscolhaDtoMapeadorLinha();

    private static class TopicoDtoMapeadorLinha implements RowMapper<TopicoDto> {
        @Override
        public TopicoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TopicoDto(
                    rs.getLong("ID_TOPICO"),
                    rs.getString("DSC_TOPICO")
            );
        }
    }

    private static class QuizDtoMapeadorLinha implements RowMapper<QuizDto> {
        @Override
        public QuizDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new QuizDto(
                    rs.getLong("ID_QUIZ"),
                    rs.getString("DSC_QUIZ"),
                    rs.getLong("ID_TOPICO")
            );
        }
    }

    private static class PerguntaDtoMapeadorLinha implements RowMapper<PerguntaDto> {
        @Override
        public PerguntaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PerguntaDto(
                    rs.getLong("ID_PERGUNTA"),
                    rs.getLong("ID_QUIZ"),
                    rs.getString("DSC_PERGUNTA"),
                    rs.getString("URL_IMAGEM")
            );
        }
    }

    private static class EscolhaDtoMapeadorLinha implements RowMapper<EscolhaDto> {
        @Override
        public EscolhaDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EscolhaDto(
                    rs.getLong("ID_PERGUNTA"),
                    rs.getLong("ID_ESCOLHA"),
                    rs.getString("DSC_ESCOLHA"),
                    rs.getString("EXP_ESCOLHA"),
                    rs.getBoolean("CORRETA")
            );
        }
    }
}
