package com.linecode.mobile.questionario.controller;

import com.linecode.compartilhado.paginacao.PaginaDto;
import com.linecode.mobile.questionario.comando.*;
import com.linecode.mobile.questionario.dto.EscolhaDto;
import com.linecode.mobile.questionario.dto.PerguntaDto;
import com.linecode.mobile.questionario.dto.QuizDto;
import com.linecode.mobile.questionario.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/cadastrar")
    public String cadastrarQuiz(@RequestBody CadastrarQuizCmd cadastrarQuizCmd) {
        quizService.cadastrarQuiz(cadastrarQuizCmd);
        return "Questionario cadastrado com sucesso";
    }

    @GetMapping("/listar")
    public PaginaDto<QuizDto> listarQuizes(
            @RequestParam(defaultValue = "1") int numPagina,
            @RequestParam(defaultValue = "15") int tamanhoPagina) {
        return quizService.listarQuizes(numPagina, tamanhoPagina);
    }

    @DeleteMapping("/deletar")
    public String deletarQuiz(@RequestBody DeletarQuizCmd deletarQuizCmd) {
        quizService.deletarQuiz(deletarQuizCmd);
        return "Questionario deletado com sucesso";
    }

    @PostMapping("/cadastrar/pergunta")
    public String cadastrarPergunta(@RequestBody CadastrarPerguntaCmd cadastrarPerguntaCmd) {
        quizService.cadastrarPergunta(cadastrarPerguntaCmd);
        return "Pergunta adicionada ao quiz";
    }

    @DeleteMapping("/deletar/pergunta")
    public String deletarPergunta(@RequestBody DeletarPerguntaCmd deletarPerguntaCmd) {
        quizService.deletarPergunta(deletarPerguntaCmd);
        return "Pergunta deletada do quiz";
    }

    @PostMapping("/listar/pergunta")
    public PaginaDto<PerguntaDto> listarPerguntas(
            @RequestBody ListarPerguntasCmd listarPerguntasCmd,
            @RequestParam(defaultValue = "1") int numPagina,
            @RequestParam(defaultValue = "15") int tamanhoPagina) {
        return quizService.listarPerguntas(listarPerguntasCmd, numPagina, tamanhoPagina);
    }

    @PostMapping("/cadastrar/escolha")
    public String cadastrarEscolha(@RequestBody CadastrarEscolhaCmd cadastrarEscolharCmd) {
        quizService.cadastrarEscolha(cadastrarEscolharCmd);
        return "Escolha adicionada com sucesso";
    }

    @PostMapping("/listar/escolha")
    public List<EscolhaDto> listarEscolhas(@RequestBody ListarEscolhasCmd listarEscolhasCmd) {
        return quizService.listarEscolhas(listarEscolhasCmd);
    }

    @DeleteMapping("/deletar/escolha")
    public String deletarEscolha(@RequestBody DeletarEscolhaCmd deletarEscolhaCmd){
        quizService.deletarEscolha(deletarEscolhaCmd);
        return "Escolha deletada com sucesso";
    }



}
