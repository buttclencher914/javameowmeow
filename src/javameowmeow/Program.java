package javameowmeow;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Java Crawler - Group 2");
		System.out.println(
				"====================================================================================================================================");
		System.out.println("Select source to view data");
		System.out.println("1. Reddit");
		System.out.println("2. Stomp");
		System.out.println("3. Both");
		int choice = input.nextInt();
		while (choice != 1 && choice != 2 && choice != 3) {
			System.out.println("Please enter a valid value");
			choice = input.nextInt();
		}
		System.out.println("Enter search string, seperated by commas if you need content with multiple matches");
		String searchString = input.next();
		String searchStringArray[] = searchString.split(",");

		//User u = new User(choice);
		DataRow result[] = null;
		Database db = new Database(Database_Interface.dbpath);
		boolean connectResult = db.Connect();

		
		int looplen = searchStringArray.length + 1;
		String searcha1[] = new String[looplen];
		String searcha2[] = new String[looplen];
		searcha1[0] = "SOURCE";
		
		for (int i = 1; i < looplen; i++)
		{
			searcha1[i] = "CONTENT";
			searcha2[i] = "%" + searchStringArray[i - 1] + "%";
		}
		
		if (choice == 1)
		{
			searcha2[0] = "reddit";
		}
		else if(choice == 2)
		{
			searcha2[0] = "stomp";
		}
		else
		{
			searcha2[0] = "%";
		}
		result = db.SearchByColumn(searcha1, searcha2, true, false);
		System.out.println(result.length + " results found. Do you want to display them? Y/n");
		if (input.next().toLowerCase().contains("y"))
		{
			for (DataRow dr : result) {
				System.out.println(dr.source + " | " + dr.content);
			}
		}

		System.out.println(
				"====================================================================================================================================");
		// test
		/*
		 * 
		 * //This is an example of how to update the database for reddit //this example
		 * will fetch a total of 200 articles ExtractData d = new ExtractData();
		 * //create new ExtractData object d.UpdateReddit(2, 100); //1st parameter =
		 * number of pages, 2nd parameter = articles per page.
		 * 
		 * //This is an example usage of the Database class //How to connect Database db
		 * = new Database(Database_Interface.dbpath); boolean connectResult =
		 * db.Connect(); //connection return a boolean value to indicate whether it was
		 * successful
		 * 
		 * //This is an example usage of Adding data //Values are needed for source,
		 * article, and article_id, or else data adding will fail DataRow dr = new
		 * DataRow(); //create a DataRow Object and put data dr.source = "reddit";
		 * dr.article = title; dr.article_id = id; dr.content_id = commentID; dr.content
		 * = singleComment; boolean addRes = db.AddData(dr); //a boolean value is
		 * returned to indicate whether the adding was successful
		 * 
		 * //This is an example usage of a search //This will return all the rows that
		 * is "stomp" or "reddit" in the SOURCE column DataRow result[]; String a1 = new
		 * String[]{"SOURCE", "SOURCE"}; String a1 = new String[]{"reddit", "stomp"};
		 * result = db.SearchByColumn(a1, a2, false, true); //first paramter is an array
		 * of columns to search //2nd parameter is an array of strings to search for
		 * //3rd parameter is a boolean value to indicate whether multiple conditions
		 * are AND or OR //4th parameter is an boolean value is to indicate whether the
		 * search is case sensitive
		 * 
		 * //This is another example to list any reddit comments that contain the word
		 * "kitten" //not case sensitive String a1 = new String[]{"SOURCE", "CONTENT"};
		 * String a1 = new String[]{"reddit", "%kitten%"}; result =
		 * db.SearchByColumn(a1, a2, true, false);
		 * 
		 * This is another example to list all reddit content String a1 = new
		 * String[]{"SOURCE"}; String a1 = new String[]{"reddit"}; result =
		 * db.SearchByColumn(a1, a2, false, true); //3rd parameter wont matter if is a
		 * single condition search
		 * 
		 * //another way to write this in a single line result = db.SearchByColumn(new
		 * String[]{"SOURCE"}, new String[]{"reddit"}, false, true);
		 */
		// test

	}

}
