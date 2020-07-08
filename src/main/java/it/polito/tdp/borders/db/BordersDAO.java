package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Collegamento;
import it.polito.tdp.borders.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {
	
	public List<Country> loadAllCountries() {
		
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}

	public ArrayList<Collegamento> getConfiniAnno(int anno) {
		
		String sql = 
				"SELECT co.CCode AS cod1, co.StateAbb AS abb1, co.StateNme AS nome1, co2.CCode AS cod2, co2.StateAbb AS abb2, co2.StateNme AS nome2 " + 
				"FROM contiguity c, country co, country co2 " + 
				"WHERE c.conttype=1 and c.year<=? AND c.state1no=co.CCode AND c.state2no=co2.CCode AND co.CCode>co2.CCode " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery() ;
			
			ArrayList<Collegamento> list = new ArrayList<Collegamento>() ;
			
			while( rs.next() ) {
				
				Country co1 = new Country(
						rs.getInt("cod1"),
						rs.getString("abb1"), 
						rs.getString("nome1")) ;
				Country co2 = new Country(
						rs.getInt("cod2"),
						rs.getString("abb2"), 
						rs.getString("nome2")) ;
				
				Collegamento c=new Collegamento(co1,co2);
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	
	
	
}

