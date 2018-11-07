import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class DatabaseManager {

	private static DatabaseManager instance = null ;
	private static Connection myConn;
	private Statement myStmt;
	private DatabaseManager() 
	{
		try {
			String userName = "sa";
			String passWord = "CS3650";
			
			String url = "jdbc:sqlserver://DESKTOP-MK8HBBS\\PROGRAMINGSERVER:1433";
					
			myConn = DriverManager.getConnection(url, userName, passWord);
			
			myStmt = myConn.createStatement();
		
			myStmt.execute("use Bank");
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			createDatabase();
		}
	}
	
	
	public static DatabaseManager getInstance()
	{
		if(instance == null)
		{
			synchronized(DatabaseManager.class)
			{
				if(instance == null)
				{
					instance = new DatabaseManager();
				}
			}
		}
		
		return instance; 
	}
	
	public boolean CreateAccount(String[] Array)
	{
		try {
			myStmt = myConn.createStatement();
			myStmt.execute("use Accounts");
			
			String sql = "Insert into Accounts "
					+ "";
			myStmt.executeUpdate(sql);
			return true;
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "ERROR: Creating an Account");
			return false;
		}
		
	}
	public Handle getHandle(String UserName, String PassWord, String Pin)
	{
		
		return getAccountNumber(UserName,PassWord,Pin);	
	}
	
	
	
	
	private  Handle getAccountNumber(String UserName, String PassWord, String Pin)
	{
		try{
				Statement myStmt = myConn.createStatement();
			
				ResultSet myRs = myStmt.executeQuery("SELECT * FROM Accounts");
		
				while(myRs.next())
				{
				
					if(UserName.equals(myRs.getString("First_Name"))&&
							PassWord.equals(myRs.getString("Pass_Word"))&&
							Pin.equals(myRs.getString("Pin")))
					{
						setUpHandle setup = new setUpHandle(myRs.getString("Account_Number"));
						return setup.getHandle();
						//EncryptDecryptinfo Encrypt =
							//	new EncryptDecryptinfo(myRs.getString("AccountNum"));
				
						//Account = new Get_Info_On_Account(Encrypt);
				
					}
		
				}
			
			}catch(Exception ex){
				
				createAccountsTable();
				
			}
		
		return null;
	}
	
	/*we will need another inner class getHandle
	 * and another node list for all handles created by the Database manager
	 * that way.. if a Handle was created by another class and it was passed to Database
	 * it wouldn't be able to update the database because it wasn't created by the database
	 * */
	
	private class setUpHandle{
		
		private Handle currentHandle;
		private String Account_Number;
		public setUpHandle(String Account_Number)
		{
			this.Account_Number = Account_Number;
			this.currentHandle = new Handle(getChecking(), getSavings(), getCreditCards());
		}
		public Handle getHandle()
		{
			return currentHandle;
		
		}
		private  String getChecking()
		{
			return obtainValue("SELECT * FROM Checking", "Checking");
		}
		
		private String getSavings()
		{
			return obtainValue("SELECT * FROM Savings", "Savings");
		}
		
		private String getCreditCards()
		{
			return obtainValue("SELECT * FROM Savings", "Credit");
		}
		
		private String obtainValue(String line, String get)
		{
			try {
				
			}catch(Exception ex)
			{
				if(get.equals("Checking"))
				{
					createCheckingTable();
				}else if(get.equals("Savings")){
					createSavingsTable();
				}else if(get.equals("Credit")) {
					createCreditTable();
				}
			}
			
			return null;
		}
	}
	
	private void createCreditTable()
	{
		try {
			myStmt = myConn.createStatement();
			String line = "create table Credit ("
					+ "Account_Number int Primary Key identity (1,1), ";
			myStmt.execute(line);
			
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR: Creating Credit Table");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	private void createSavingsTable()
	{
		try {
			myStmt = myConn.createStatement();
			String line = "create table Savings ("
					+ "Account_Number int Primary Key identity (1,1), "
					+ "Savings varchar(20), "
					+ "Routing_Number varchar(30),)";
			myStmt.execute(line);
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "ERROR: Creating Savings Table");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	private void createCheckingTable()
	{
		try {
			myStmt = myConn.createStatement();
			String line = "create table Checking ("
					+ " Account_Number int Primary Key identity(1,1), "
					+ " Checking varchar(20),"
					+ " Routing_Number varchar(30),)"; 
			
			myStmt.execute(line);
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "ERROR: Creating Checking Table");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	private void createAccountsTable()
	{	
		try{
		
		myStmt = myConn.createStatement();
		
		String line = "create table Accounts ("
				+ "Account_Number int Primary Key identity(1,1),"
				+ "First_Name varchar(30),"
				+ "Last_Name varchar(30),"
				+ "Pin varchar(5),"
				+ "Pass_Word varchar(30),"
				+ "DOB date,)" ;
		
		myStmt.execute(line);
		
		}catch(Exception ex){
			
			JOptionPane.showMessageDialog(null, "ERROR: Creating Accounts Table");
			ex.printStackTrace();
			System.exit(0);
		}
		
	}
	private void createDatabase()
	{
		try{
				myStmt = myConn.createStatement();
	
				myStmt.execute("create Database Bank");
				
				myStmt.execute("use Bank");
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "ERROR: Creating Database");
				ex.printStackTrace();
				System.exit(0);
			}
	}
		
}
