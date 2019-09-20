package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CadastrarPerguntaCmd {

    @Min(value = 1, message = "Id quiz inválido")
    private long quiz;

    @NotEmpty(message = "Descrição da pergunta não pode estar vazia")
    private String descricao;

    private String urlImagem;

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
