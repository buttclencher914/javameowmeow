package javameowmeow;

public class ProcessData {
	public void SetResult(DataRow[] dr, String input){
		//set the results after execution of query

		int rowCount = 0;
	    for (DataRow data: dr)
	    {   // Move the cursor to the next row, return false if no more row
	        System.out.println(data.content);
	        System.out.println();

	        //increment the count of results when done
	        ++rowCount;
	    }
	    if (rowCount != 0) {
	    	System.out.println("'" + input + "'" + " has been mentioned: " + rowCount);
	    }

	    else {
	    	System.out.println("Unable to find any records matching your search");
	    }

	}

	public void StompResults(DataRow[] dr) {
		int rowCount = 0;
	    for (DataRow data: dr)
	    {   // Move the cursor to the next row, return false if no more row
	        System.out.println(data.content);
	        System.out.println();

	        //increment the count of results when done
	        ++rowCount;
	    }
	    if (rowCount != 0) {
	    	System.out.println(+ rowCount + " articles regarding PMDs have been found.");
	    }

	    else {
	    	System.out.println("Unable to find any recent articles about PMDs");
	    }

	}
}
