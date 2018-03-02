package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestQueries 
{
	/**
	 * 
	 * @param signal (Class variable that contains database connection)
	 * @param ATTDraft (The Database in use)
	 * @throws SQLException (Handles SQL Exceptions)
	 * 
	 * This method runs a SQL statement that Selects First & Last Name from the Volunteer table
	 * in the VTTDraft database. Database connection is opened in the beginning,
	 * then closed once query is ran.
	 * 
	 * 
	 */
	public void selectFullName(Connection signal, String ATTDraft) throws SQLException 
	{		
		Statement stmt = null; //Set Statement class variable to null
		
		String selectFullName = ("Select FirstName, LastName FROM Volunteer"); //SQL Select statement
	
			
		try 
		{
			stmt = signal.createStatement(); // This line creates the Statement object for database communication 
			ResultSet rs = stmt.executeQuery(selectFullName); // This line creates a ResultSet object then argues the query string that contains the select statement
			
			//Loop through the result set to pull strings out that are needed in order to execute the query string
			while (rs.next()) 
			{
				String fname = rs.getString("FirstName"); //Gather string stored in the FirstName row
				String lname = rs.getString("LastName"); //Gather string stored in the LastName row
				System.out.println(fname + " " + lname); //Output concatenated Volunteer Name 
			}
			
		} catch (SQLException e) {
			System.out.println(e); //Output exception type
		} finally {
			if (stmt != null) {  stmt.close(); } //Close query statement
		}
		signal.close(); //Close Database connection
	}
	
	public void selectCredentials(Connection signal, String ATTDraft) throws SQLException 
	{		
		Statement stmt = null; //Set Statement class variable to null
		
		String selectCredentials = ("Select Username, Password FROM VolunteerCredentials"); //SQL Select statement
	
			
		try 
		{
			stmt = signal.createStatement(); // This line creates the Statement object for database communication 
			ResultSet rs = stmt.executeQuery(selectCredentials); // This line creates a ResultSet object then argues the query string that contains the select statement
			
			//Loop through the result set to pull strings out that are needed in order to execute the query string
			while (rs.next()) 
			{
				String Username = rs.getString("Username"); //Gather string stored in the FirstName row
				String Password = rs.getString("Password"); //Gather string stored in the LastName row
				//int id = rs.getInt("VolunteerID");
				System.out.println(Username + " " + Password); //Output concatenated Volunteer Name 
			}
			
		} catch (SQLException e) {
			System.out.println(e); //Output exception type
		} finally {
			if (stmt != null) {  stmt.close(); } //Close query statement
		}
		signal.close(); //Close Database connection
	}
	
	
}
