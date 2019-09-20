package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CadastrarEscolhaCmd {
    @NotEmpty
    private String descricao;

    private String explicacao;

    @NotEmpty(message = "Escolha não identificada como correta ou não")
    private boolean correta;

    @Min(value = 1, message = "ID pergunta inválido")
    private long pergunta;

    public String getDescricao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isCorreta() {
        return correta;
    }

    public long getPergunta() {
        return pergunta;
    }
}
