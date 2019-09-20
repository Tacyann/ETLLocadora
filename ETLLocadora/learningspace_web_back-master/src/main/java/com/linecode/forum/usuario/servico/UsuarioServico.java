package com.linecode.forum.usuario.servico;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.linecode.compartilhado.error.ErroAplicacao;
import com.linecode.compartilhado.error.ErroNegocio;
import com.linecode.compartilhado.servico.ArquivoServico;
import com.linecode.compartilhado.servico.AutorizacaoServico;
import com.linecode.forum.usuario.comando.AtualizarUsuarioCmd;
import com.linecode.forum.usuario.comando.CadastroUsuarioCmd;
import com.linecode.forum.usuario.comando.LoginCmd;
import com.linecode.forum.usuario.dao.UsuarioDao;
import com.linecode.forum.usuario.dto.PefilUsarioDto;
import com.linecode.forum.usuario.dto.UsuarioDto;

@Service
public class UsuarioServico {
	
	private static final String ERRO_OBJTO_NULO = "É necessário informar os parâmetros";
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AutorizacaoServico autorizacaoServico;
	
	@Autowired
	private ArquivoServico arquivoServico;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ApplicationContext contexto;
	
	/**
	 * Retorna os dados de um usuário por login
	 * 
	 * @param objeto de login {@link LoginCmd}
	 * @return dados d o usuario {@link UsuarioDto}
	 */
	public UsuarioDto getUsuarioLogin(LoginCmd login) {
		
		Assert.notNull(login, ERRO_OBJTO_NULO);
		Set<ConstraintViolation<LoginCmd>> violacoes = validator.validate(login);
		
		if (violacoes.isEmpty()) {
			return usuarioDao.getUsuarioLogin(login);
		}
		
		throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
	}
	
	/**
	 * Efetua o cadastro de um usuário
	 * @param dados do cadastro {@link CadastroUsuarioCmd}
	 */
	public UsuarioDto cadastrarUsuario(CadastroUsuarioCmd cmd) {
		
		Assert.notNull(cmd, ERRO_OBJTO_NULO);
		Set<ConstraintViolation<CadastroUsuarioCmd>> violacoes = validator.validate(cmd);
		
		if (!violacoes.isEmpty()) {
			throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
		}
		
		try {
			usuarioDao.cadastrarUsuario(cmd);
		}catch (Exception e) {
			if (e.getMessage().toUpperCase().contains("UK_MATRICULA_USUARIO")) {
				throw new ErroNegocio("Já existe um cadastro para a matrícula informada cadastro.");
			}
			
			throw new ErroAplicacao("Erro ao cadastrar usuário", e);
		}
		return usuarioDao.getUsuarioPorMatricula(cmd.getMatricula());
	}
	
	/**
	 * Atualiza as informações do usuario como informações de contato,
	 * cursos e areas de conhecimento
	 * 
	 * @param uma instancia de {@link AtualizarUsuarioCmd}
	 */
	@Transactional
	public void atualizarUsuario(AtualizarUsuarioCmd cmd) {
		
		autorizacaoServico.isLogado();
		
		Assert.notNull(cmd, ERRO_OBJTO_NULO);
		Set<ConstraintViolation<AtualizarUsuarioCmd>> violacoes = validator.validate(cmd);
		
		if (violacoes.isEmpty()) {
			
			long idUsuario = autorizacaoServico.getUsuarioLogado().getId();
			usuarioDao.atualizarInformacaoContatoUsuario(cmd.getEmail(), cmd.getTelefone(), idUsuario);
			usuarioDao.deletarCursosUsuario(idUsuario);
			cmd.getCursos().forEach(curso -> usuarioDao.inserirCursoUsuario(curso, idUsuario));
			
		} else {
			throw new ErroNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}
	
	/**
	 * Atualiza a foto de perfil do usuario
	 * 
	 * @param foto {@link MultipartFile}
	 */
	/*TODO CRIAR PASTA PUBLICA E METODOS NO ARQUIVO SERVIÇO
	 * PARA SALVAR E DELETAR NA PASTA PUBLICA E AO RETORNAR OS DADOS DO USUARIO
	 * RETORNAR O CAMPO FOTO PARA SER FEITO A CHAMADA DIRETA OU SALVAR TODAS AS FOTOS COM PADRÃO ID.JPG (TESTAR MANUAL MUDANDO EXTENSÕES DE FOTOS)
	 */
	public void atualizarFoto(MultipartFile foto) {
		
		autorizacaoServico.isLogado();
		Assert.notNull(foto, ERRO_OBJTO_NULO);
		
		if (isFotoValida(foto)) {
			
			long idUsuario = autorizacaoServico.getUsuarioLogado().getId();
			String nomeFinal = String.valueOf(idUsuario).concat(foto.getOriginalFilename());
			StringBuilder caminhoFoto = new StringBuilder();
			
			caminhoFoto.append(env.getProperty("pasta.fotos.usuarios"));
			caminhoFoto.append(File.separator);
			caminhoFoto.append(nomeFinal);
			
			try {
				
				usuarioDao.atualizarFoto(nomeFinal, idUsuario);
				arquivoServico.deletarArquivo(caminhoFoto.toString());
				arquivoServico.salvarArquivo(foto.getBytes(), caminhoFoto.toString());
				
			} catch (IOException e) {
				throw new ErroAplicacao("Erro ao atualizar foto", e);
			}
		} else {
			throw new ErroNegocio("Informe um arquivo de extensão png, jpg, jpeg ou gif");
		}
		
	}
	
	/**
	 * Retorna o {@link Resource} da foto de um usuario
	 * @param id usuario {@link long}
	 * @return {@link Resource} da foto do usuario
	 */
	public Resource getFoto(long idUsuario) {
		
		StringBuilder foto = new StringBuilder();
		foto.append(env.getProperty("pasta.upload"));
		foto.append(File.separator);
		foto.append(usuarioDao.getFoto(idUsuario));
		
		return contexto.getResource("classpath:target/fotos_usuarios/1.jpg");
	}
	
	/**
	 * Verifica se um instancia de {@link MultipartFile} é um arquivo de foto
	 * de extensao [png,jpg,jpeg,gif]
	 * 
	 * retorna {@link true} caso seja.
	 */
	private boolean isFotoValida(MultipartFile foto) {
		List<String> extensoes = Arrays.asList("PNG","JPG", "JPEG", "GIF");
		return extensoes.contains(arquivoServico.getExtensao(foto).toUpperCase());
	}
	
	/**
	 * Retorna os dados do perfil do usuario
	 * @param idUsuario {@link long}
	 * @return uma instancia de {@link PefilUsarioDto}
	 */
	public PefilUsarioDto getPefilUsuario(long idUsuario) {
		
		PefilUsarioDto perfil = usuarioDao.getPefilUsuario(idUsuario);
		StringBuilder caminhoFoto = new StringBuilder();
		
		caminhoFoto.append(env.getProperty("pasta.fotos.usuarios"));
		caminhoFoto.append(File.separator);
		caminhoFoto.append(perfil.getFotoBase64());
		
		perfil.setFotoBase64(arquivoServico.getBase64(caminhoFoto.toString()));
		
		return perfil;
	}
}
