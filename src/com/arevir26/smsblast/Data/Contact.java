package com.arevir26.smsblast.Data;

public class Contact {
	protected String name, group, number;

	public Contact(String name, String group, String number) {
		super();
		this.name = name;
		this.group = group;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public String getNumber() {
		return number;
	}
	
	
}
