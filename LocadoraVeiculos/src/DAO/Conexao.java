package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexao {

    public static Connection getConexao() throws SQLException {

    		Connection conexao = null;
            String stringConexao = "jdbc:sqlserver://localhost:1433;databaseName=Veiculos;user=sa;password=123456;";

            try {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                conexao = DriverManager.
						getConnection(stringConexao);             

            } catch (SQLException ex) {

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLException: " + ex.getSQLState());
                System.out.println("SQLException: " + ex.getErrorCode());

            } catch (Exception e) {

                System.out.println("Não foi possivel conectar com o banco");

            }
            return conexao;
    }    
}
