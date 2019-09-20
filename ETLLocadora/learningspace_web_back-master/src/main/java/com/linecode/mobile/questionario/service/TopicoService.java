package com.linecode.mobile.questionario.service;

import com.linecode.compartilhado.error.ErroNegocio;
import com.linecode.compartilhado.servico.AutorizacaoServico;
import com.linecode.mobile.questionario.comando.CriarTopicoCmd;
import com.linecode.mobile.questionario.dao.TopicoDao;
import com.linecode.mobile.questionario.dto.TopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class TopicoService {


    private static final String MENSAGEM_TOPICO_VAZIO = "Tópico vazio";
    @Autowired
    private AutorizacaoServico autorizacaoServico;
    @Autowired
    private TopicoDao topicoDao;
    @Autowired
    private Validator validator;
    public List<TopicoDto> getTopicos(){
        autorizacaoServico.isLogado();
        return topicoDao.getTopicos();
    }

    public void cadastrarTopico(CriarTopicoCmd criarTopicoCmd) {
        autorizacaoServico.isAutorizacaoAdministrativa();
        Assert.notNull(criarTopicoCmd, MENSAGEM_TOPICO_VAZIO);
        Set<ConstraintViolation<CriarTopicoCmd>> constraints = validator.validate(criarTopicoCmd);
        if (!constraints.isEmpty()){
            throw new ErroNegocio(constraints.stream().findFirst().get().getMessage());
        }
        topicoDao.cadastrarTopico(criarTopicoCmd);
    }
}
