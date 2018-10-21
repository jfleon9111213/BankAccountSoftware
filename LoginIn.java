import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginIn extends JFrame {

	private JPanel 		Panel;
	private JButton 	Sign_In, Create_An_Account;
	private JLabel      user_name, password, Pin;
	private JTextField  User_Name, PassWord, PIN;
	private DatabaseManager Manager; 
	private static final long serialVersionUID = 1L;
	
	
	public LoginIn()
	{
		CreateWindow();

		// Singleton principle to obtain a Database Manager
		// once we get the object pass in the user_name pass_word and pin
		// OBTAIN A HANDLE which is an object of that persons's account  
		Manager = DatabaseManager.getInstance();
	}
	
	

	
	
	
	
	/// METHODS TO SET UP UI 
	private void CreateWindow() {
		setTitle("Bank 101");
		
		setResizable(false);
		
		setSize(210,270);
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateButtons();
		
		setVisible(true);
	}
	
	private void CreateButtons() {
		
		//create object
		Sign_In  = new JButton("Sign In");
		
		//create an action listener 
		Sign_In.addActionListener(new ButtonListener());

		Create_An_Account = new JButton("Make an Account");
		
		Create_An_Account.addActionListener(new ButtonListener());
		
		CreateTextFields();
			
	}

	private void CreateTextFields() {
		
		//create the label with text 
		user_name = new JLabel("Enter User Name: ");
		
		// then followed by a text field where user inputs credentials 
		User_Name = new JTextField(12);

		
		password = new JLabel("Enter your Password ");

		PassWord = new JTextField(12);

		
		
		Pin = new JLabel("PIN");

		PIN = new JTextField(12);
		Add_To_Panel();
		
	}
	
	private void Add_To_Panel() {
		
		
		// Create our Panel and create constraints 
		Panel = new JPanel();
		
		Panel.setLayout(new GridBagLayout());
		
		GridBagConstraints GC = new GridBagConstraints();

		GC.gridx=0;
		GC.gridy=0;
		Panel.add(user_name,GC);
		GC.gridy=1;
		Panel.add(User_Name,GC);
		GC.gridy = 2;
		Panel.add(password,GC);
		GC.gridy =3;
		Panel.add(PassWord,GC);
		GC.gridy =4;
		Panel.add(Pin,GC);
		GC.gridy=5;
		Panel.add(PIN,GC);
		GC.gridy =6;
		Panel.add(Sign_In,GC);
		GC.gridy =7;
		Panel.add(Create_An_Account,GC);
		
		add(Panel);
		
	}
	/////////////
	
	private void end()
	{
		this.dispose();
	}
	
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
				if(e.getSource() == Sign_In)
				{
					String user_name = User_Name.getText();
					String pass_word = PassWord.getText();
					String pin = PIN.getText();
					
					
					Handle handle = Manager.getHandle(user_name, pass_word, pin);
					 
					if(handle != null)
						{
							end();
						
						}else{
							User_Name.setText("");
							PassWord.setText("");
							PIN.setText("");
						}
				}
				
				if(e.getSource() == Create_An_Account)
				{
					end();
					
				}
				
		}
	}
	
}


