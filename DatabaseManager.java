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
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
				
			myStmt = myConn.createStatement();
		
			myStmt.executeQuery("use BANK");
		
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
			
				ResultSet myRs = myStmt.executeQuery("SELECT * FROM BANK");
		
				while(myRs.next())
				{
				
					if(UserName.equals(myRs.getString("User_Name"))&&
							PassWord.equals(myRs.getString("Password"))&&
							Pin.equals(myRs.getString("PIN")))
					{
						
						return new Handle(myRs.getString("AccountNum"), this, myRs);
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
	
	
	
	
	
	private void createAccountsTable()
	{	try{
		myStmt = myConn.createStatement();
		
		String line = "CREATE TABLE Accounts( "
				+ "Account_Number INT NULL," 
				+ "First_Name VARCHAR(45) NULL,"
				+ "Last_Name VARCHAR(45) NULL,"
				+ "Pin VARCHAR(45) NULL,"
				+ "Routing_Number VARCHAR(45) NULL,"
				+ "Pass_Word VARCHAR(45) NULL,"
				+ "DOB VARCHAR(45) NULL,"
				+ "PRIMARY KEY (Account_Number));";
		
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
				
				myStmt.executeQuery("use Bank");
				
			}catch(Exception ex){
				System.out.println("FATAL CRASH");
				ex.printStackTrace();
				System.exit(0);
			}
	}
		
}
