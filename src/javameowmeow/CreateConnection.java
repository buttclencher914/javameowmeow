package javameowmeow;

public class CreateConnection {
	//database directory goes here, might differ according to machine we use
			Database db = null;

			//establish sql connection and plug user input into prepared statement for sql query
			public void estabConnection(){
				db = new Database(Database_Interface.dbpath);
				db.Connect();
			}

			public DataRow[] Search(String[] input1, String[] input2){
				db = new Database(Database_Interface.dbpath);
				db.Connect();
				return db.SearchByColumn(input1, input2, true, false);
			}

			public DataRow[] PMDarticles(String input) {
				db = new Database(Database_Interface.dbpath);
				db.Connect();
				return db.SearchByColumn(new String [] {input}, new String[] {"pmd" }, false, false);
			}

			public void closeConnection() {
				db.Disconnect();
}
}
