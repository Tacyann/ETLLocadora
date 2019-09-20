package com.linecode.forum.postagens.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecode.compartilhado.paginacao.PaginaDto;
import com.linecode.forum.postagens.comando.CriarComentarioCmd;
import com.linecode.forum.postagens.comando.CriarPostagemCmd;
import com.linecode.forum.postagens.comando.ListarPostagensUsuarioCmd;
import com.linecode.forum.postagens.comando.VotacaoComentarioCmd;
import com.linecode.forum.postagens.comando.VotacaoPostagemCmd;
import com.linecode.forum.postagens.dto.AnexoDto;
import com.linecode.forum.postagens.dto.PostagemDto;
import com.linecode.forum.postagens.service.PostagemService;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

    private static final String MSG_VOTACAO_REALIZADA_COM_SUCESSO = "Votagem realizada com sucesso";

    @Autowired
    private PostagemService postagemService;

    @PostMapping(value = "/criar")
    public String criarPostagem(@RequestPart("dados") String json, @RequestPart("anexos") List<MultipartFile> anexos)
            throws JsonParseException, JsonMappingException, IOException {

        CriarPostagemCmd cmd = new ObjectMapper().readValue(json, CriarPostagemCmd.class);
        postagemService.criarPostagem(cmd, anexos);

        return "Postagem criada com sucesso";
    }

    @PostMapping("/listar/usuario")
    public PaginaDto<PostagemDto> listarPostagemUsuario(
            @RequestBody ListarPostagensUsuarioCmd listarPostagensUsuarioCmd,
            @RequestParam(defaultValue = "1") int numPagina, @RequestParam(defaultValue = "15") int tamanhoPagina) {
        return postagemService.listarPostagemUsuario(listarPostagensUsuarioCmd, numPagina, tamanhoPagina);
    }

    @GetMapping("/listar")
    public PaginaDto<PostagemDto> listarPostagens(@RequestParam(defaultValue = "1") int numPagina,
                                                  @RequestParam(defaultValue = "15") int tamanhoPagina, @RequestParam(defaultValue = "") String disciplina) {
        return postagemService.listarPostagens(numPagina, tamanhoPagina, disciplina);
    }

    @DeleteMapping("/deletar/{idPostagem}")
    public String deletarPostagem(@PathVariable long idPostagem) {
        postagemService.deletarPostagem(idPostagem);
        return "Postagem deletada com sucesso";
    }

    @PostMapping("/comentar")
    public String inserirComentario(@RequestBody CriarComentarioCmd cmd) {
        postagemService.criarComentario(cmd);
        return "Comentário efetuado com sucesso";
    }

    @PostMapping("/votar/postagem")
    public String votarPostagem(@RequestBody VotacaoPostagemCmd votacaoPostagemCmd) {
        postagemService.votarPostagem(votacaoPostagemCmd);
        return MSG_VOTACAO_REALIZADA_COM_SUCESSO;
    }

    @PostMapping("/votar/comentario")
    public String votarComentaio(@RequestBody VotacaoComentarioCmd votacaoComentarioCmd) {
        postagemService.votarComentario(votacaoComentarioCmd);
        return MSG_VOTACAO_REALIZADA_COM_SUCESSO;
    }

    @DeleteMapping("/deletar/comentario/{idComentarioPostagem}")
    public String deletarComentario(@PathVariable long idComentarioPostagem) {
        postagemService.deletarComentario(idComentarioPostagem);
        return "Comentário deletado com sucesso";
    }
    
    @GetMapping("/download-anexo/{idAnexo}")
    public void downloadAnexo(@PathVariable long idAnexo, HttpServletResponse response) throws IOException {
    	
    	Path anexo = postagemService.getAnexo(idAnexo);
    	InputStream input = new FileInputStream(anexo.toFile());
    	
    	response.setContentType(Files.probeContentType(anexo));
        response.setHeader("Content-Disposition", "attachment; filename="+anexo.getFileName()); 
         
        IOUtils.copy(input, response.getOutputStream());
        response.flushBuffer();
        input.close();
 
    }

    @PostMapping("/pesquisarAnexo")
    public PaginaDto<AnexoDto> pesquisarAnexo(@RequestParam(defaultValue = "1") int numPagina,
                                              @RequestParam(defaultValue = "15") int tamanhoPagina,
                                              @RequestParam(defaultValue = "") String formato) {
        return postagemService.pesquisarAnexo(formato, numPagina, tamanhoPagina);
    }
}
