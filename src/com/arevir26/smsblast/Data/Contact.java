package com.arevir26.smsblast.Data;

import java.util.ArrayList;
import java.util.List;

public class Contact {
	public String name, number;
	public List<String> groups;

	public Contact(String name,  String number) {
		this.groups = new ArrayList<String>();
		this.name = name;
		this.number = number;
	}
	
}
