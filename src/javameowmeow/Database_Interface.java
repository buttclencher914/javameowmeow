package javameowmeow;

public interface Database_Interface {
	final String dbpath = "jdbc:sqlite:db/db.db3";
	final String tablename = "db";
	boolean Connect();
	boolean Disconnect();
	DataRow[] SearchByColumn(String[] column, String[] searchString, boolean ANDcondition, boolean caseSensitive); //get all rows with specific column and content
	boolean AddData(DataRow dat); //add a new data
	boolean RemoveData(long ID); //remove data by id
}
