package VVT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mysql.jdbc.log.Log;

public class TestQueries {
	// Field for the database connection
	private Connection conn;
	
	/**
	 * Method to get the userID from VolunteerCredentials mainly to reference in other methods
	 * 
	 * @param user Holds the user input for the username
	 * @return userID
	 * @throws SQLException
	 */
	public String selectUserID(String user) throws SQLException
	{
		String uID = "";
		// Create a connection to the database.
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTTDraft?useSSL=false", "root", "rootPass717");
		
		try
		{
			//tries to use the connection to run the query
			PreparedStatement stmt = conn.prepareStatement("SELECT VolunteerID FROM VolunteerCredentials WHERE Username = ?");
			stmt.setString(1, user); //inputs the username
			ResultSet rs = stmt.executeQuery();
			
			//Loop through the result set to pull strings out that are needed in order to execute the query string
			while (rs.next()) 
			{
				uID = rs.getString("VolunteerID"); //Gather string stored in the VolunteerID row 
			}	
		}
		catch (SQLException e)
		{
			System.out.println(e); //Output exception type
		}
		conn.close(); //Close Database connection
		return uID;
	}
	
	/**
	 *  Method to get the Full name of user from Volunteer table using userID method
	 * 
	 * @param user Holds the user input for the username
	 * @throws SQLException
	 */
	public void selectFullName(String user) throws SQLException //Gets full Name
	{	
		// Create a connection to the database.
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTTDraft?useSSL=false", "root", "rootPass717");

		try 
		{
			//tries to use the connection to run the query
			PreparedStatement stmt = conn.prepareStatement("SELECT FirstName, LastName FROM Volunteer WHERE VolunteerID = ?");
			stmt.setString(1, selectUserID(user)); //inputs the username
			ResultSet rs = stmt.executeQuery();
			
			//Loop through the result set to pull strings out that are needed in order to execute the query string
			while (rs.next()) 
			{
				String fname = rs.getString("FirstName"); //Gather string stored in the FirstName row
				String lname = rs.getString("LastName"); //Gather string stored in the LastName row
				System.out.println(fname + " " + lname); //Output concatenated Volunteer Name 
			}
			
		}
		catch (SQLException e)
		{
			System.out.println(e); //Output exception type
		}
		conn.close(); //Close Database connection
	}
	
	/**
	 * Method to verify if username exists in the database via VolunteerCredentials table
	 * 
	 * @param user Holds the user input for the username
	 * @return Boolean
	 * @throws SQLException
	 */
	public Boolean checkUsername(String user) throws SQLException // Gets username and password
	{	
		String userID = "";
		// Create a connection to the database.
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTTDraft?useSSL=false", "root", "rootPass717");
		
		try 
		{
			//tries to use the connection to run the query
			PreparedStatement stmt = conn.prepareStatement("SELECT VolunteerID FROM VolunteerCredentials WHERE Username = ?");
			stmt.setString(1, user); //inputs the username
			ResultSet rs = stmt.executeQuery();
			
			//Loop through the result set to pull strings out that are needed in order to execute the query string
			while (rs.next()) 
			{
				userID = rs.getString("VolunteerID"); //Gather string stored in the FirstName row
			}
			if(userID != "")
			{
				System.out.println("Success " + userID); //Output concatenated Volunteer Name
				conn.close();
				return true;
			}
		}
		catch (SQLException e)
		{
			System.out.println(e + "Error");
			conn.close(); return false;//Output exception type
		} 
		conn.close(); return false;//Output exception type
	}
	
	/**
	 * Method to get all usernames and passwords, most likely won't be used
	 * 
	 * @throws SQLException
	 */
	public void selectCredentials() throws SQLException // Gets username and password
	{	
		// Create a connection to the database.
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTTDraft?useSSL=false", "root", "rootPass717");
		
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();
		
		String selectCredentials = ("Select Username, Password FROM VolunteerCredentials"); //SQL Select statement

		try 
		{
			ResultSet rs = stmt.executeQuery(selectCredentials); // This line creates a ResultSet object then argues the query string that contains the select statement
			
			//Loop through the result set to pull strings out that are needed in order to execute the query string
			while (rs.next()) 
			{
				String Username = rs.getString("Username"); //Gather string stored in the FirstName row
				String Password = rs.getString("Password"); //Gather string stored in the LastName row 
			}
			
		}
		catch (SQLException e)
		{
			System.out.println(e); //Output exception type
		}
		conn.close(); //Close Database connection
	}
	
	/**
	 * Method to authenticate the user's username and password are not only within the database, but they are correct
	 * 
	 * @param uname Holds the user input for the username
	 * @param pword Holds the user input for the password
	 * @return Boolean
	 * @throws SQLException
	 */
	public Boolean authenticate(String uname, String pword) throws SQLException
	{
		String iD = "";
		// Create a connection to the database.
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTTDraft?useSSL=false", "root", "rootPass717");

		try
		{
			//tries to use the connection to run the query
			PreparedStatement stmt = conn.prepareStatement("SELECT VolunteerID, Username, Password FROM VolunteerCredentials WHERE Username = ? AND Password = ?");
			stmt.setString(1, uname); //inputs the username
			stmt.setString(2, pword); //inputs the password
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				iD = rs.getString("VolunteerID");
			}
			
			//checks if query pulled a userID therefore was successful at authentication
			if (iD != "")
			{
				conn.close();
				return true;
			}
		}
		catch (SQLException e)
		{
			conn.close();
			return false;
		} 
		conn.close(); //Close Database connection
		return false;
	}
}