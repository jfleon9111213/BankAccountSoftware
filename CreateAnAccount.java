import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateAnAccount extends JFrame{

	private JButton Home, Sign_Up;
	private JLabel first_Name, last_Name, DoB, User_Name,password, pin;
	private JTextField First_Name, Last_Name, DOB, UserName, Password, Pin;
	private JPanel Panel;
	
	
	private static final long serialVersionUID = 1L;
	private DatabaseManager Manager; 
	
	public CreateAnAccount(){
		
		CreateWindow();
		Manager = DatabaseManager.getInstance();
	}
	
	private boolean CreateAccount()
	{
		String[] array = {First_Name.getText(), Last_Name.getText(), DOB.getText(), 
				UserName.getText(), Password.getText(), Pin.getText()};
		return Manager.CreateAccount(array);
	}
	private boolean checkinput(){
		boolean status = true;
		if(First_Name.getText().length() < 0){
			status = false;
		}if(Last_Name.getText().length() < 0){
			status = false;
		}if(Pin.getText().length() > 4 || Pin.getText().length() < 4){
			status = false;
		}if(UserName.getText().length() > 10){
			status = false; 
		}if(Password.getText().length() >20 ){
			status = false;
		}
		
		return status;
	}
	private void CreateWindow()
		{
			setTitle("Create An Account");
			
			setResizable(false);
			
			setSize(500,250);
			
			setLocationRelativeTo(null);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			CreateButtons();
			
			setVisible(true);
		}
	private void CreateButtons()
		{
			Home = new JButton("Home");
			
			Home.addActionListener(new ButtonListener());
			
			Sign_Up = new JButton("Sign Up");
			
			Sign_Up.addActionListener(new ButtonListener());
			
			CreateTextFields();
		}
	private void CreateTextFields()
		{
			first_Name = new JLabel("Enter First Name");
			
			First_Name = new JTextField(10);
			
			last_Name = new JLabel("Enter Last Name");
			
			Last_Name = new JTextField(10);
			
			DoB= new JLabel("Enter DOB MM/DD/YY");
			
			DOB = new JTextField(8);
			//Second ROW
			User_Name = new JLabel("Enter new UserName");
			
			UserName = new JTextField(10);
			
			password = new JLabel("Enter your Password");
			
			Password = new JTextField(10);
			//Third ROW
			pin = new JLabel("Enter your choice of Pin");
			
			Pin = new JTextField(4);
			
			Add_To_Panel();
		}
	private void Add_To_Panel()
		{
		
			Panel = new JPanel();
		
			Panel.setLayout(new GridBagLayout());
		
			GridBagConstraints GC = new GridBagConstraints();GC.gridx = 0; 
			GC.gridy = 0;
			Panel.add(first_Name, GC);
			GC.gridy = 1;
			Panel.add(First_Name,GC);
		
			GC.gridx=1;
			GC.gridy=0;
			Panel.add(last_Name,GC);
			GC.gridy = 1;
			Panel.add(Last_Name,GC);
			
			GC.gridx = 2; 
			GC.gridy = 0; 
			Panel.add(DoB, GC);
			GC.gridy = 1; 
			Panel.add(DOB, GC);
			
			GC.gridx = 0;
			GC.gridy = 2;
			Panel.add(User_Name, GC);
			GC.gridy = 3;
			Panel.add(UserName, GC);
			
			GC.gridx = 1;
			GC.gridy = 2;
			Panel.add(password,GC);
			GC.gridy = 3;
			Panel.add(Password, GC);
			
			GC.gridx = 0;
			GC.gridy = 4;
			Panel.add(pin, GC);
			GC.gridy = 5;
			Panel.add(Pin, GC);
			
			GC.gridx = 2; 
			GC.gridy = 6;
			Panel.add(Sign_Up, GC);
			
			GC.gridx = 1; 
			Panel.add(Home, GC);
			
			add(Panel);
	}	
	private void end()
	{
		this.dispose();
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
				if(e.getSource() == Home)
					{
						end();
						new LoginIn();
					}
				if(e.getSource() == Sign_Up)
					{
					 if(checkinput())
					 	{
						 if(CreateAccount()){
								end();
								JOptionPane.showMessageDialog(null, "Sign up Successful" );
								new LoginIn();
							}else{
								end();
								JOptionPane.showMessageDialog(null, "Sign up Error");
								UserName.setText("");
								Password.setText("");
								Pin.setText("");
							}
					 	}
					}
			
		}
		
	}
}
