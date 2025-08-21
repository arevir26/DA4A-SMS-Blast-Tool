package com.arevir26.smsblast.Data;

public class Commodity {
	protected String name, displayname, value;

	public Commodity(String name, String displayname, String value) {
		super();
		this.name = name;
		this.displayname = displayname;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public String getValue() {
		return value;
	}
	
}
