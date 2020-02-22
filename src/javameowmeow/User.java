package javameowmeow;

public class User {
	int choice;
	String keyword;
	
	public User(int choice) {
		this.choice = choice;
	}
	
	public User(String keyword) {
		this.keyword = keyword;
	}
	
	public int getChoice(){
		return choice;
	}
	
	public String getKeyword() {
		return keyword;
	}

}
