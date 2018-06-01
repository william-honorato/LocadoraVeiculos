package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VO.EstadoCivil;

public class EstadoCivilDAO extends Conexao {

	public ArrayList<EstadoCivil> RetornaEstadosCivis() throws SQLException{
		
		 ArrayList<EstadoCivil> listaEstadoCivil = new ArrayList<>();
		 Connection conexao = Conexao.getConexao();
		
		try {
			Statement stm = conexao.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from tb_estado_civil order by desc_estado_civil;");
			
			while (rs.next()) {
				EstadoCivil ec = new EstadoCivil();
				ec.setCod_estado_civil(rs.getString("cod_estado_civil"));
				ec.setDesc_estado_civil(rs.getString("desc_estado_civil"));
				listaEstadoCivil.add(ec);
			} //while

			stm.close();
		}
		catch(Exception erro){
			
		}
		finally{
			//Fecha a conexão, ao finalizar
			conexao.close();
		}
		
		return listaEstadoCivil;		
	}
}
