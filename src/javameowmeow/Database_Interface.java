package javameowmeow;

public interface Database_Interface {
	int IsExist(String path); //checks if database exists
	DataRow ReadRow(long ID); //read by row by id
	DataRow[] SearchByColumn(String column, String content); //get all rows with specific column and content
	int AddData(DataRow dat); //add a new data
	int RemoveData(long ID); //remove data by id
}
