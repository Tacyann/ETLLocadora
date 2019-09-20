package com.linecode.forum.postagens.dto;

public class CriarAnexosDto {
    private long idPostagem;
    private long idAnexo;
    private String nomeArquivo;

    public CriarAnexosDto(long idPostagem, String nomeArquivo) {
        this.idPostagem = idPostagem;
        this.nomeArquivo = nomeArquivo;
    }

    public CriarAnexosDto(long idPostagem, long idAnexo, String nomeArquivo) {
        this(idPostagem, nomeArquivo);
        this.idAnexo = idAnexo;
    }

    public long getIdPostagem() {
        return idPostagem;
    }

    public long getIdAnexo() {
        return idAnexo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }
}