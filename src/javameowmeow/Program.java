package javameowmeow;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Java Crawler - Group 2");
		System.out.println(
				"====================================================================================================================================");
		System.out.println("Select source to view all comments: ");
		System.out.println("1. Reddit");
		System.out.println("2. Stomp");
		int choice = input.nextInt();
		while (choice != 1 && choice != 2) {
			System.out.println("Enter only option 1 or 2!");
			choice = input.nextInt();
		}

		User u = new User(choice);
		DataRow result[];
		String src;
		Database db = new Database(Database_Interface.dbpath);
		boolean connectResult = db.Connect();

		if (choice == 1) {
			src = "reddit";
			result = db.SearchByColumn(new String[] { "SOURCE" }, new String[] { "reddit" }, false, true);
			for (DataRow dr : result) {
				System.out.println(dr.source + " | " + dr.content);
			}

		} else {
			src = "stomp";
			result = db.SearchByColumn(new String[] { "SOURCE" }, new String[] { "stomp" }, false, true);
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
