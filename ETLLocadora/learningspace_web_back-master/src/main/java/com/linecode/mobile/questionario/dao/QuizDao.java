package com.linecode.mobile.questionario.dao;

import com.linecode.compartilhado.paginacao.ControladorPagina;
import com.linecode.compartilhado.paginacao.PaginaDto;
import com.linecode.mobile.questionario.comando.*;
import com.linecode.mobile.questionario.dto.EscolhaDto;
import com.linecode.mobile.questionario.dto.PerguntaDto;
import com.linecode.mobile.questionario.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PropertySource("com/linecode/mobile/questionario/questionarioDao.xml")
@Repository
public class QuizDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;

    @Transactional
    public void cadastrarQuiz(CadastrarQuizCmd cadastrarQuizCmd) {
        jdbcTemplate.update(
                env.getProperty("cadastrarQuiz"),
                cadastrarQuizCmd.getNome(),
                cadastrarQuizCmd.getTopico()
        );
    }

    @Transactional(readOnly = true)
    public PaginaDto<QuizDto> getQuizes(int numPagina, int tamanhoPagina) {
        ControladorPagina<QuizDto> controladorPagina = new ControladorPagina<>();
        return controladorPagina.carregarPagina(
                jdbcTemplate,
                env.getProperty("quantidadeQuizes"),
                env.getProperty("listarQuizes"),
                new Object[]{},
                numPagina,
                tamanhoPagina,
                QuestionarioMapeadorLinha.QUIZ_MAPEADOR_LINHA
        );
    }

    @Transactional
    public void deletarQuiz(DeletarQuizCmd deletarQuizCmd) {
        jdbcTemplate.update(
                env.getProperty("deletarQuiz"),
                deletarQuizCmd.getQuiz()
        );
    }

    @Transactional
    public void cadastrarPergunta(CadastrarPerguntaCmd cadastrarPerguntaCmd) {
        jdbcTemplate.update(
                env.getProperty("cadastrarPergunta"),
                cadastrarPerguntaCmd.getQuiz(),
                cadastrarPerguntaCmd.getDescricao(),
                cadastrarPerguntaCmd.getUrlImagem()
        );
    }

    @Transactional
    public void deletarPergunta(DeletarPerguntaCmd deletarPerguntaCmd) {
        jdbcTemplate.update(
                env.getProperty("deletarPergunta"),
                deletarPerguntaCmd.getPergunta(),
                deletarPerguntaCmd.getQuiz()
        );
    }

    @Transactional(readOnly = true)
    public PaginaDto<PerguntaDto> listarPerguntas(ListarPerguntasCmd listarPerguntasCmd, int numPagina, int tamanhoPagina) {
        ControladorPagina<PerguntaDto> controladorPagina = new ControladorPagina<PerguntaDto>();

        return controladorPagina.carregarPagina(
                jdbcTemplate,
                env.getProperty("quantidadePerguntasQuiz"),
                env.getProperty("listarPerguntas"),
                new Object[]{listarPerguntasCmd.getQuiz()},
                numPagina,
                tamanhoPagina,
                QuestionarioMapeadorLinha.PERGUNTA_MAPEADOR_LINHA
        );
    }

    @Transactional
    public void cadastrarEscolha(CadastrarEscolhaCmd cadastrarEscolharCmd) {
        jdbcTemplate.update(
                env.getProperty("cadastrarEscolha"),
                cadastrarEscolharCmd.getPergunta(),
                cadastrarEscolharCmd.getDescricao(),
                cadastrarEscolharCmd.getExplicacao(),
                cadastrarEscolharCmd.isCorreta()
        );
    }

    @Transactional
    public void deletarEscolha(DeletarEscolhaCmd deletarEscolhaCmd) {
        jdbcTemplate.update(
                env.getProperty("deletarEscolha"),
                deletarEscolhaCmd.getPergunta(),
                deletarEscolhaCmd.getId()
        );
    }

    @Transactional(readOnly = true)
    public List<EscolhaDto> listarEscolhas(ListarEscolhasCmd listarEscolhasCmd) {
        return jdbcTemplate.query(
                env.getProperty("listarEscolhas"),
                new Object[]{listarEscolhasCmd.getPergunta()},
                QuestionarioMapeadorLinha.ESCOLHA_MAPEADOR_LINHA
        );
    }
}
