package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class CadastrarQuizCmd {
    @Min(value = 1, message = "ID tópico inválido")
    private long topico;

    @NotEmpty(message = "Nome do quiz vazio")
    private String nome;

    public long getTopico() {
        return topico;
    }

    public String getNome() {
        return nome;
    }
}
