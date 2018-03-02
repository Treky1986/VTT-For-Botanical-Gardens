package DAO;

import java.sql.SQLException;

public class TestDAO {
	
	public static void main(String[] args) throws SQLException 
	{
		TestQueries queries = new TestQueries(); //Instantiate TestQueries class to access it's methods 
		TestConnection connect = new TestConnection(); //Instantiate TestConnection class to access it's methods 
		
		
		queries.selectFullName(connect.createConnection(), null); //Call selectFullName method from the queries class
		
		//This method contains a prepared statement that selects First & Last name from the ATTDraft database
		//You must argue the createConnection() method in order to open the database connection
		//Once query is ran, the connection is closed.
		
		//queries.selectCredentials(connect.createConnection(), null);
	}
	
	
}
