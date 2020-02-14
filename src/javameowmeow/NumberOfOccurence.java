import java.sql.*;
import java.util.Scanner;

//get userinput, allow only alphabets for now
class KeyWord {
	Scanner input = new Scanner(System.in);
	String userinput;
	String getKeyWord() {
		String userinput = input.next();
		while(!userinput.matches("[a-zA-Z0-9]+")){ //check to make sure user enters only alphabets
		    System.out.println("Please enter only ALPHABETS!");
		    userinput = input.next();
		}
		System.out.println("You have entered: " + userinput); //for debugging purposes
		return userinput;
	}
}

class Connect {
	
	//database directory goes here, might differ according to machine we use
	final String DB_PATH = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String username = null;
	String password = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	//initialize sql username and password
	public Connect(String user, String pass) {
		username = user;
		password = pass;
	}
	
	//establish sql connection and plug user input into prepared statement for sql query
	public void connect(String input) throws SQLException, Exception {
		conn = DriverManager.getConnection(DB_PATH, username, password);
		
		//search the content column for our keyword
		pstmt = conn.prepareStatement("select * from testing where content LIKE ?");
		
		//this part searches for our keyword anywhere in a sentence
		pstmt.setString(1,'%'+ input+ '%');
	}
	
	public void closeConnection() throws SQLException, Exception {
		pstmt.close();
		conn.close();
	}
	
	//return sql query to be executed
	public PreparedStatement getStatement() {
		return pstmt;
	}
	
	//return sql connection
	public Connection getConnection() {
		return conn;
	}
	
			
}

public class NumberOfOccurence{
	
	public static void main(String[] args) {
		
		//take 1st input and set as input1 to be plugged into database search
		System.out.println("Please enter a keyword: ");
		KeyWord FirstInput = new KeyWord();
		String input1 = FirstInput.getKeyWord();
		
		//take 2nd input and set as input2 to be plugged into database search
		System.out.println("Please enter a 2nd keyword: ");
		KeyWord SecondInput = new KeyWord();
		String input2 = SecondInput.getKeyWord();	
	
		
		try {
		
		//create new connection with db using root and no password
		Connect db = new Connect("root", "");
		
		//this part starts the db connection and also puts input1 into the prepared statement we created earlier
		db.connect(input1);
		
		//set the results after execution of query
	    ResultSet rset = db.getStatement().executeQuery();
	    
	    //sql query to return columns containing input
	    System.out.println("The records selected are: ");
	    
	    //we will be using this to count the results obtained
	    int rowCount1 = 0;
	    
	    //iterate through the results we have fetched earlier
	    while(rset.next()) 
	    {   // Move the cursor to the next row, return false if no more row
	    	int rowNum = rset.getInt("id");
	        System.out.println(rowNum);
	        
	        //increment the count of results when done
	        ++rowCount1;
	    }
		
	    System.out.println("Number of times = " +"'" + input1 + "'" + " has been mentioned: " + rowCount1);
	    
	    db.connect(input2);
	    ResultSet rset2 = db.getStatement().executeQuery();
	    System.out.println("The records selected are: ");
	    int rowCount2 = 0;
	    while(rset2.next()) 
	    {   // Move the cursor to the next row, return false if no more row
	    	int rowNum = rset2.getInt("id");
	        System.out.println(rowNum);
	        ++rowCount2;
	    }
		
	    System.out.println("Number of times = " +"'" + input2 + "'" + " has been mentioned: " + rowCount2); 
	    
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	      } 
	   

