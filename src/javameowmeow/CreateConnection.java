package javameowmeow;

public class CreateConnection {
	//database directory goes here, might differ according to machine we use
			Database db = null;

			//establish sql connection and plug user input into prepared statement for sql query
			public DataRow[] connect(String input){
				db = new Database(Database_Interface.dbpath);
				db.Connect();
				return db.SearchByColumn(new String[] {"CONTENT"}, new String[] {"%" + input + "%"}, false, false);
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
