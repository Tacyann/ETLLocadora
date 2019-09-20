package com.linecode.forum.util.controlador;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecode.forum.util.dto.CursoDto;
import com.linecode.forum.util.dto.DisciplinaDto;
import com.linecode.forum.util.dto.TagDto;
import com.linecode.forum.util.servico.UtilServico;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/util")
public class UtilControlador {
	
	@Autowired
	private UtilServico utilServico;

	@ApiOperation(value = "Listar cursos")
	@GetMapping("/cursos")
	public List<CursoDto> getCursos() {
		return utilServico.getCursos();
	}
	
	@GetMapping("/disciplinas/{idCurso}")
	public List<DisciplinaDto> getDisciplinasPorCurso(@PathVariable long idCurso) {
		return utilServico.getDisciplinasPorCurso(idCurso);
	}
	
	@GetMapping("/tags/{descricao}")
	public List<TagDto> getTags(@PathVariable String descricao) {
		return utilServico.getTags(descricao);
	}
}
