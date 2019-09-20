package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Conexao;
import br.com.model.Fornecedor;


public class FornecedorDao {
	
	private Connection connection;

	public FornecedorDao() {
		new Conexao();
		this.connection = Conexao.getConexao();
	}
	
	public Fornecedor Obter(String nome) {
		String sql = "select * from Fornecedor where nome = '" + nome + "'";	
		Fornecedor fornecedores = new Fornecedor();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				fornecedores.setCod_forn(rs.getInt("cod_forn"));
				fornecedores.setNom_forn(rs.getString("nom_forn"));
				fornecedores.setSld_credor(rs.getInt("sld_credor"));
				fornecedores.setUf_forn(rs.getString("uf_forn"));
			}
			stmt.close();
			rs.close();

			return fornecedores;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}
	
	public List<Fornecedor> Listar() {
		String sql = "select * from fornecedores";
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				var fornecedor = new Fornecedor();

				fornecedor.setCod_forn(rs.getInt("cod_forn"));
				fornecedor.setNom_forn(rs.getString("nom_forn"));
				fornecedor.setSld_credor(rs.getInt("sld_credor"));
				fornecedor.setUf_forn(rs.getString("uf_forn"));
				
				fornecedores.add(fornecedor);
			}
			stmt.close();
			rs.close();
			
			return fornecedores;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Conexao.FecharConexao();
		}
	}

}
