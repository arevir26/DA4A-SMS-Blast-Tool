package com.arevir26.smsblast.core;

import java.io.File;
import java.util.List;

import com.arevir26.smsblast.Data.MarketData;

public interface IMarketDataParser {
	public List<MarketData> getMarketData(File file) throws Exception;
}
