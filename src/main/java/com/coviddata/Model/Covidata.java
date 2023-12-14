package com.coviddata.Model;



public class Covidata {

	
	private String state;
	private String country;
	private  int   lastedTotalCases;
	private int diffrompreviousday;
	
	
	
	public int getDiffrompreviousday() {
		return diffrompreviousday;
	}
	
	
	public void setDiffrompreviousday(int diffrompreviousday) {
		this.diffrompreviousday = diffrompreviousday;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLastedTotalCases() {
		return lastedTotalCases;
	}
	public void setLastedTotalCases(int lastedTotalCases) {
		this.lastedTotalCases = lastedTotalCases;
	}
	@Override
	public String toString() {
		return "Covidata [state=" + state + ", country=" + country + ", lastedTotalCases=" + lastedTotalCases + "]";
	}
	
	
	
	
	
}
