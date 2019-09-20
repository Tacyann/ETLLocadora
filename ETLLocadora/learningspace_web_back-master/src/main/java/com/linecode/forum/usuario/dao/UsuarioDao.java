package com.linecode.forum.usuario.dao;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.forum.usuario.comando.CadastroUsuarioCmd;
import com.linecode.forum.usuario.comando.CursoUsuarioCmd;
import com.linecode.forum.usuario.comando.LoginCmd;
import com.linecode.forum.usuario.dto.PefilUsarioDto;
import com.linecode.forum.usuario.dto.UsuarioDto;
import com.linecode.forum.usuario.enumerador.TipoUsuarioEnumerador;

@PropertySource("com/linecode/forum/usuario/usuaioDao.xml")
@Repository
public class UsuarioDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private Environment env;

	@Transactional(readOnly = true)
	public UsuarioDto getUsuarioLogin(LoginCmd login) {
		return jdbc.query(env.getProperty("getUsuarioLogin"), UsuarioMapeadorLinha.USUARIO_DTO_MAPEADOR_LINHA,
				login.getMatricula(), login.getSenha());
	}

	@Transactional(readOnly = true)
	public UsuarioDto getUsuarioPorMatricula(long matricula) {
		return jdbc.query(env.getProperty("getUsuarioPorMatricula"), UsuarioMapeadorLinha.USUARIO_DTO_MAPEADOR_LINHA,
				matricula);
	}

	@Transactional
	public void cadastrarUsuario(CadastroUsuarioCmd cmd) throws PSQLException {
		jdbc.update(env.getProperty("cadastrarUsuario"), cmd.getMatricula(), cmd.getNome(), cmd.getSenha(),
				cmd.getEmail(), TipoUsuarioEnumerador.ALUNO.toString(), cmd.getCurso(), cmd.getPeriodo(),
				cmd.getTelefone());
	}

	@Transactional
	public void inserirCursoUsuario(CursoUsuarioCmd cmd, long idUsuario) {
		jdbc.update(env.getProperty("inserirCursoUsario"), cmd.getNome(), cmd.getInstituicao(), idUsuario);
	}

	@Transactional
	public void atualizarInformacaoContatoUsuario(String email, String telefone, long idUsuario) {
		jdbc.update(env.getProperty("atualizarInformacaoContatoUsuario"), email, telefone, idUsuario);
	}

	@Transactional
	public void deletarCursosUsuario(long idUsuario) {
		jdbc.update(env.getProperty("deletarCursosUsuario"), idUsuario);
	}

	@Transactional
	public void atualizarFoto(String nomeFoto, long idUsuario) {
		jdbc.update(env.getProperty("atualizarFoto"), nomeFoto, idUsuario);
	}

	@Transactional(readOnly = true)
	public String getFoto(long idUsuario) {
		return jdbc.queryForObject(env.getProperty("getFoto"), String.class, idUsuario);
	}

	@Transactional(readOnly = true)
	public PefilUsarioDto getPefilUsuario(long idUsuario) {
		return jdbc.query(env.getProperty("getDadosPerfilUsario"), UsuarioMapeadorLinha.PERFIL_USUARIO_MAPEADOR_LINHA,
				idUsuario);
	}
}
