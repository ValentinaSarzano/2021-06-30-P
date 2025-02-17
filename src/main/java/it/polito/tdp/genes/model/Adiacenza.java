package it.polito.tdp.genes.model;

public class Adiacenza {

	public String localization1;
	public String localization2;
	public Integer peso;
	
	public Adiacenza(String localization1, String localization2, Integer peso) {
		super();
		this.localization1 = localization1;
		this.localization2 = localization2;
		this.peso = peso;
	}

	public String getLocalization1() {
		return localization1;
	}

	public void setLocalization1(String localization1) {
		this.localization1 = localization1;
	}

	public String getLocalization2() {
		return localization2;
	}

	public void setLocalization2(String localization2) {
		this.localization2 = localization2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	
}
