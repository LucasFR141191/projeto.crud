package br.com.apexencinoprojeto_crud.mvc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	// método de conexão com o banco de dados
	public Connection conectar() {
		// criando uma variavel do tipo de retorno da classe connection

		Connection retornoConexao = null;
		try {
			// Referenciar p JDBC
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// dados do servidor
			String dadosServidor = "jdbc:sqlserver://localhost:1433;databaseName=projeto_crud;user=sa;password=12345678;encrypt=false";
			//retorno conexao
			retornoConexao = DriverManager.getConnection(dadosServidor);
			// Mensgem de sucesso
			System.out.println("conectado com sucesso!");
		} catch (Exception e) {
			System.out.println("Falha ao conectar" + e.getMessage());

		}

		// retorno da conexão
		return retornoConexao;

	}

}
