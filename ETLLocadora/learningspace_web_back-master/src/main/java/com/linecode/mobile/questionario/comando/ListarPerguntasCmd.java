package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;

public class ListarPerguntasCmd {
    @Min(value = 1, message = "ID quiz inválido")
    private long quiz;

    public long getQuiz() {
        return quiz;
    }
}
