package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Conexao;
import br.com.model.Artistas;
import br.com.model.Produto;

public class ProdutoDao {
	

	private Connection connection;

	public ProdutoDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}
	
	public Produto Obter(String nome) {
		String sql = "select * from Produto where nome = '" + nome + "'";	
		Produto produtos = new Produto();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				produtos.setCod_forn(rs.getInt("cod_forn"));
				produtos.setCod_prod(rs.getInt("cod_prod"));
				produtos.setDsc_prod(rs.getString("dsc_prod"));
				produtos.setPer_parc(rs.getInt("per_parc"));
				produtos.setPreco_pro(rs.getInt("preco_pro"));
				produtos.setQtd_estoque(rs.getInt("qtd_estoque"));
			}
			stmt.close();
			rs.close();

			return produtos;
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
