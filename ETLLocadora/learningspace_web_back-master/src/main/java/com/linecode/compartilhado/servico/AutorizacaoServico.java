package com.linecode.compartilhado.servico;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.linecode.compartilhado.error.ErroNegocio;
import com.linecode.forum.usuario.dto.UsuarioDto;
import com.linecode.forum.usuario.enumerador.TipoUsuarioEnumerador;

@Service
public class AutorizacaoServico {

	private static final String MSG_USUARIO_NAO_AUTENTICADO = "Usuário não autenticado.";
	private static final String MSG_ACESSO_NEGADO = "Acesso negado";
	
    @Autowired
    private Environment env;

    @Autowired
    private HttpSession httpSession;

    public void isAutorizado(TipoUsuarioEnumerador ...tipo) {
        UsuarioDto usuario = this.getUsuarioLogado();

        if (usuario == null) {
            throw new ErroNegocio(MSG_USUARIO_NAO_AUTENTICADO);
        }

        /*
        sem nenhum tipo especificado qualquer usuario pode fazer a requisicao contanto que esteja logado
        se tiver ele checa se o usuario tem a permissao
        */
        boolean hasPermission = tipo.length == 0 ? true : false;

        for (int i = 0; i < tipo.length; i++) {
            if (usuario.getTipoUsuario() == tipo[i]){
                hasPermission = true;
            }
        }

        if (!hasPermission) {
            throw new ErroNegocio(MSG_ACESSO_NEGADO);
        }
    }
    
    public void isLogado() {
    	if (getUsuarioLogado() == null) {
            throw new ErroNegocio(MSG_USUARIO_NAO_AUTENTICADO);
        }
    }
    
    public void isAutorizacaoAdministrativa() {
    	isAutorizado(TipoUsuarioEnumerador.ADMIN, TipoUsuarioEnumerador.MONITOR, TipoUsuarioEnumerador.PROFESSOR);
    }
    
    public UsuarioDto getUsuarioLogado() {
        UsuarioDto usuarioDto = (UsuarioDto) httpSession.getAttribute(env.getProperty("sessao.usuario"));
        return usuarioDto;
    }

}
