package com.arevir26.smsblast.core;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.arevir26.smsblast.Data.APICredentials;
import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.Data.DataManager;

public class MessageSender {
	
	
	public MessageSender() {
		
	}
	
	private String addCountryCode(String mobileNumber) {
		Pattern pattern = Pattern.compile("");
		if(Pattern.matches("09[0-9]{9}", mobileNumber)) {
			return "+63"+mobileNumber.substring(1);
		}else if(Pattern.matches("[+]639[0-9]{9}", mobileNumber)){
			return mobileNumber;
		}else {
			return null;
		}
	}
	
	private String[] generateRecipientList(List<Contact> contacts) {
		ArrayList<String> list = new ArrayList();
		for(Contact contact : contacts) {
			String number = addCountryCode(contact.number);
			if(number==null)continue;
			list.add(addCountryCode(contact.number));
		}
		return list.toArray(new String[list.size()]);
	}
	
	public void sendMessage(String message, List<Contact> recipient) {

		HttpClient client = HttpClient.newHttpClient();
		
		APICredentials credential = DataManager.getInstance().getCurrentDatabase().getApiCredential("testname");
		URI uri = URI.create(MessageFormat.format("https://sms.8x8.com/api/v1/subaccounts/{0}/messages/batch", credential.subAccountID));
		JSONObject template = new JSONObject();
		template.put("text", message); // Message
		template.put("source", credential.senderID);
		template.put("encoding", "AUTO");
		
		String[] destinations = generateRecipientList(recipient);
		
		JSONObject smsbody = new JSONObject();
		smsbody.put("template", template);
		smsbody.put("destinations", destinations);
		
		BodyPublisher body = BodyPublishers.ofString(smsbody.toString());
		
		
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(uri)
				.header("accept", "application/json")
				.header("content-type", "application/json")
				.header("authorization", String.format("Bearer %s", credential.key))
				.POST(body)
				.build();

		// Prevents sending message without message and destinations
		if(message.length()==0 || destinations.length==0) {
			System.err.println("No Message or destination");
			return;
		}
		
		BodyHandler<String> handler = BodyHandlers.ofString();
		

		// Sends the message
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
	

}
