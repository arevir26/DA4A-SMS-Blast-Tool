package com.arevir26.smsblast.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.arevir26.smsblast.Data.MarketData;

public class MessageDataParser {
	private MessageDataParser() {
		
	}
	public static String parseData(String message, MarketData data) {
		String newMessage = message;
		if(data==null)return newMessage;
		HashMap<String, String> priceData = data.getData();
		Iterator<Entry<String,String>> iterator = priceData.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String,String> entry = iterator.next();
			String pattern = String.format("{%s}", entry.getKey());
			newMessage = newMessage.replace(pattern, entry.getValue());
		}
		return newMessage;
	}
}
