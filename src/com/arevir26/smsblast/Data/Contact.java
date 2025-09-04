package com.arevir26.smsblast.Data;

import java.util.ArrayList;
import java.util.List;

public class Contact {
	public String name, number;
	public long id;
	public List<String> groups;

	public Contact(String name,  String number) {
		id = 0;
		this.groups = new ArrayList<String>();
		this.name = name;
		this.number = number;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Contact) {
			return ((Contact)obj).number == this.number;
		}
		return super.equals(obj);
	}
	
	
	
}
