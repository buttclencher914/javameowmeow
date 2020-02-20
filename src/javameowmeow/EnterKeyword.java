package javameowmeow;

import java.util.Scanner;

public class EnterKeyword {
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
