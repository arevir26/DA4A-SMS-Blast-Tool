package com.arevir26.smsblast.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.arevir26.smsblast.Data.Contact;
import com.arevir26.smsblast.Data.SmsTemplate;

public class SqliteDatabase implements IDatabase{
	private static final String DATABASE = "jdbc:sqlite:data/smsblast.db";
	private List<DataChangeListener> listeners;
	public SqliteDatabase()
	{
		listeners = new ArrayList<IDatabase.DataChangeListener>();
		try(Connection connection = DriverManager.getConnection(SqliteDatabase.DATABASE);
				Statement statement = connection.createStatement()){
				statement.setQueryTimeout(30);
				statement.execute("CREATE TABLE IF NOT EXISTS \"contacts_tb\" ("
						+ "	\"id\"	INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "	\"name\"	TEXT NOT NULL,"
						+ "	\"number\"	TEXT NOT NULL"
						+ ");");
				connection.createStatement().execute("CREATE TABLE IF NOT EXISTS \"group_tb\" ("
						+ "	\"id\"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
						+ "	\"name\"	TEXT NOT NULL UNIQUE"
						+ ");");
				connection.createStatement().execute("CREATE TABLE IF NOT EXISTS \"lookup_tb\" ("
						+ "	\"group_id\"	INTEGER NOT NULL,"
						+ "	\"contact_id\"	INTEGER NOT NULL,"
						+ "	PRIMARY KEY(\"group_id\",\"contact_id\"),"
						+ "	UNIQUE(\"group_id\",\"contact_id\")\r\n"
						+ ");");
				connection.createStatement().execute("create view if not exists merged as SELECT contacts_tb.id, contacts_tb.name, contacts_tb.number, group_tb.name as groupname FROM contacts_tb\r\n"
						+ "LEFT JOIN lookup_tb ON lookup_tb.contact_id=contacts_tb.id\r\n"
						+ "LEFT JOIN group_tb ON lookup_tb.group_id=group_tb.id\r\n"
						+ "ORDER BY contacts_tb.id asc");
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
	
	/**
	 * Insert a group in the database and return the id
	 * @param name
	 * @return
	 */
	private long insertGroup(String name) {
		String query = "INSERT INTO group_tb(\"name\") values(\"{0}\")";
		query = MessageFormat.format(query, name);
		long id = 0;
		try(Connection con = DriverManager.getConnection(DATABASE);
				PreparedStatement prestatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			int res = prestatement.executeUpdate();
			if(res>0) {
				ResultSet result = prestatement.getGeneratedKeys();
				if(result.next()) {
					id = result.getLong(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * Searches the given string from the database and return the id
	 * @param group name of group to get the id
	 * @return group_id, 0 if not found
	 */
	public long getGroupId(String group) {
		long id = 0;
		String query = "SELECT id FROM group_tb where name=\"{0}\";";
		query = MessageFormat.format(query, group);
		try(Connection con = DriverManager.getConnection(DATABASE);
				Statement statement = con.createStatement()){
			ResultSet results = statement.executeQuery(query);
			if(results.next()) {
				id = results.getLong(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	private boolean insertLookup(long groupId, long contactId) {
		boolean result = false;
		String sql = "INSERT INTO lookup_tb(group_id, contact_id) values(?, ?);";
		try(Connection con = DriverManager.getConnection(DATABASE);
				PreparedStatement statement = con.prepareStatement(sql)){
			statement.setLong(1, groupId);
			statement.setLong(2, contactId);
			int res = statement.executeUpdate();
			if(res>0) {
				result = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	@Override
	public boolean addContact(Contact contact) {
		String query = "INSERT INTO contacts_tb(\"name\", \"number\") values(\"{0}\",\"{1}\" )";
		//System.out.println(MessageFormat.format(query, 5, 6));
		long contactId = 0;
		// insert contact info to Database
		try(Connection connection = DriverManager.getConnection(SqliteDatabase.DATABASE);
			PreparedStatement statement = connection.prepareStatement(MessageFormat.format(query, contact.name,contact.number), Statement.RETURN_GENERATED_KEYS);){
			
			int res = statement.executeUpdate();
			
			if(res>0) {
				ResultSet result = statement.getGeneratedKeys();
				if(result.next()) {
					contactId = result.getLong(1);
				}
			}
			// Insert group to group table if not existing
			for(String group : contact.groups) {
				long groupId = getGroupId(group);
				if(groupId==0) {
					groupId = insertGroup(group);
				}
				if(groupId>0) {
					// Insert data to lookup table
					insertLookup(groupId, contactId);
				}
			}
			
				
				
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		if(contactId<0)return false;
		onContactpDataChanged();
		return true;
	}
	
	
	@Override
	public boolean addGroup(String name) {
		
		if(insertGroup(name)>0) {
			onGroupDataChanged();
			return true;
		}
		return false;
	}
	@Override
	public List<String> getGroupList() {
		List<String> groups = new ArrayList<String>();
		String query = "SELECT id, name FROM group_tb";
		try(Connection connection = DriverManager.getConnection(SqliteDatabase.DATABASE);
			Statement statement = connection.createStatement()){
			statement.setQueryTimeout(30);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				groups.add(result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	public void onGroupDataChanged() {
		for(DataChangeListener listener : this.listeners) {
			listener.onGroupDataChanged(this);
		}
	}
	
	
	public void onContactpDataChanged() {
		for(DataChangeListener listener : this.listeners) {
			listener.onContactDataChanged(this);
		}
	}
	@Override
	public void addDataChageListener(DataChangeListener listener) {
		listeners.add(listener);
	}
	@Override
	public List<Contact> getContacts() {
		List<Contact> c = new ArrayList<Contact>();
		String query = "SELECT * FROM merged";
		
		HashMap<Long, Contact> list = new HashMap<Long, Contact>();
		
		try(Connection con = DriverManager.getConnection(DATABASE);
				Statement stment = con.createStatement()){
			ResultSet result = stment.executeQuery(query);
			while(result.next()) {
				long id = result.getLong(1);
				String contactName = result.getString(2);
				String contactNum = result.getString(3);
				Contact contact;
				if(list.containsKey(id)) {
					contact = list.get(id);
				}else {
					contact = new Contact(contactName, contactNum);
					contact.id = id;
				}
				contact.groups.add(result.getString(4));
				list.put(id, contact);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.addAll(list.values());
		return c;
	}

}
