package br.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

import br.com.Conexao;
//import br.com.Integer;

	public class Conexao {
		
		private static final String SERVER = "localhost";
		private static final Integer PORTA = 1521;
		private static final String USER = "locadora";
		private static final String PASSWORD = "#l4c1d4r1";
		private static final String DATABASE = "orcl";
		public static String status = "Conectando...";
		public static Conexao conexao;
		
		public static Conexao getInstance() {
			if(conexao == null) {
				conexao = new Conexao();
			}
			return conexao;
		}
		
		public static Connection getConexao() {
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName(SERVER);
			ds.setPort(PORTA);
			ds.setDatabaseName(DATABASE);
			
			try {
				Connection conn = DriverManager.getConnection(ds.getUrl() + "?useSSL=false&useTimezone=true&serverTimezone=UTC",USER, PASSWORD);
				if(conn != null) {
					var msg = "Conexão efetuada com sucesso";
					status = msg;
					return conn;
				}
			} catch (SQLException e) {
				var msg = "Falha na conexão -> " + e.getMessage();
				status = msg;
				return null;
			}
			return null;
		}
		
		public static String StatusConexao() {
			return status;
		}
		
		public static boolean FecharConexao() {
			try {
				Conexao.getConexao().close();
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
		
		public static Connection ReiniciarConexao() {
			FecharConexao();
			return Conexao.getConexao();
		}
}

