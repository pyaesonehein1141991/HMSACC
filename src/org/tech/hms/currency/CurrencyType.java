package org.tech.hms.currency;

public enum CurrencyType {
	HOMECURRENCY("Home Currency"),SOURCECURRENCY("Source Currrency");
	
	private String label;

	
	private CurrencyType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
}
