package com.linecode.mobile.questionario.controller;

import com.linecode.mobile.questionario.comando.CriarTopicoCmd;
import com.linecode.mobile.questionario.dto.TopicoDto;
import com.linecode.mobile.questionario.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping("/listar")
    public List<TopicoDto> getTopicos(){
        return topicoService.getTopicos();
    }

    @PostMapping("/cadastrar")
    public String cadastrarTopico(@RequestBody CriarTopicoCmd criarTopicoCmd){
        topicoService.cadastrarTopico(criarTopicoCmd);
        return "Topico cadastrado com sucesso";
    }

}
