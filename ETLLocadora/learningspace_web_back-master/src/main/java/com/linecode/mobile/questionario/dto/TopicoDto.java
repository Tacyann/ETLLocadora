package com.linecode.mobile.questionario.dto;

public class TopicoDto {
    private String nome;
    private long id;

    public TopicoDto(long id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }
}
