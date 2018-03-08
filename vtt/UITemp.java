package VVT;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UITemp extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;
	private JLabel lblPass;
	private JLabel lblError;
	private JButton btnClear;
	static ArrayList<String> listUser;
	static ArrayList<String> listPass;
	TestQueries tq = new TestQueries();
	Boolean checkSum;
	
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
		
		/**
		 * Enter button method to authenticate user information
		 */
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					String un = getUsername().toString();
					String pw = getPassword().toString();
					
					//checks if textboxes are not empty
					if (txtUser.getText() != "" && txtPass.getText() != "")
					{
						if(userCheck(un) == true) //***MAY REMOVE*** runs userCheck to ensure the username is correct and to get the userID based on said username
							if(passAuth(un, pw) == true) //runs passAuth to authenticate the password based on the userID returned in usercheck
								lblError.setText("Success");
							else
								lblError.setText("Invalid Username or Password");
						else
							lblError.setText("Invalid Username or Password");
					}
					else
					{
						lblError.setText("Please enter Username and Password");
					}
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnter.setBounds(109, 215, 91, 23);
		contentPane.add(btnEnter);
		
		lblError = new JLabel("");
		lblError.setBounds(10, 11, 422, 14);
		contentPane.add(lblError);
		
		/**
		 * Clear button method
		 */
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
	
	/**
	 * get Username
	 * @return Username
	 */
	public String getUsername()
	{
		String un = txtUser.getText();
		return un;
	}
	
	/**
	 * get Password
	 * @return Password
	 */
	public String getPassword()
	{
		String pw = txtPass.getText();
		return pw;
	}
	
	/**
	 * Method to run all methods to check if the username entered exists in the database
	 * 
	 * @return Boolean true if username checks out, false if otherwise
	 * @throws SQLException
	 */
	private Boolean userCheck() throws SQLException
	{		
		//authenticates username user entered textbox
		if (tq.checkUsername(getUsername().toString()) == true)
			return true;
		else
			return false;
	}
	
	/**
	 * Method to run all methods to check if the username entered exists in the database
	 * 
	 * @param un Username
	 * @return Boolean true if username checks out, false if otherwise
	 * @throws SQLException
	 */
	private Boolean userCheck(String un) throws SQLException
	{
		//authenticates username user entered textbox
		if (tq.checkUsername(un) == true)
			return true;
		else
			return false;
	}
	
	/**
	 * Method to run all methods to check if the password entered exists in the database
	 * and matches the username (No parameters)
	 * 
	 * @return Boolean true if password matches username (authentication successful)
	 * @throws SQLException
	 */
	private Boolean passAuth() throws SQLException
	{	
		//authenticates username and password to userID
		if (tq.authenticate(getUsername().toString(), getPassword().toString()) == true)
			return true;
		else
			return false;
	}
	

	/**
	 * Method to run all methods to check if the password entered exists in the database
	 * and matches the username
	 * 
	 * @param un (Username)
	 * @param pw (Password)
	 * @return Boolean true if password matches username (authentication successful)
	 * @throws SQLException
	 */
	public Boolean passAuth(String un, String pw) throws SQLException
	{
		//authenticates username and password to userID
		if(tq.authenticate(un, pw) == true)
			return true;
		else
			return false;
	}
	
	/**
	 * Method to clear fields
	 */
	private void clearFields()
	{
		txtUser.setText("");
		txtPass.setText("");
		lblError.setText("");
	}
}