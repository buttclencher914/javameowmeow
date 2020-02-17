package javameowmeow;

import java.util.Collection;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class ExtractData extends ExtractData_Abstract{
	public void UpdateStomp() {
		// TODO Auto-generated method stub
		
	}
	public void UpdateReddit(int pages, int perpage) {
		long lPagesProcessed = 0;
		long lPagesSkipped = 0;
		long lArticlesProcessed = 0;
		long lArticlesSkipped = 0;
		long lCommentAdded = 0;
		long lCommentSkipped = 0;
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
						System.out.println("Yes");
						JSONArray ja2 = (JSONArray)((JSONObject)((JSONObject)article.get(1)).get("data")).get("children");
						for (int z = 0; z < ja2.size(); z++)
						{
							String singleComment = (String)((JSONObject)((JSONObject)ja2.get(z)).get("data")).get("body");
							/*ACCESS DATABASE HERE*/
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
		
	}

}
