package com.arevir26.smsblast.Data;

import java.util.HashMap;

public class MarketData {
	private String name;
	private HashMap<String, String> data;
	
	
	public MarketData(String name, HashMap<String, String> data) {
		this.name = name;
		this.data = data;
	}


	public String getName() {
		return name;
	}


	public HashMap<String, String> getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
