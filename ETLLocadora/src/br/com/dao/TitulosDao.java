package br.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Conexao;
import br.com.model.Titulos;

public class TitulosDao {
		
		private Connection connection;

		public TitulosDao() {
			new Conexao();
			this.connection = Conexao.getConexao();
		}
		
		public Titulos Obter(Integer cod_soc) {
			String sql = "select * from lote where numeroLotePrestador = '" + cod_soc + "'";		
			Titulos titulo = new Titulos();

			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					titulo.setCod_tit(rs.getInt("cod_tit"));
					titulo.setCla_tit(rs.getString("cla_tit"));
					titulo.setCod_art(rs.getInt("cod_art"));
					titulo.setCod_grav(rs.getInt("cod_grav"));
					titulo.setDsc_tit(rs.getString("dsc_tit"));
					titulo.setQtd_cop(rs.getInt("qtd_cop"));
					titulo.setTpo_tit(rs.getString("tpo_tit"));
					
					var calendario = Calendar.getInstance(); 
					calendario.setTime(rs.getDate("dat_lanc"));
					titulo.setDat_lanc(calendario);
				}
				
				stmt.close();
				rs.close();

				return titulo;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}finally {
				Conexao.FecharConexao();
			}

	}
		public List<Titulos> Listar() {
			String sql = "select * from lote";
			List<Titulos> titulos = new ArrayList<Titulos>();

			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					var titulo = new Titulos();

					titulo.setCod_tit(rs.getInt("cod_tit"));
					titulo.setCla_tit(rs.getString("cla_tit"));
					titulo.setCod_art(rs.getInt("cod_art"));
					titulo.setCod_grav(rs.getInt("cod_grav"));
					titulo.setDsc_tit(rs.getString("dsc_tit"));
					titulo.setQtd_cop(rs.getInt("qtd_cop"));
					titulo.setTpo_tit(rs.getString("tpo_tit"));
					var calendario = Calendar.getInstance(); 
					calendario.setTime(rs.getDate("dat_lanc"));
					titulo.setDat_lanc(calendario);
					
					titulos.add(titulo);
				}
				stmt.close();
				rs.close();
				
				return titulos;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}finally {
				Conexao.FecharConexao();
			}
		}
}



