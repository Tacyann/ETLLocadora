package com.linecode.forum.postagens.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostagemDto {

    private long idPostagem;
    private String textoPostagem;
    private String tituloPostagem;
    private long idCriador;
    private Date dataPostagem;
    private List<AnexoDto> anexos;
    private long idDisciplina;

    public PostagemDto(long idPostagem, long idCriador, String textoPostagem, String tituloPostagem,
                       Date dataPostagem, long idDisciplina) {
        this.idPostagem = idPostagem;
        this.textoPostagem = textoPostagem;
        this.tituloPostagem = tituloPostagem;
        this.idCriador = idCriador;
        this.dataPostagem = dataPostagem;
        this.idDisciplina = idDisciplina;
        this.anexos = new ArrayList<>();
    }

    public long getIdDisciplina() {
        return idDisciplina;
    }

    public long getIdPostagem() {
        return idPostagem;
    }

    public String getTextoPostagem() {
        return textoPostagem;
    }

    public String getTituloPostagem() {
        return tituloPostagem;
    }

    public long getIdCriador() {
        return idCriador;
    }

    public Date getDataPostagem() {
        return dataPostagem;
    }

    public List<AnexoDto> getAnexos() {
        return anexos;
    }
}
