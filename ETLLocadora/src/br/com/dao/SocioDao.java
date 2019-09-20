package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Conexao;
import br.com.model.Socios;

public class SocioDao {
	
	private Connection connection;

	public SocioDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}
	
	public Socios Obter(Integer cod_soc) {
		String sql = "select * from lote where numeroLotePrestador = '" + cod_soc + "'";		
		Socios socios = new Socios();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				socios.setCod_soc(rs.getInt("cod_soc"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dat_cad"));
				socios.setDat_cad(calendario);
				socios.setCod_tps(rs.getInt("cod_tps"));
				socios.setNom_soc(rs.getString("nom_soc"));
				socios.setSta_soc(rs.getString("sta_soc"));				

			}
			
			stmt.close();
			rs.close();

			return socios;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}

}
	public List<Socios> Listar() {
		String sql = "select * from lote";
		List<Socios> socios = new ArrayList<Socios>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var socio = new Socios();

				socio.setCod_soc(rs.getInt("cod_soc"));
				var calendario = Calendar.getInstance(); 
				calendario.setTime(rs.getDate("dat_cad"));
				socio.setDat_cad(calendario);
				socio.setCod_tps(rs.getInt("cod_tps"));
				socio.setNom_soc(rs.getString("nom_soc"));
				socio.setSta_soc(rs.getString("sta_soc"));								
				
				socios.add(socio);
			}
			stmt.close();
			rs.close();
			
			return socios;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();

		}
}
	}
