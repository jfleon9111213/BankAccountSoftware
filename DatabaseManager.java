import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseManager {

	private static DatabaseManager instance = null ;
	private static Connection myConn;
	private Statement myStmt;
	private DatabaseManager() 
	{
		try {
			String userName = "Eperez";
			String passWord = "enriqueperez9";
			
			String url = "jdbc:sqlserver://DESKTOP-N08O38T\\PROGRAMINGSERVER:1433";
					
			myConn = DriverManager.getConnection(url, userName, passWord);
			
			myStmt = myConn.createStatement();
		
			myStmt.execute("use Bank");
		
		}
		catch(Exception ex)
		{
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
						setUpHandle setup = new setUpHandle();
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
		
		Handle currentHandle;
		public setUpHandle()
		{
			getChecking();
			getSavings();
			getCreditCards();
			this.currentHandle = currentHandle;
		}
		public Handle getHandle()
		{
			return currentHandle;
		
		}
		private  void getChecking()
		{
			
		}
		
		private void getSavings()
		{
			
		}
		
		private void getCreditCards()
		{
			
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
			System.out.println("FATAL CRASH");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	private void createAccountsTable()
	{	try{
		
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
		System.out.println("FATAL CRASH");
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
				System.out.println("FATAL CRASH");
				ex.printStackTrace();
				System.exit(0);
			}
	}
		
}
