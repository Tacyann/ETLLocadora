package com.linecode.mobile.questionario.dto;

public class EscolhaDto {
    private long pergunta;
    private long id;
    private String descricao;
    private String explicacao;
    private boolean correta;

    public EscolhaDto(long pergunta, long id, String descricao, String explicacao, boolean correta) {
        this.pergunta = pergunta;
        this.id = id;
        this.descricao = descricao;
        this.explicacao = explicacao;
        this.correta = correta;
    }

    public long getPergunta() {
        return pergunta;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isCorreta() {
        return correta;
    }
}
