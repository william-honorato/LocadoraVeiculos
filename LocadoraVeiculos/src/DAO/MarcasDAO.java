package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VO.Marcas;

public class MarcasDAO extends Conexao {

	
	public ArrayList<Marcas> RetornaMarcas() throws SQLException{
		
		 ArrayList<Marcas> listaMarcas = new ArrayList<>();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from tb_marcas order by cod_marca;");							
			
			while (rs.next()) {
				Marcas marca = new Marcas();
				marca.setCod_marca(rs.getInt("cod_marca"));
				marca.setDesc_marca(rs.getString("desc_marca"));
				listaMarcas.add(marca);
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return listaMarcas;		
	}
	
}
