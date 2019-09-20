package com.linecode.compartilhado.paginacao;

import com.linecode.compartilhado.paginacao.PaginaDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ControladorPagina<T> {

    public PaginaDto<T> carregarPagina(final JdbcTemplate jdbcTemplate,
                                       final String sqlCount,
                                       final String sqlList,
                                       final Object args[],
                                       final int numPagina,
                                       final int tamanhoPagina,
                                       final RowMapper<T> rowMapper) {
        return carregarPagina(jdbcTemplate, sqlCount, sqlList, args, args, numPagina, tamanhoPagina, rowMapper);
    }

    public PaginaDto<T> carregarPagina(
            final JdbcTemplate jdbcTemplate,
            final String sqlCount,
            final String sqlList,
            final Object argsSqlCount[],
            final Object argsSqlList[],
            final int numPagina,
            final int tamanhoPagina,
            final RowMapper<T> rowMapper) {

        final int totalLinhas = jdbcTemplate.queryForObject(sqlCount, argsSqlCount, Integer.class);

        int totalPaginas = totalLinhas / tamanhoPagina;
        if (totalLinhas > tamanhoPagina * totalPaginas) {
            totalPaginas++;
        }

        PaginaDto<T> paginaDto = new PaginaDto<>(numPagina, tamanhoPagina);
        paginaDto.setNumPagina(numPagina);
        paginaDto.setQntItens(tamanhoPagina);

        int primeiraLinha = (numPagina - 1) * tamanhoPagina;

        jdbcTemplate.query(
                sqlList,
                argsSqlList,
                new ResultSetExtractor() {
                    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                        final List pageItems = paginaDto.getItens();
                        int linha = 0;
                        while (rs.next() && linha < primeiraLinha + tamanhoPagina) {
                            if (linha >= primeiraLinha) {
                                pageItems.add(rowMapper.mapRow(rs, linha));
                            }
                            linha++;
                        }
                        return paginaDto;
                    }
                });
        return paginaDto;
    }
}