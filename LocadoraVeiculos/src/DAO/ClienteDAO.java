package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import VO.Cliente;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClienteDAO extends Conexao {
	
	public int ProximoIDCliente() throws SQLException {
		
		int idCliente = 0;		
		String sql = "select top 1 cod_cliente from tb_clientes order by cod_cliente desc";
		
		 Connection conexao = Conexao.getConexao();
			
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);							
			
			while (rs.next()) {
				idCliente = rs.getInt("cod_cliente");
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return idCliente+1;
	}
	
	private Cliente Atualizar(Cliente c) throws SQLException {
		
		Connection conexao = Conexao.getConexao();
		String sql = "UPDATE tb_clientes SET nome=?, dt_nascimento=?, tipo_pessoa=?, cod_estado_civil=?, rendimento=?, cod_uf=? WHERE cod_cliente = ?";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, c.getNome());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			stmt.setString(2, df.format(c.getDt_nascimento()));
			stmt.setString(3, c.getTipo_pessoa());
			stmt.setString(4, c.getCod_estado_civil());
			stmt.setString(5, Double.toString(c.getRendimento()));
			stmt.setString(6, c.getCod_uf());
			stmt.setString(7, Integer.toString(c.getCod_cliente()));
			stmt.execute();
		}
		catch(Exception erro) {
			return null;
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return c;
	}

	private Cliente Incluir(Cliente c) throws SQLException {
		
		Connection conexao = Conexao.getConexao();
		String sql = "INSERT INTO tb_clientes(cod_cliente, nome, dt_nascimento, tipo_pessoa, cod_estado_civil, rendimento, cod_uf) VALUES(?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			c.setCod_cliente(ProximoIDCliente());
			
			stmt.setString(1, Integer.toString(c.getCod_cliente()));
			stmt.setString(2, c.getNome());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			stmt.setString(3, df.format(c.getDt_nascimento()));
			stmt.setString(4, c.getTipo_pessoa());
			stmt.setString(5, c.getCod_estado_civil());
			stmt.setString(6, Double.toString(c.getRendimento()));
			stmt.setString(7, c.getCod_uf());
			stmt.execute();
		}
		catch(Exception erro) {
			return null;
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return c;
	}
	
	public void Salvar(Cliente c) throws SQLException {

		if(c.getCod_cliente() == 0) {//É um cliente novo
			Incluir(c);
		}
		else {//Senão é atualização
			Atualizar(c);
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
				cliente.setColCodigo(new SimpleIntegerProperty(rs.getInt("cod_cliente")));
				cliente.setNome(rs.getString("nome"));
				cliente.setColNome(new SimpleStringProperty(rs.getString("nome")));
				cliente.setDt_nascimento(rs.getDate("dt_nascimento"));
				cliente.setTipo_pessoa(rs.getString("tipo_pessoa"));
				cliente.setCod_estado_civil(rs.getString("cod_estado_civil"));				
				cliente.setRendimento(rs.getDouble("rendimento"));
				cliente.setCod_uf(rs.getString("cod_uf"));
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

	public Cliente RetornaCliente(int cod_cliente) throws SQLException{
		
		 Cliente cliente = new Cliente();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			String sql = "select * from tb_clientes where cod_cliente = " + cod_cliente;
			
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()) {				
				cliente.setCod_cliente(rs.getInt("cod_cliente"));
				cliente.setColCodigo(new SimpleIntegerProperty(rs.getInt("cod_cliente")));
				cliente.setNome(rs.getString("nome"));
				cliente.setColNome(new SimpleStringProperty(rs.getString("nome")));
				cliente.setDt_nascimento(rs.getDate("dt_nascimento"));
				cliente.setTipo_pessoa(rs.getString("tipo_pessoa"));
				cliente.setCod_estado_civil(rs.getString("cod_estado_civil"));				
				cliente.setRendimento(rs.getDouble("rendimento"));
				cliente.setCod_uf(rs.getString("cod_uf"));				
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return cliente;		
	}
	
	public void Excluir(int cod_cliente) throws SQLException {
		
		Connection conexao = Conexao.getConexao();
		String sql = "DELETE FROM tb_clientes WHERE cod_cliente = ?";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, Integer.toString(cod_cliente));
			stmt.execute();
		}
		catch(Exception erro) {
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
	}
}
