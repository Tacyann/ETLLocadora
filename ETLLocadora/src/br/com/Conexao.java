package br.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.Conexao;

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
						
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin"+SERVER+":"+PORTA+":"+DATABASE,USER, PASSWORD);
				if(conn != null) {
					var msg = "Conexão efetuada com sucesso";
					status = msg;
					return conn;
				}
			} catch (SQLException e) {
				var msg = "Falha na conexãoo -> " + e.getMessage();
				status = msg;
				return null;
			} catch (ClassNotFoundException e) {
				var msg = "Driver não encontrado ->" + e.getMessage();
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

