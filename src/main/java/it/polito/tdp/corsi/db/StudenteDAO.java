package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getStudentiByCorso(String codins)
	{
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola "
				+ "AND i.codins = ?";
		
		List<Studente> result = new ArrayList<Studente>();
		
		try 
		{
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				
				result.add(s);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
		}
		catch(SQLException e)
		{
			System.out.println("ERRORE nel DAO");
			e.printStackTrace();
			
			return null;
		}
	}

	
	public List<Divisione> getDivisioneStudenti(String codins)
	{
		String sql = "SELECT s.CDS, COUNT(*) AS n "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola "
				+ "AND i.codins = ? "
				+ "AND s.CDS <> \"\" "
				+ "GROUP BY s.cds";
		
		List<Divisione> result = new ArrayList<Divisione>();
		
		try 
		{
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Divisione d = new Divisione(rs.getString("CDS"), rs.getInt("n"));
				
				result.add(d);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
		}
		catch(SQLException e)
		{
			System.out.println("ERRORE nel DAO");
			e.printStackTrace();
			
			return null;
		}
	}
}
