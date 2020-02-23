package javameowmeow;

import java.util.Scanner;

public class EnterKeyword {
	Scanner input = new Scanner(System.in);
	String userinput;
	String usersrc;

	public String getSource() {
		System.out.print("Choose a source, Reddit or Stomp: ");
		usersrc = input.next();
		System.out.println("You have chosen: " + usersrc);
		return usersrc;
	}

	public String getKeyWord() {
		System.out.println("Enter a keyword to search: ");
		userinput = input.next();
		System.out.println("You have entered: " + userinput); //for debugging purposes

		while(!userinput.matches("[a-zA-Z0-9]+")){ //check to make sure user enters only alphabets
			System.out.println("Please enter only ALPHABETS!");
			userinput = input.next();
		}
		return userinput;

}
}
