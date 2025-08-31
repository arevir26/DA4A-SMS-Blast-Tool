package com.arevir26.smsblast.core;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.LinkedList;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.Data.DataManager;
import com.arevir26.smsblast.Data.MarketData;
import com.arevir26.smsblast.Data.SmsTemplate;

public class MessageSender {
	
	
	public MessageSender() {
		URI uri = null;
		try {
			uri = new URI("https://www.google.com");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(SmsTemplate smstemplate, MarketData pricedata, LinkedList<Contact> recipient) {

		HttpClient client = HttpClient.newHttpClient();
		
		BodyPublisher body = BodyPublishers.ofString(generateRecipientList(recipient));
		
		URI uri = DataManager.getInstance().getApiUri();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(uri)
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				//.header("content-type", "text/html")
				//.header("authorization", String.format("Bearer %s", DataManager.getInstance().getBearer()))
				.build();
		System.out.println(request.headers().toString());
//		HttpRequest request = HttpRequest.newBuilder()
//			.uri(uri)
//			.header("accept", "application/json")
//			.header("content-type", "application/json")
//			.header("authorization", String.format("Bearer %s", DataManager.getInstance().getBearer()))
//			.POST(body).build();

		
		BodyHandler<String> handler = BodyHandlers.ofString();

		try {
			HttpResponse<String> response = client.send(request, handler);
			System.out.println(response.body());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param recipients
	 * @return formatted string for recipients
	 */
	private String generateRecipientList(LinkedList<Contact> recipients) {
		String data = "";
		for(Contact contact : recipients) {
			//data += contact.getNumber() + ",";
		}
		return data;
	}
}
