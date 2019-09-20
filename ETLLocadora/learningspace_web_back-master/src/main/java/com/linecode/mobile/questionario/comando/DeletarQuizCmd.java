package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;

public class DeletarQuizCmd {
    @Min(value = 1, message = "ID inválido")
    private long quiz;

    public long getQuiz() {
        return quiz;
    }
}
