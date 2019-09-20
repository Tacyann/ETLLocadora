package com.linecode.mobile.questionario.dto;

public class QuizDto {
    private long quiz;
    private long topico;
    private String descricao;

    public QuizDto(long quiz, String descricao, long topico) {
        this.quiz = quiz;
        this.topico = topico;
        this.descricao = descricao;
    }

    public long getQuiz() {
        return quiz;
    }

    public long getTopico() {
        return topico;
    }

    public String getDescricao() {
        return descricao;
    }
}
