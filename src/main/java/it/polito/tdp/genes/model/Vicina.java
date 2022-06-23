package it.polito.tdp.genes.model;

public class Vicina {
	private String localization;
	private int peso;
	
	public Vicina(String localization, int peso) {
		super();
		this.localization = localization;
		this.peso = peso;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
