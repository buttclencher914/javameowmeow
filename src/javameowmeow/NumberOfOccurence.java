package javameowmeow;
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
	Database db = null;


	//establish sql connection and plug user input into prepared statement for sql query
	public DataRow[] connect(String input) throws SQLException, Exception {
		db = new Database(Database_Interface.dbpath);
		return db.SearchByColumn(new String[] {"CONTENT"}, new String[] {input}, false, false);
	}

	public void closeConnection() throws SQLException, Exception {
		db.Disconnect();
	}
}

class Process {
	public void SetResult(DataRow[] dr, String input) throws SQLException {
		//set the results after execution of query
	    
		int rowCount = 0;
	    for (DataRow data: dr)
	    {   // Move the cursor to the next row, return false if no more row
	        System.out.println(data.content);

	        //increment the count of results when done
	        ++rowCount;
	    }

	    System.out.println("Number of times = " +"'" + input + "'" + " has been mentioned: " + rowCount);

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
		Connect db = new Connect();

		//this part starts the db connection and also puts input1 into the prepared statement we created earlier
		//then processing the results we fetched from database
		DataRow[] prep1 = db.connect(input1);
		Process results = new Process();
		results.SetResult(prep1, input1);

		DataRow[] prep2 = db.connect(input2);
		Process results2 = new Process();
		results2.SetResult(prep2, input2);

		//close connection when we are done
		db.closeConnection();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		}
	      }


