package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VO.Estado;

public class EstadoDAO extends Conexao {

	public ArrayList<Estado> RetornaEstados() throws SQLException{
		
		 ArrayList<Estado> listaEstados = new ArrayList<>();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from tb_estados order by desc_uf;");							
			
			while (rs.next()) {
				Estado estado = new Estado();
				estado.setCod_uf(rs.getString("cod_uf"));
				estado.setDesc_uf(rs.getString("desc_uf"));			
				
				listaEstados.add(estado);
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return listaEstados;		
	}
}