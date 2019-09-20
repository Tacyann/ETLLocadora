package com.linecode.forum.postagens.comando;

import javax.validation.constraints.Min;

public class ListarPostagensUsuarioCmd {

    @Min(value = 1, message = "Id usuario invalido")
    private  long usuario;

    public long getUsuario() {
        return usuario;
    }
}
