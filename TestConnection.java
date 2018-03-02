package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection 
{
	/**
	 * This Method creates a connection to the Database. 
	 * 
	 * @return conn
	 */
	public Connection createConnection() 
	{
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTTDraft?useSSL=false","root","root2");
			
		} catch (Exception e) { System.out.println("Not Connected"); }
		
		return conn;
	}
}
