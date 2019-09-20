package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Conexao;
import br.com.model.Locacoes;

public class LocacoesDao {

	private Connection connection;

	public LocacoesDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}
	
	public Locacoes Obter(Integer cod_soc) {
		String sql = "select * from lote where numeroLotePrestador = '" + cod_soc + "'";		
		Locacoes locacao = new Locacoes();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				locacao.setCod_soc(rs.getInt("cod_soc"));
				var calendario1 = Calendar.getInstance(); 
				calendario1.setTime(rs.getDate("dat_loc"));
				locacao.setDat_loc(calendario1);
				locacao.setSta_pgto(rs.getString("sta_pgto"));
				locacao.setVal_loc(rs.getInt("val_loc"));
				var calendario2 = Calendar.getInstance(); 
				calendario2.setTime(rs.getDate("dat_venc"));
				locacao.setDat_venc(calendario2);

			}
			
			stmt.close();
			rs.close();

			return locacao;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Locacoes> Listar() {
		String sql = "select * from lote";
		List<Locacoes> locacoes = new ArrayList<Locacoes>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var locacao = new Locacoes();

				locacao.setCod_soc(rs.getInt("cod_soc"));
				var calendario1 = Calendar.getInstance(); 
				calendario1.setTime(rs.getDate("dat_loc"));
				locacao.setDat_loc((calendario1));
				locacao.setVal_loc(rs.getInt("val_loc"));
				var calendario2 = Calendar.getInstance(); 
				calendario2.setTime(rs.getDate("dat_venc"));
				locacao.setDat_loc((calendario2));
				locacao.setSta_pgto(rs.getString("sta_pgto"));
				var calendario3 = Calendar.getInstance(); 
				calendario3.setTime(rs.getDate("dat_pgto"));
				locacao.setDat_loc((calendario3));
						
				
				locacoes.add(locacao);
			}
			stmt.close();
			rs.close();
			
			return locacoes;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
}
