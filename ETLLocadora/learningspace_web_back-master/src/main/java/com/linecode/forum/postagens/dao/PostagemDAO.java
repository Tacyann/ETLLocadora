package com.linecode.forum.postagens.dao;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.paginacao.ControladorPagina;
import com.linecode.compartilhado.paginacao.PaginaDto;
import com.linecode.forum.postagens.comando.CriarComentarioCmd;
import com.linecode.forum.postagens.comando.CriarPostagemCmd;
import com.linecode.forum.postagens.comando.ListarPostagensUsuarioCmd;
import com.linecode.forum.postagens.comando.VotacaoComentarioCmd;
import com.linecode.forum.postagens.comando.VotacaoPostagemCmd;
import com.linecode.forum.postagens.dto.AnexoDto;
import com.linecode.forum.postagens.dto.CriarAnexosDto;
import com.linecode.forum.postagens.dto.PostagemDto;

@PropertySource("com/linecode/forum/postagens/postagemDao.xml")
@Repository
public class PostagemDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Environment env;

	@Transactional
	public long criarPostagem(CriarPostagemCmd cmd) {
		return jdbcTemplate.queryForObject(env.getProperty("criarPostagem"), Long.class, cmd.getTextoPostagem(),
				cmd.getTituloPostagem(), cmd.getIdUsuario(), cmd.getIdDisciplina());
	}

	@Transactional(readOnly = true)
	public PaginaDto<PostagemDto> listarPostagemUsuario(ListarPostagensUsuarioCmd listarPostagensUsuarioCmd,
			int numPagina, int tamanhoPagina) {
		ControladorPagina<PostagemDto> controladorPagina = new ControladorPagina<>();
		PaginaDto<PostagemDto> paginaDto = controladorPagina.carregarPagina(jdbcTemplate,
				env.getProperty("quantidadePostagemUsuario"), env.getProperty("listarPostagemUsuario"),
				new Object[] { listarPostagensUsuarioCmd.getUsuario() }, numPagina, tamanhoPagina,
				PostagemMapeadorLinha.POSTAGEM_MAPEADOR_LINHA);
		for (PostagemDto postagemDto : paginaDto.getItens()) {
			postagemDto.getAnexos().addAll(getAnexosPostagem(postagemDto.getIdPostagem()));
		}

		return paginaDto;
	}

	@Transactional
	public void deletarPostagem(long idPostagem) {
		jdbcTemplate.update(env.getProperty("deletarPostagem"), idPostagem);
	}

	@Transactional
	public void votarPostagem(VotacaoPostagemCmd votacaoPostagemDTO) {
		jdbcTemplate.update(env.getProperty("votarPostagem"), votacaoPostagemDTO.getPostagem(),
				votacaoPostagemDTO.getUsuario(), votacaoPostagemDTO.getVoto());
	}

	@Transactional
	public void atualizarVotoPostagem(VotacaoPostagemCmd votacaoPostagemDTO) {
		jdbcTemplate.update(env.getProperty("atualizarVotoPostagem"), votacaoPostagemDTO.getVoto(),
				votacaoPostagemDTO.getUsuario(), votacaoPostagemDTO.getPostagem()

		);
	}

	@Transactional
	public void criarComentario(CriarComentarioCmd cmd) {
		jdbcTemplate.update(env.getProperty("criarComentario"), cmd.getPostagem(), cmd.getUsuario(),
				cmd.getComentario());
	}

	@Transactional
	public void votarComentario(VotacaoComentarioCmd votacaoComentarioDTO) {
		jdbcTemplate.update(env.getProperty("votarComentario"), votacaoComentarioDTO.getUsuario(),
				votacaoComentarioDTO.getComentario(), votacaoComentarioDTO.getVoto());

	}

	@Transactional
	public void atualizarVotoComentario(VotacaoComentarioCmd votacaoComentarioDTO) {
		jdbcTemplate.update(env.getProperty("atualizarVotoComentario"), votacaoComentarioDTO.getVoto(),
				votacaoComentarioDTO.getUsuario(), votacaoComentarioDTO.getComentario());
	}

	@Transactional
	public void criarAnexo(CriarAnexosDto anexosDTO) {
		jdbcTemplate.update(env.getProperty("inserirAnexo"), anexosDTO.getIdPostagem(), anexosDTO.getNomeArquivo());
	}

	@Transactional(readOnly = true)
	public long getIdCriadorComentario(long idComentario) {
		return jdbcTemplate.queryForObject(env.getProperty("getIdCriadorComentario"), Long.class, idComentario);
	}

	@Transactional
	public void deletarComentario(long idComentarioPostagem) {
		jdbcTemplate.update(env.getProperty("deletarComentario"), idComentarioPostagem);
	}

	@Transactional(readOnly = true)
	public List<AnexoDto> getAnexosPostagem(long idAnexo) {
		return jdbcTemplate.query(env.getProperty("getAnexosPostagem"), PostagemMapeadorLinha.ANEXO_DTO_MAPEADOR_LINHA,
				idAnexo);
	}

	@Transactional(readOnly = true)
	public PaginaDto<PostagemDto> listarPostagens(int numPagina, int tamanhoPagina) {
		ControladorPagina<PostagemDto> controladorPagina = new ControladorPagina<>();
		PaginaDto<PostagemDto> pagina = controladorPagina.carregarPagina(jdbcTemplate,
				env.getProperty("quantidadePostagens"), env.getProperty("listarPostagens"), new Object[] {}, numPagina,
				tamanhoPagina, PostagemMapeadorLinha.POSTAGEM_MAPEADOR_LINHA);
		for (PostagemDto postagemDto : pagina.getItens()) {
			postagemDto.getAnexos().addAll(getAnexosPostagem(postagemDto.getIdPostagem()));
		}
		return pagina;
	}

	@Transactional(readOnly = true)
	public PaginaDto<PostagemDto> listarPostagensDisciplina(int numPagina, int tamanhoPagina, String disciplina) {
		ControladorPagina<PostagemDto> controladorPagina = new ControladorPagina<>();
		String likeStr = "%" + disciplina + "%";
		PaginaDto<PostagemDto> pagina = controladorPagina.carregarPagina(jdbcTemplate,
				env.getProperty("quantidadePostagens"), env.getProperty("listarPostagensDisciplina"), new Object[] {},
				new Object[] { likeStr }, numPagina, tamanhoPagina, PostagemMapeadorLinha.POSTAGEM_MAPEADOR_LINHA);
		return pagina;
	}

	@Transactional
	public void inserirTagPostagem(long idPostagem, long idTag) {
		jdbcTemplate.update(env.getProperty("inserirTagPostagem"), idPostagem, idTag);
	}

	@Transactional
	public void deletarTagsPostagem(long idPostagem) {
		jdbcTemplate.update(env.getProperty("deletarTagsPostagem"), idPostagem);
	}

	@Transactional(readOnly = true)
	public PaginaDto<AnexoDto> pesquisarAnexo(String formato, int numPagina, int tamanhoPagina) {
		ControladorPagina<AnexoDto> controladorPagina = new ControladorPagina<>();
		return controladorPagina.carregarPagina(jdbcTemplate, env.getProperty("quantidadeAnexo"),
				env.getProperty("pesquisarAnexo"), new Object[] {}, new Object[] { "%." + formato }, numPagina,
				tamanhoPagina, PostagemMapeadorLinha.ANEXO_DTO_MAPEADOR_LINHA);
	}

	@Transactional(readOnly = true)
	public String getCaminhoAnexo(long idAnexo) {
		return jdbcTemplate.queryForObject(env.getProperty("getCaminhoAnexo"), String.class, File.separator, idAnexo);
	}
}
