package javameowmeow;

abstract public class ExtractData_Abstract {
	protected boolean AddData(DataRow dat, Database_Interface connection) //use that to add your data
	{
		return ((Database)connection).AddData(dat);
	}
	abstract public void UpdateStomp(/*add your parameters here*/);
	abstract public boolean UpdateReddit(int pages, int perpage);
	
}
