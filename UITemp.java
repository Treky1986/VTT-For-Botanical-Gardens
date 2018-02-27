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
				runEnter();
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
	
	private void runEnter()
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
			/*for (String strTemp: listUser)
			{
				if (strTemp == strUser)
				{
					if (strPass == listPass.get(intIndex))
					{
						lblError.setText("Success");
					}
					else
					{
						lblError.setText("Invalid Password");
					}
					
					break;
				}
				else
				{
					intIndex++;
					
					if (intIndex == listUser.size())
					{
						lblError.setText("Invalid Username");
					}
				}
			}*/
		}
		else
		{
			lblError.setText("Please enter username AND password");
		}
	}
	
	private void clearFields()
	{
		txtUser.setText("");
		txtPass.setText("");
		lblError.setText("");
	}
}
