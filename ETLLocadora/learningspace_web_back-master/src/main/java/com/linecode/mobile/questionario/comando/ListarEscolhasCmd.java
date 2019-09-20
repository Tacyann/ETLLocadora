package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;

public class ListarEscolhasCmd {
    @Min(value = 1, message = "ID pergunta inválido")
    private long pergunta;

    public long getPergunta() {
        return pergunta;
    }
}
