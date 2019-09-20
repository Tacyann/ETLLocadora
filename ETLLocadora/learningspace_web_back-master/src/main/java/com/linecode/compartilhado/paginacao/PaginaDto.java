package com.linecode.compartilhado.paginacao;

import java.util.ArrayList;
import java.util.List;

public class PaginaDto<T> {
    private int numPagina;
    private int qntItens;
    private List<T> itens;

    public PaginaDto(int numPagina, int qntPaginas) {
        this.numPagina = numPagina;
        this.qntItens = qntPaginas;
        this.itens = new ArrayList<T>();
    }

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    public int getQntItens() {
        return qntItens;
    }

    public void setQntItens(int qntItens) {
        this.qntItens = qntItens;
    }

    public List<T> getItens() {
        return itens;
    }

    public void setItens(List<T> itens) {
        this.itens = itens;
    }
}
