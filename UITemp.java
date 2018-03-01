package Rotary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UITemp extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;
	private JLabel lblPass;
	private JLabel lblError;
	private JButton btnClear;
	private String strUser;
	private String strPass;
	private int intUser;
	private int intPass;
	static ArrayList<String> listUser;
	static ArrayList<String> listPass;
	
	// Constant for database URL.
	public final String DB_URL =
	               "jdbc:derby:CoffeeDB";

	// Field for the database connection
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UITemp frame = new UITemp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UITemp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(109, 67, 196, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(109, 42, 73, 14);
		contentPane.add(lblUser);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(109, 145, 196, 20);
		contentPane.add(txtPass);
		
		lblPass = new JLabel("Password");
		lblPass.setBounds(109, 120, 157, 14);
		contentPane.add(lblPass);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Boolean checkSum = false;
				//runEnter();
				try
				{
					if(trueRun() == true)
						checkSum = true;
					else
						checkSum = false;
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//checks boolean
				if(checkSum == true)
					lblError.setText("Success");
				else
					lblError.setText("Invalid Username or Password");
			}
		});
		btnEnter.setBounds(109, 215, 91, 23);
		contentPane.add(btnEnter);
		
		lblError = new JLabel("");
		lblError.setBounds(10, 11, 422, 14);
		contentPane.add(lblError);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFields();
			}
		});
		btnClear.setBounds(214, 215, 91, 23);
		contentPane.add(btnClear);
		
		listUser = new ArrayList<String>();
			listUser.add("ezDillion");
			listUser.add("megMonster");
			listUser.add("darthStith");
		
		listPass = new ArrayList<String>();
			listPass.add("SecurePass123");
			listPass.add("Passwizzle56*");
			listPass.add("slipFwack12");
	}
	
	private void runEnter() //Not used though possibly useful?
	{
		if (txtUser.getText() != "" && txtPass.getText() != "")
		{
			strUser = txtUser.getText();
			strPass = txtPass.getText();
			intUser = -1;
			intPass = -1;
			
			intUser = listUser.indexOf(strUser);
			
			if (intUser != -1)
			{
				intPass = listPass.indexOf(strPass);
				
				if (intUser == intPass)
				{
					lblError.setText("Success");
				}
				else
				{
					lblError.setText("Invalid Password");
				}
			}
			else
			{
				lblError.setText("Invalid Username");
			}
		}
		else
		{
			lblError.setText("Please enter username AND password");
		}
	}
	
	private Boolean trueRun() throws SQLException
	{
		if (txtUser.getText() != "" && txtPass.getText() != "")
		{
			strUser = txtUser.getText();
			strPass = txtPass.getText();
			String strFinal = "";
			
			// Create a connection to the database.
			conn = DriverManager.getConnection(DB_URL);
			
			// Create a Statement object for the query.
			Statement stmt = conn.createStatement();
			
			// Execute the query.
			ResultSet resultSet = stmt.executeQuery(
			          "SELECT Username " +
			          "FROM volunteerCredentials" +
			          "WHERE Username = '" +
			          strUser + "' LIMIT 1");
			
			// If the result set has a row, go to it
			// and retrieve the product number.
			if (resultSet.next())
				strFinal = resultSet.getString(1);
				
			// Close the Connection and Statement objects.
			conn.close();
			stmt.close();
			
			if (strUser == strFinal)
				return checkPass(strUser, strPass);
			else
				lblError.setText("Invalid Username");
				return false;
		}
		else
		{
			lblError.setText("Please enter username AND password");
			return false;
		}
	}
	
	public Boolean checkPass(String un, String pw) throws SQLException
	{
		// Create a connection to the database.
		conn = DriverManager.getConnection(DB_URL);
		
		// Create a Statement object for the query.
		Statement stmt = conn.createStatement();
		
		// Execute the query.
		ResultSet resultSet = stmt.executeQuery(
		          "SELECT Username " +
		          "FROM volunteerCredentials" +
		          "WHERE Username = '" +
		          un + "' AND Password = '" +
		          pw + "'" +
		          "LIMIT 1");
		
		// If the result set has a row, go to it
		// .
		if (resultSet.next())
			return true;
		
		// Close the Connection and Statement objects.
		conn.close();
		stmt.close();
		
		//
		lblError.setText("Invalid Password");
		return false;
	}
	
	private void clearFields()
	{
		txtUser.setText("");
		txtPass.setText("");
		lblError.setText("");
	}
}
