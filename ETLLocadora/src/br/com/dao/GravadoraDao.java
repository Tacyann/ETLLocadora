package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Conexao;
import br.com.model.Gravadoras;

public class GravadoraDao {
	

	private Connection connection;

	public GravadoraDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}
	
	public Gravadoras Obter(String nome) {
		String sql = "select * from Gravadoras where nome = '" + nome + "'";	
		Gravadoras gravadora = new Gravadoras();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				
				gravadora.setCod_grav(rs.getInt("cod_grav"));
				gravadora.setNac_bras(rs.getString("nac_bras"));
				gravadora.setNom_grav(rs.getString("nom_grav"));
				gravadora.setUf_grav(rs.getString("uf_grav"));

			}
			stmt.close();
			rs.close();

			return gravadora;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Gravadoras> Listar() {
		String sql = "select * from artistas";
		List<Gravadoras> gravadoras = new ArrayList<Gravadoras>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var gravadora = new Gravadoras();

				gravadora.setCod_grav(rs.getInt("cod_grav"));
				gravadora.setNac_bras(rs.getString("nac_bras"));
				gravadora.setNom_grav(rs.getString("nom_grav"));
				gravadora.setUf_grav(rs.getString("uf_grav"));

				
				gravadoras.add(gravadora);
			}
			stmt.close();
			rs.close();
			
			return gravadoras;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}

}
