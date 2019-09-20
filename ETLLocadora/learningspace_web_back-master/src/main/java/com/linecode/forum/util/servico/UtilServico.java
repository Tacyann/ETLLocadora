package com.linecode.forum.util.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecode.compartilhado.servico.AutorizacaoServico;
import com.linecode.forum.util.dao.UtilDao;
import com.linecode.forum.util.dto.CursoDto;
import com.linecode.forum.util.dto.DisciplinaDto;
import com.linecode.forum.util.dto.TagDto;

@Service
public class UtilServico {

	@Autowired
	private UtilDao utilDao;
	
	@Autowired
	private AutorizacaoServico autorizacaoServico;
	
	/**
	 * Retorna uma lista com todos os cursos
	 * 
	 * @return lista com todos os cursos {@link List<OpcaoDto>}
	 */
	public List<CursoDto> getCursos() {
		return utilDao.getCursos();
	}
	
	/**
	 * Retorna uma  lista com todas  as disciplinas por curso
	 * 
	 * @return lista de disciplinas por curso {@link List<DisciplinaDto>}
	 */
	public List<DisciplinaDto> getDisciplinasPorCurso(long idCurso) {
		autorizacaoServico.isLogado();
		return utilDao.getDisciplinasPorCurso(idCurso);
	}
	
	/**
	 * Consulta as tags de postagem por uma parte da descrição
	 * retorna no máximo 5.
	 * 
	 * @param inicio da descricao da postagem
	 * @return uma instancia {@link List<TagPostagemDto>}
	 */
	public List<TagDto> getTags(String descricao) {
		autorizacaoServico.isLogado();
		return utilDao.getTags(descricao);
	}
}
