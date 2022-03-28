package it.polito.tdp.corsi.model;

public class Divisione implements Comparable<Divisione>{
	
	// ATTRIBUTI
	private String CDS;
	private Integer n;
	
	// COSTRUTTORE
	public Divisione(String cDS, Integer n) 
	{
		super();
		CDS = cDS;
		this.n = n;
	}

	// GETTER & SETTER
	public String getCDS() 
	{
		return CDS;
	}

	public void setCDS(String cDS) 
	{
		CDS = cDS;
	}

	public Integer getN() 
	{
		return n;
	}

	public void setN(Integer n) 
	{
		this.n = n;
	}

	@Override
	public String toString() 
	{
		return "CDS=" + CDS + "   n=" + n;
	}

	@Override
	public int compareTo(Divisione o) 
	{
		return this.CDS.compareTo(o.getCDS());
	}
	
	
	
	
	
	

}
