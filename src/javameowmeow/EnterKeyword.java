package javameowmeow;

import java.util.Scanner;

public class EnterKeyword {
	Scanner input = new Scanner(System.in);
	String userinput;
	String usersrc;

	public int getSource() {
		System.out.println("Select source to view data");
		System.out.println("1. Reddit");
		System.out.println("2. Stomp");
		System.out.println("3. Both");
		int choice = input.nextInt();
		while (choice != 1 && choice != 2 && choice != 3) {
			System.out.println("Please enter a valid value");
			choice = input.nextInt();
	}
		return choice;
	}

	public String[] getKeyWord() {
		System.out.println("Enter search string, separated by commas if you need content with multiple matches");
		userinput = input.next();
		String searchStringArray[] = userinput.split(",");

		return searchStringArray;

}
}
