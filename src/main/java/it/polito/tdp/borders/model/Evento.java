package it.polito.tdp.borders.model;


public class Evento implements Comparable<Evento> {
	
	private TipoEvento tipo;
	private int tempo;
	private Country nazione;
	private int numeroMigranti;
	
	public enum TipoEvento {
		ARRIVO_MIGRANTI,
		PARTENZA_MIGRANTI
	}
	
	

	public Evento(TipoEvento tipo, int tempo, Country nazione,int numeroMigranti) {
		super();
		this.tipo = tipo;
		this.tempo = tempo;
		this.nazione=nazione;
		this.numeroMigranti=numeroMigranti;
	}



	@Override
	public int compareTo(Evento other) {
		// TODO Auto-generated method stub
		if(tempo>other.tempo)
			return 1;
		if(tempo<other.tempo)
			return -1;
		return 0;
	}



	@Override
	public String toString() {
		return tipo + " " + tempo+ " "+ nazione+" "+this.numeroMigranti;
	}



	public TipoEvento getTipo() {
		return tipo;
	}



	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}



	public int getTempo() {
		return tempo;
	}



	public void setTempo(int tempo) {
		this.tempo = tempo;
	}



	public Country getNazione() {
		return nazione;
	}



	public void setNazione(Country nazione) {
		this.nazione = nazione;
	}



	public int getNumeroMigranti() {
		return numeroMigranti;
	}



	public void setNumeroMigranti(int numeroMigranti) {
		this.numeroMigranti = numeroMigranti;
	}
	
	
	
	
	

}
