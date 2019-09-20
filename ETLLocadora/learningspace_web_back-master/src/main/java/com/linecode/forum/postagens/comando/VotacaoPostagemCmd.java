package com.linecode.forum.postagens.comando;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class VotacaoPostagemCmd {
	
	@Min(value = 1, message = "Id do usuário inválido")
    private long usuario;
	
	@Min(value = 1, message = "Id postagem inválido")
    private long postagem;
    
	@Min(value = 1, message = "Voto inválido")
	@Max(value = 5, message = "Voto inválido")
	private int voto;

    public long getUsuario() {
        return usuario;
    }

    public long getPostagem() {
        return postagem;
    }

    public int getVoto() {
        return voto;
    }
    
    public void setUsuario(long id) {
    	usuario = id;
    }
}
