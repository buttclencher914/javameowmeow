package javameowmeow;

public class ProcessData {
	public void SetResult(DataRow[] dr, String input){
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
