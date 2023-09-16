package br.com.apexencinoprojeto_crud.mvc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.apexencinoprojeto_crud.mvc.conexao.Conexao;

public class Principal {

	public static void main(String[] args) {
		// instanciar um objeto da classe conexao
		Conexao conexao = new Conexao();

		String sql = "SELECT * FROM tabela";
		try {
			Statement createStatement = conexao.conectar().createStatement();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void createPreparedStatement() {
		Conexao conexao = new Conexao();
		String sql = "SELECT * FROM tabela WHERE coluna = ?";
		try {
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(sql);
			int parametro = 1;
			preparedStatement.setInt(1, parametro);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}

	public static void createPreparedStatementWithResultset() {
		Conexao conexao = new Conexao();
		String sql = "SELECT * FROM tabela WHERE coluna = ?";
		try {
			PreparedStatement preparedStatement = conexao.conectar().prepareStatement(sql);
			int parametro = 1;
			preparedStatement.setInt(1, parametro);
			ResultSet executeQuery = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}

}
