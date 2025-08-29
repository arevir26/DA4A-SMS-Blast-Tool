package com.arevir26.smsblast.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.arevir26.smsblast.Data.MarketData;


/// Windows ANSI encoding = ""Cp1252""

public class CSVMarketDataParser implements IMarketDataParser{
	
	public static final String WINDOWS_ENCODING = "Cp1252";

	@Override
	public List<MarketData> getMarketData(File file) throws Exception {
		List<MarketData> marketdatalist = new ArrayList<>();
		try (Reader reader = new InputStreamReader(new FileInputStream(file), WINDOWS_ENCODING)) {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
			Iterator<CSVRecord> iterator = records.iterator();
			
			//get columnNames
			String headers[] = {};
			if(iterator.hasNext()) {
				CSVRecord record = iterator.next();
				headers = record.values();
			}
			
			while(iterator.hasNext()) {
				CSVRecord record = iterator.next();
				String marketName = record.get(0);
				int valuesCount = record.values().length;
				int columnCount = valuesCount > headers.length ? headers.length : valuesCount;
				HashMap<String, String> data = new HashMap<>();
				MarketData marketdata = new MarketData(marketName, data);
				for(int i=0; i < columnCount; i++) {
					data.put(headers[i], record.get(i));
					
				}
				marketdatalist.add(marketdata);
			}
			
		} 

		return marketdatalist;
	}
	
}
