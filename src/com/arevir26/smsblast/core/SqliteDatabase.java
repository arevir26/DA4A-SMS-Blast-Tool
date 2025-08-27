package com.arevir26.smsblast.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.Data.SmsTemplate;

public class SqliteDatabase implements IDatabase{
	
	public SqliteDatabase()
	{
		try(Connection connection = DriverManager.getConnection("jdbc:sqlite:smsblast.db");
				Statement statement = connection.createStatement()){
				statement.setQueryTimeout(30);
				statement.execute("Create table if not exists users(name TEXT)");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	@Override
	public List<SmsTemplate> getTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTemplate(SmsTemplate template) {

		return false;
	}

	@Override
	public boolean addContact(Contact contact) {
		// TODO Auto-generated method stub
		return false;
	}

}
