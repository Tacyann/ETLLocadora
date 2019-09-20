package com.linecode.mobile.questionario.dto;

public class PerguntaDto {
    private long pergunta;
    private long quiz;
    private String descricao;
    private String urlImagem;

    public PerguntaDto(long pergunta, long quiz, String descricao, String urlImagem) {
        this.pergunta = pergunta;
        this.quiz = quiz;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
    }

    public long getPergunta() {
        return pergunta;
    }

    public long getQuiz() {
        return quiz;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
}
