package it.polito.tdp.borders.model;

public class Confine {
	Country c;
	int confinanti;
	public Country getC() {
		return c;
	}
	public void setC(Country c) {
		this.c = c;
	}
	public int getConfinanti() {
		return confinanti;
	}
	public void setConfinanti(int confinanti) {
		this.confinanti = confinanti;
	}
	public Confine(Country c, int confinanti) {
		super();
		this.c = c;
		this.confinanti = confinanti;
	}
	@Override
	public String toString() {
		return c+" "+confinanti;
	}
	

}
