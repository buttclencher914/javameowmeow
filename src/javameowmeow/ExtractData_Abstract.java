package javameowmeow;

abstract public class ExtractData_Abstract {
	protected boolean AddData(DataRow dat, Database connection) //use that to add your data
	{
		return connection.AddData(dat);
	}
	abstract public void UpdateStomp(/*add your parameters here*/);
	abstract public boolean UpdateReddit(int pages, int perpage);
	
}
