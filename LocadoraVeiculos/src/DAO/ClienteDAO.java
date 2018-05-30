package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import VO.Cliente;

public class ClienteDAO extends Conexao {

public void Salvar(Cliente c) throws SQLException {
		
		Connection conexao = Conexao.getConexao();
		String sql = "INSERT INTO Clientes() VALUES()";
		
		try {
			

		}
		catch(Exception erro) {
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
	}
	
	public ArrayList<Cliente> RetornaClientes() throws SQLException{
		
		 ArrayList<Cliente> listaClientes = new ArrayList<>();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from tb_clientes order by cod_cliente;");							
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCod_cliente(rs.getInt("cod_cliente"));
				cliente.setNome(rs.getString("nome"));				
				cliente.setDt_nascimento(rs.getDate("dt_nascimento"));
				listaClientes.add(cliente);
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return listaClientes;		
	}
	
}
