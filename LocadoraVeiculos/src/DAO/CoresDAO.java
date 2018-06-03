package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VO.Cores;

public class CoresDAO {
	
	public ArrayList<Cores> RetornaCores() throws SQLException{
		
		 ArrayList<Cores> listaCores = new ArrayList<>();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from tb_cores order by cod_cor;");							
			
			while (rs.next()) {
				Cores cor = new Cores();
				cor.setCod_cor(rs.getInt("cod_cor"));
				cor.setDesc_cor(rs.getString("desc_cor"));
				listaCores.add(cor);
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return listaCores;		
	}	
}
