package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;

public class DeletarPerguntaCmd {

    @Min(value = 1, message = "ID pergunta inválido")
    private long pergunta;
    @Min(value = 1, message = "ID quiz inválido")
    private long quiz;

    public long getPergunta() {
        return pergunta;
    }

    public long getQuiz() {
        return quiz;
    }
}
