package com.linecode.forum.usuario.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linecode.forum.usuario.comando.AtualizarUsuarioCmd;
import com.linecode.forum.usuario.comando.CadastroUsuarioCmd;
import com.linecode.forum.usuario.comando.LoginCmd;
import com.linecode.forum.usuario.dto.PefilUsarioDto;
import com.linecode.forum.usuario.dto.UsuarioDto;
import com.linecode.forum.usuario.servico.UsuarioServico;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	private static final String MSG_ATUALIZACAO = "Atualizado com sucesso.";
	
	@Autowired
	private UsuarioServico servico;

	@Autowired
	private Environment env;

	@PostMapping("/login")
	public UsuarioDto login(@RequestBody LoginCmd login, @ApiIgnore HttpSession sessao) {

		UsuarioDto usuario = servico.getUsuarioLogin(login);
		sessao.setAttribute(env.getProperty("sessao.usuario"), usuario);

		return usuario;
	}

	@PostMapping("/cadastrar")
	public UsuarioDto cadastrar(@RequestBody CadastroUsuarioCmd cmd, @ApiIgnore HttpSession sessao) {

		UsuarioDto usuario = servico.cadastrarUsuario(cmd);
		sessao.setAttribute(env.getProperty("sessao.usuario"), usuario);

		return usuario;
	}

	@PostMapping("/atualizar")
	public String atualizarUsuario(@RequestBody AtualizarUsuarioCmd cmd) {
		servico.atualizarUsuario(cmd);
		return MSG_ATUALIZACAO;
	}
	
	@PostMapping("/atualizar-foto")
	public String atualizarFoto(@RequestBody MultipartFile foto) {
		servico.atualizarFoto(foto);
		return MSG_ATUALIZACAO;
	}
	
	@GetMapping("/foto/{idUsuario}")
	public Resource getFoto(@PathVariable long idUsuario)  {
		return servico.getFoto(idUsuario);
	}
	
	@GetMapping("/perfil/{idUsuario}")
	public PefilUsarioDto getPefilUsuario(@PathVariable long idUsuario) { 
		return servico.getPefilUsuario(idUsuario);
	}
}
