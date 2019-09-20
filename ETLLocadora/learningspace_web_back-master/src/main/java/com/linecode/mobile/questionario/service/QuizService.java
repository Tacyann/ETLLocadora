package com.linecode.mobile.questionario.service;

import com.linecode.compartilhado.paginacao.PaginaDto;
import com.linecode.compartilhado.error.ErroNegocio;
import com.linecode.compartilhado.servico.AutorizacaoServico;
import com.linecode.mobile.questionario.comando.*;
import com.linecode.mobile.questionario.dao.QuizDao;
import com.linecode.mobile.questionario.dto.EscolhaDto;
import com.linecode.mobile.questionario.dto.PerguntaDto;
import com.linecode.mobile.questionario.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class QuizService {

    @Autowired
    private AutorizacaoServico autorizacaoServico;

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private Validator validator;

    public void cadastrarQuiz(CadastrarQuizCmd cadastrarQuizCmd) {
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(cadastrarQuizCmd, "Quiz vazio");
        Set<ConstraintViolation<CadastrarQuizCmd>> violations = validator.validate(cadastrarQuizCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        quizDao.cadastrarQuiz(cadastrarQuizCmd);
    }

    public PaginaDto<QuizDto> listarQuizes(int numPagina, int tamanhoPagina) {
        autorizacaoServico.isLogado();

        return quizDao.getQuizes(numPagina, tamanhoPagina);
    }

    public void deletarQuiz(DeletarQuizCmd deletarQuizCmd) {
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(deletarQuizCmd, "Id vazio");
        Set<ConstraintViolation<DeletarQuizCmd>> violations = validator.validate(deletarQuizCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        quizDao.deletarQuiz(deletarQuizCmd);

    }

    public void cadastrarPergunta(CadastrarPerguntaCmd cadastrarPerguntaCmd) {
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(cadastrarPerguntaCmd, "Pergunta vazia");
        Set<ConstraintViolation<CadastrarPerguntaCmd>> violations = validator.validate(cadastrarPerguntaCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        quizDao.cadastrarPergunta(cadastrarPerguntaCmd);
    }

    public void deletarPergunta(DeletarPerguntaCmd deletarPerguntaCmd) {
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(deletarPerguntaCmd, "Id vazio");
        Set<ConstraintViolation<DeletarPerguntaCmd>> violations = validator.validate(deletarPerguntaCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        quizDao.deletarPergunta(deletarPerguntaCmd);
    }

    public PaginaDto<PerguntaDto> listarPerguntas(ListarPerguntasCmd listarPerguntasCmd, int numPagina, int tamanhoPagina) {
        autorizacaoServico.isLogado();
        Assert.notNull(listarPerguntasCmd, "Id pergunta vazio");
        Set<ConstraintViolation<ListarPerguntasCmd>> violations = validator.validate(listarPerguntasCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        return quizDao.listarPerguntas(listarPerguntasCmd, numPagina, tamanhoPagina);
    }

    public void cadastrarEscolha(CadastrarEscolhaCmd cadastrarEscolharCmd) {
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(cadastrarEscolharCmd, "Escolha vazia");
        Set<ConstraintViolation<CadastrarEscolhaCmd>> violations = validator.validate(cadastrarEscolharCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        quizDao.cadastrarEscolha(cadastrarEscolharCmd);
    }

    public void deletarEscolha(DeletarEscolhaCmd deletarEscolhaCmd){
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(deletarEscolhaCmd, "Escolha vazia");
        Set<ConstraintViolation<DeletarEscolhaCmd>> violations = validator.validate(deletarEscolhaCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        quizDao.deletarEscolha(deletarEscolhaCmd);
    }

    public List<EscolhaDto> listarEscolhas(ListarEscolhasCmd listarEscolhasCmd) {
        autorizacaoServico.isLogado();
        Assert.notNull(listarEscolhasCmd, "Id escolha vazio");
        Set<ConstraintViolation<ListarEscolhasCmd>> violations = validator.validate(listarEscolhasCmd);
        if (!violations.isEmpty()) {
            throw new ErroNegocio(violations.stream().findFirst().get().getMessage());
        }
        return quizDao.listarEscolhas(listarEscolhasCmd);
    }


}
