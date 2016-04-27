package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dizionario.db.DBConnect;

public class DizionarioDAO {
	
	public List<String> getParole(int lun) {
		List<String> lista = new ArrayList<String>();
		
		Connection conn = DBConnect.getConnection();
		String sql = "select nome from parola where length(nome)=?";
		PreparedStatement st;
		try {
			
			st = conn.prepareStatement(sql);
			st.setInt(1, lun);
			ResultSet res = st.executeQuery();
			
			while(res.next()) 
			{
				lista.add(res.getString("nome"));
			}
			
			res.close();
			conn.close();
			return lista;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}

}
