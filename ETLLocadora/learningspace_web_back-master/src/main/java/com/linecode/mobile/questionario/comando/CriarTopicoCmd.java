package com.linecode.mobile.questionario.comando;

import javax.validation.constraints.NotEmpty;

public class CriarTopicoCmd {

    @NotEmpty(message = "Nome do tópico não pode estar vazio")
    private String nome;

    public String getNome() {
        return nome;
    }
}
