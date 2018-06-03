package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import VO.Alienacao;
import VO.Cliente;

public class AlienacaoDAO extends Conexao {
	
	public int ProximoCodigoAlienacao() throws SQLException {
		
		int codigo = 0;		
		String sql = "select top 1 cod_alienacao from tb_alienacao order by cod_alienacao desc";
		
		 Connection conexao = Conexao.getConexao();
			
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);							
			
			while (rs.next()) {
				codigo = rs.getInt("cod_alienacao");
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return codigo+1;
	}

	

	private Alienacao Incluir(Alienacao a) throws SQLException {
		
		Connection conexao = Conexao.getConexao();
		String sql = "INSERT INTO tb_alienacao (cod_alienacao, cod_cliente, data_cadastro, data_liberacao, cod_marca, modelo, ano, ano_modelo, cod_cor, numchassi, cod_uf_placa VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			a.setCod_alienacao(ProximoCodigoAlienacao());
			
			stmt.setString(1, Integer.toString(a.getCod_alienacao()));
			stmt.setString(2, Integer.toString(a.getCod_cliente()));	
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			stmt.setString(3, df.format(LocalDate.now()));
			stmt.setString(4, df.format(LocalDate.now()));			
			stmt.setString(5, Integer.toString(a.getCod_marca()));
			stmt.setString(6, a.getModelo());
			stmt.setString(7, Integer.toString(a.getAno()));
			stmt.setString(8, Integer.toString(a.getAno_modelo()));
			stmt.setString(9, Integer.toString(a.getCod_cor()));
			stmt.setString(10, a.getNumchassi());
			stmt.setString(11, a.getCod_uf_placa());
			stmt.execute();
		}
		catch(Exception erro) {
			return null;
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return a;
	}
	
	public void Salvar(Alienacao a) throws SQLException {

		if(a.getCod_alienacao() == 0) {//É um cliente novo
			Incluir(a);
		}
		else {//Senão é atualização
			//Atualizar(a);
		}
	}
	
	
	public ArrayList<Alienacao> RetornaAlienados() throws SQLException{
		
		 ArrayList<Alienacao> listaAlienados = new ArrayList<>();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from tb_alienacao order by cod_alienacao;");							
			
			while (rs.next()) {
				Alienacao a = new Alienacao();
				a.setCod_alienacao(rs.getInt("cod_alienacao"));				
				a.setCod_cliente(rs.getInt("cod_cliente"));				
				a.setData_cadastro(rs.getDate("data_cadastro"));
				a.setData_liberacao(rs.getDate("data_liberacao"));
				a.setCod_marca(rs.getInt("cod_marca"));				
				a.setModelo(rs.getString("modelo"));
				a.setAno(rs.getInt("ano"));
				a.setAno_modelo(rs.getInt("ano_modelo"));
				a.setCod_cor(rs.getInt("cod_cor"));
				a.setNumchassi(rs.getString("numchassi"));
				a.setCod_uf_placa(rs.getString("cod_uf_placa"));
				listaAlienados.add(a);
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return listaAlienados;		
	}
}
