package com.linecode.forum.postagens.comando;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class VotacaoComentarioCmd {
	
	@Min(value = 1, message = "Id usuário inválido")
    private long usuario;
	
	@Min(value = 1, message = "Id usuário comentário")
    private long comentario;
	
	@Min(value = 1, message = "Voto inválido")
	@Max(value = 5, message = "Voto inválido")
    private int voto;

    public long getUsuario() {
        return usuario;
    }

    public long getComentario() {
        return comentario;
    }

    public int getVoto() {
        return voto;
    }
    
    public void setUsuario(long id) {
    	usuario = id;
    }
}
