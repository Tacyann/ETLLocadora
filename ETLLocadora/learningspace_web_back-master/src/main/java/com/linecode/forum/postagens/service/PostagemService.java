package com.linecode.forum.postagens.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.linecode.forum.postagens.dto.AnexoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.linecode.compartilhado.error.ErroAplicacao;
import com.linecode.compartilhado.error.ErroNegocio;
import com.linecode.compartilhado.paginacao.PaginaDto;
import com.linecode.compartilhado.servico.ArquivoServico;
import com.linecode.compartilhado.servico.AutorizacaoServico;
import com.linecode.forum.postagens.comando.CriarComentarioCmd;
import com.linecode.forum.postagens.comando.CriarPostagemCmd;
import com.linecode.forum.postagens.comando.ListarPostagensUsuarioCmd;
import com.linecode.forum.postagens.comando.VotacaoComentarioCmd;
import com.linecode.forum.postagens.comando.VotacaoPostagemCmd;
import com.linecode.forum.postagens.dao.PostagemDAO;
import com.linecode.forum.postagens.dto.CriarAnexosDto;
import com.linecode.forum.postagens.dto.PostagemDto;

@Service
public class PostagemService {

	private static final String MENSAGEM_VOTACAO_VAZIA = "Votacao vazia";

	@Autowired
	private PostagemDAO postagemDAO;

	@Autowired
	private ArquivoServico arquivoServico;

	@Autowired
	private AutorizacaoServico autorizacaoServico;

	@Autowired
	private Validator validator;

