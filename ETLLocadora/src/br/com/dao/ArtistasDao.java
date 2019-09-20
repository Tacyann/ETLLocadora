package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Conexao;
//import br.com.dao.RuntimeException;
//import br.com.dao.String;
import br.com.model.Artistas;

public class ArtistasDao {
	
	private Connection connection;

	public ArtistasDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}
	
	public Artistas Obter(String nome) {
		String sql = "select * from Artista where nome = '" + nome + "'";	
		Artistas artistas = new Artistas();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				artistas.setCod_art(rs.getInt("cod_art"));
				artistas.setCod_grav(rs.getInt("cod_grav"));
				artistas.setMed_anual(rs.getInt("med_anual"));
				artistas.setNac_bras(rs.getString("nac_bras"));
				artistas.setNom_art(rs.getString("nom_art"));
				artistas.setQtd_tit(rs.getInt("qtd_tit"));
				artistas.setTpo_art(rs.getString("tpo_art"));
			}
			stmt.close();
			rs.close();

			return artistas;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Artistas> Listar() {
		String sql = "select * from artistas";
		List<Artistas> artistas = new ArrayList<Artistas>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var artista = new Artistas();

				artista.setCod_art(rs.getInt("cod_art"));
				artista.setCod_grav(rs.getInt("cod_grav"));
				artista.setMed_anual(rs.getInt("med_anual"));
				artista.setNac_bras(rs.getString("nac_bras"));
				artista.setNom_art(rs.getString("nom_art"));
				artista.setQtd_tit(rs.getInt("qtd_tit"));
				artista.setTpo_art(rs.getString("tpo_art"));
				
				artistas.add(artista);
			}
			stmt.close();
			rs.close();
			
			return artistas;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	
}
