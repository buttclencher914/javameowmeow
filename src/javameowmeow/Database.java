package javameowmeow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database implements Database_Interface{
	private String path;
	private Connection conn = null;
	public Database(String path)
	{ 	
		this.path = path;
	}
	public boolean Connect() //returns true if successful
	{
		try
		{
			this.conn = DriverManager.getConnection(this.path);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean Disconnect() //returns true if successful
	{
		try
		{
			conn.close();
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public DataRow[] SearchByColumn(String column[], String[] searchString, boolean ANDcondition, boolean caseSensitive) {
		String sql = "SELECT ID, SOURCE, ARTICLE_ID, ARTICLE, CONTENT_ID, CONTENT FROM " + tablename + " WHERE ";
		for (int i = 0; i < column.length; i++)
		{
			sql += column[i] + " LIKE ?";
			if ((i+1) < column.length )
			{
				if (ANDcondition)
					sql += " AND ";
				else
					sql += " OR ";
			}
		}
		if (!caseSensitive)
			sql += " COLLATE NOCASE";
		sql += ";";
		try
		{
			PreparedStatement pstmt  = this.conn.prepareStatement(sql);
			for (int i = 0; i < searchString.length; i++)
			{
				pstmt.setString(i+1, searchString[i]);
			}
	        ResultSet rs = pstmt.executeQuery();
	        List<DataRow> res = new ArrayList<DataRow>();
            while (rs.next())
            {
            	DataRow t = new DataRow();
            	t.ID = rs.getLong("ID");
            	t.source = rs.getString("SOURCE");
            	t.article_id = rs.getString("ARTICLE_ID");
            	t.article = rs.getString("ARTICLE");
            	t.content_id = rs.getString("CONTENT_ID");
            	t.content = rs.getString("CONTENT");
            	res.add(t);
            }
            DataRow dt[] = new DataRow[res.size()];
            for (int i = 0; i < res.size(); i++)
            {
            	dt[i] = res.get(i);
            }
            return dt;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	public boolean AddData(DataRow dat) {
		String sql = "INSERT INTO " + tablename + "(SOURCE, ARTICLE_ID, ARTICLE, CONTENT_ID, CONTENT) VALUES(?,?,?,?,?);";  
		   
        try{
            PreparedStatement pstmt = this.conn.prepareStatement(sql);  
            pstmt.setString(1, dat.source);
            pstmt.setString(2, dat.article_id);
            pstmt.setString(3, dat.article);
            pstmt.setString(4, dat.content_id);
            pstmt.setString(5, dat.content);
            pstmt.executeUpdate();
            return true;
        }
        catch (Exception e) 
        {
        	return false;
        }  
	}

	public boolean RemoveData(long ID) {
		//not needed at the moment
		return false;
	}

}
