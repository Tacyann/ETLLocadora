package com.linecode.mobile.questionario.dao;

import com.linecode.mobile.questionario.comando.CriarTopicoCmd;
import com.linecode.mobile.questionario.dto.TopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PropertySource("com/linecode/mobile/questionario/questionarioDao.xml")
@Repository
public class TopicoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;

    @Transactional
    public void cadastrarTopico(CriarTopicoCmd criarTopicoCmd) {
        jdbcTemplate.update(env.getProperty("cadastrarTopico"), criarTopicoCmd.getNome());
    }

    @Transactional(readOnly = true)
    public List<TopicoDto> getTopicos(){
        return jdbcTemplate.query(env.getProperty("getTopicos"), QuestionarioMapeadorLinha.TOPICO_MAPEADOR_LINHA);
    }
}
