package javameowmeow;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class ExtractData extends ExtractData_Abstract{
	public void UpdateStomp() {
		// TODO Auto-generated method stub
		
	}
	public boolean UpdateReddit(int pages, int perpage) {
		long lPagesProcessed = 0;
		long lPagesSkipped = 0;
		long lArticlesProcessed = 0;
		long lArticlesSkipped = 0;
		long lCommentAdded = 0;
		long lCommentSkipped = 0;
		Database db = new Database(Database_Interface.dbpath);
		boolean connectResult = db.Connect(); //connect to db
		if (!connectResult)
		{
			return false;
		}
		Reddit r = new Reddit("singapore", perpage); //create new reddit class for specific subreddit
		for (int i = 0; i < pages; i++)
		{
			JSONObject j = r.DownloadPage(); //download jsonobject for each page
			if (!j.isEmpty())
			{
				JSONArray ja = (JSONArray)((JSONObject)j.get("data")).get("children");
				for (int x = 0; x < ja.size(); x++)
				{
					JSONObject jo = (JSONObject)((JSONObject)ja.get(x)).get("data");
					String title = (String)jo.get("title"); //get title of each article
					String id = (String)jo.get("id"); //get id of each article
					JSONArray article = r.DownloadArticle(id); //download the article
					if (!article.isEmpty())
					{
						JSONArray ja2 = (JSONArray)((JSONObject)((JSONObject)article.get(1)).get("data")).get("children");
						for (int z = 0; z < ja2.size(); z++)
						{
							String singleComment = (String)((JSONObject)((JSONObject)ja2.get(z)).get("data")).get("body");
							String commentID = (String)((JSONObject)((JSONObject)ja2.get(z)).get("data")).get("id");
							/*Check if comment is already in the database*/
							if (db.SearchByColumn(new String[] {"ARTICLE_ID", "CONTENT_ID"}, new String[]{id, commentID}, true, true).length == 0)
							{
								/*If comment doesnt exist*/
								DataRow dr = new DataRow();
								dr.source = "reddit";
								dr.article = title;
								dr.article_id = id;
								dr.content_id = commentID;
								dr.content = singleComment;
								boolean addRes = db.AddData(dr);
								if (addRes)
									lCommentAdded++;
								else
									return false;
							}
							else
							{
								lCommentSkipped++;
							}
						}
					}
					else
					{
						lArticlesSkipped++;
					}
					lArticlesProcessed++;
					/*Add delay*/
					try {
						java.util.concurrent.TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			else
			{
				lPagesSkipped++;
			}
			lPagesProcessed++;
			
		}
		return true;
	}

}
