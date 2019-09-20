package com.linecode.forum.util.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.forum.util.dto.CursoDto;
import com.linecode.forum.util.dto.DisciplinaDto;
import com.linecode.forum.util.dto.TagDto;

@PropertySource("com/linecode/forum/util/utilDao.xml")
@Repository
public class UtilDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private Environment env;

	@Transactional(readOnly = true)
	public List<CursoDto> getCursos() {
		return jdbc.query(env.getProperty("getCursos"), UtilMapeadorLinha.CURSO_DTO_MAPEADOR_LINHA);
	}

	@Transactional(readOnly = true)
	public List<DisciplinaDto> getDisciplinasPorCurso(long idCurso) {
		return jdbc.query(env.getProperty("getDisciplinasPorCurso"), UtilMapeadorLinha.DISCIPLINA_DTO_MAPEADOR_LINHA,
				idCurso);
	}

	@Transactional(readOnly = true)
	public List<TagDto> getTags(String descricao) {
		return jdbc.query(env.getProperty("getTags"), UtilMapeadorLinha.TAG_POSTAGEM_DTO_MAPEADOR_LINHA,
				descricao.toUpperCase().concat("%"));
	}
}