	/**
	 * Cria uma pastagem com seus anexos
	 *
	 * @param cmd - dados da postagem {@link CriarPostagemCmd}
	 * @throws {@link ErroNegocio} para qualquer regra de negocio quebrada
	 */
	@Transactional
	public void criarPostagem(CriarPostagemCmd cmd, List<MultipartFile> anexos) {

		autorizacaoServico.isAutorizacaoAdministrativa();
		Assert.notNull(cmd, "Postagem vazia");
		cmd.setIdUsuario(autorizacaoServico.getUsuarioLogado().getId());
		Set<ConstraintViolation<CriarPostagemCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {

			long idPostagem = postagemDAO.criarPostagem(cmd);

			if (cmd.getTags() != null) {
				cmd.getTags().forEach(idTag -> postagemDAO.inserirTagPostagem(idPostagem, idTag));
			}

			if (anexos != null) {
				salvarAnexos(anexos, idPostagem);
			}

		} else {
			throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	public PaginaDto<PostagemDto> listarPostagemUsuario(ListarPostagensUsuarioCmd listarPostagensUsuarioCmd,
			int numPagina, int tamanhoPagina) {
		autorizacaoServico.isLogado();
		return postagemDAO.listarPostagemUsuario(listarPostagensUsuarioCmd, numPagina, tamanhoPagina);
	}

	/**
	 * Delete uma postagem e seus anexos pelo ID da postagem
	 *
	 * @param deletarPostagemCmd - dados da postagem {@link DeletarPostagemCmd}
	 */
	@Transactional
	public void deletarPostagem(long idPostagem) {

		autorizacaoServico.isAutorizacaoAdministrativa();

		postagemDAO.deletarPostagem(idPostagem);
		arquivoServico.deletarPasta(String.valueOf(idPostagem));
	}

	/**
	 * Efetua uma votação de uma postagem
	 *
	 * @param cmd - Dados da votação {@link VotacaoPostagemCmd}
	 * @throws {@link ErroNegocio} para qualquer exeção de negócio.
	 */
	public void votarPostagem(VotacaoPostagemCmd cmd) {

		autorizacaoServico.isLogado();

		Assert.notNull(cmd, MENSAGEM_VOTACAO_VAZIA);
		cmd.setUsuario(autorizacaoServico.getUsuarioLogado().getId());
		Set<ConstraintViolation<VotacaoPostagemCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {
			try {
				postagemDAO.votarPostagem(cmd);
			} catch (DuplicateKeyException e) {
				postagemDAO.atualizarVotoPostagem(cmd);
			}
		} else {
			throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	/**
	 * Efetua o comentário de uma postagem
	 *
	 * @param dados do comentário {@link CriarComentarioCmd}
	 */
	public void criarComentario(CriarComentarioCmd cmd) {

		autorizacaoServico.isLogado();

		Assert.notNull(cmd, MENSAGEM_VOTACAO_VAZIA);
		cmd.setUsuario(autorizacaoServico.getUsuarioLogado().getId());
		Set<ConstraintViolation<CriarComentarioCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {
			postagemDAO.criarComentario(cmd);
		} else {
			throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	/**
	 * Efetua uma votação de um comentário.
	 *
	 * @param cmd - Dados da votação {@link VotacaoPostagemCmd}
	 * @throws {@link ErroNegocio} para qualquer exeção de negócio.
	 */
	public void votarComentario(VotacaoComentarioCmd cmd) {

		autorizacaoServico.isLogado();

		Assert.notNull(cmd, MENSAGEM_VOTACAO_VAZIA);
		cmd.setUsuario(autorizacaoServico.getUsuarioLogado().getId());
		Set<ConstraintViolation<VotacaoComentarioCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {
			try {
				postagemDAO.votarComentario(cmd);
			} catch (DuplicateKeyException e) {
				postagemDAO.atualizarVotoComentario(cmd);
			}
		} else {
			throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	/**
	 * Deleta um comentário (Só é possível deletar pelo criador do comentário)
	 *
	 * @para idComentario - id do comentario
	 */
	public void deletarComentario(long idComentarioPostagem) {

		autorizacaoServico.isLogado();

		long idCriador = postagemDAO.getIdCriadorComentario(idComentarioPostagem);
		long idUsuarioLogado = autorizacaoServico.getUsuarioLogado().getId();

		if (idCriador == idUsuarioLogado) {
			postagemDAO.deletarComentario(idComentarioPostagem);
		} else {
			throw new ErroNegocio("Você não tem permissão para deletar o comentário.");
		}
	}

	/**
	 * Salva os anexos de uma postagem. É criada uma pasta com o ID da postagem e
	 * seus anexos são salvos nesta pasta.
	 *
	 * @param arquivos   {@link List<MultipartFile>}
	 * @param idPostagem {@link Long}
	 * @throws {@link ErroAplicacao} para qualquer erro de IO.
	 */
	private void salvarAnexos(List<MultipartFile> arquivos, long idPostagem) {
		try {

			if (arquivos != null) {

				String idPostagemStr = Long.toString(idPostagem);

				for (int i = 0; i < arquivos.size(); i++) {

					MultipartFile arquivo = arquivos.get(i);
					StringBuilder nomeArquivo = new StringBuilder();
					nomeArquivo.append(idPostagemStr);
					nomeArquivo.append(File.separator);
					nomeArquivo.append(arquivo.getOriginalFilename());

					CriarAnexosDto anexo = new CriarAnexosDto(idPostagem, arquivo.getOriginalFilename());

					postagemDAO.criarAnexo(anexo);
					arquivoServico.salvarArquivo(arquivo.getBytes(), nomeArquivo.toString());

				}
			}
		} catch (Exception e) {
			throw new ErroAplicacao("Erro ao salvar o arquivo", e);
		}
	}

	/**
	 * Retorna o arquivo de um anexo apartir do id
	 * 
	 * @param id do anexo
	 * @return uma instancia de {@link Path}
	 */
	public Path getAnexo(long idAnexo) {

		String caminhoArquivo = postagemDAO.getCaminhoAnexo(idAnexo);

		if (StringUtils.isEmpty(caminhoArquivo)) {
			throw new ErroAplicacao("Erro ao carregar o anexo", new Exception("Arquivo não encontrado"));
		}
		
		return arquivoServico.getArquivo(caminhoArquivo);
	}

	public PaginaDto<PostagemDto> listarPostagens(int numPagina, int tamanhoPagina, String disciplina) {
		autorizacaoServico.isLogado();
		if (disciplina.isEmpty() || disciplina == null) {
			return postagemDAO.listarPostagens(numPagina, tamanhoPagina);
		}
		return postagemDAO.listarPostagensDisciplina(numPagina, tamanhoPagina, disciplina);
	}

	public PaginaDto<AnexoDto> pesquisarAnexo(String formato, int numPagina, int tamanhoPagina) {
		autorizacaoServico.isLogado();
		Assert.notNull(formato, "Formato inválido");
		if (formato.isEmpty()) {
			throw new ErroNegocio("Formato vazio");
		}
		return postagemDAO.pesquisarAnexo(formato, numPagina, tamanhoPagina);

	}
}
