package javameowmeow;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class ExtractData extends ExtractData_Abstract{
	public void UpdateStomp() {
		Document document;
		Database db = new Database(Database_Interface.dbpath);
		db.Connect();
		try {
			//crawl base URL
			document = Jsoup.connect("https://stomp.straitstimes.com/tag/e-scooter").get(); 
			//HTML parse for element article links
			Elements articleLinks = document.select("h3.story-headline a[href]"); 
			//HTML parse for element comments
			
			for(Element article : articleLinks) {
				String linkHref = article.attr("href"); //get link of each href element
				Document artDoc = Jsoup.connect(linkHref).get(); //get document of each link
				Elements e = artDoc.select("div.field-item.even");
				int bigcount = 0;
				String finalText = "";
				
				//Only retrieve the titles of the articles that contain eScooter or PMD
				if (article.text().matches("^.*?(escooter|e scooter|e Scooter|E-scooter|E-bike|e bike|eBike|e bike|PMD|pmd|PMDs).*$")) {
					//Add it into temporary array list 
					ArrayList<String> temporary = new ArrayList<>();
					temporary.add(article.text()); //The title of the article
					temporary.add(article.attr("abs:href")); //the URL of the article
				}
				//Stomp Article 
				for(Element e2: e)
				{
					String t = e2.text();
					if (t.length() > bigcount)
					{
						bigcount = t.length();
						finalText = t;
					}
				}
				//Output
				String articleText = article.text();
				String articleLink = article.attr("abs:href");
				if (db.SearchByColumn(new String[] {"ARTICLE_ID"}, new String[] {articleLink}, false, true).length == 0)
				{
					DataRow d = new DataRow();
					d.source = "stomp";
					d.article = articleText;
					d.article_id = articleLink;
					d.content = finalText;
					db.AddData(d);
				}
				
			}
			db.Disconnect();
		} catch(IOException e) {
			db.Disconnect();
			System.err.println(e.getMessage());
		}
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
								boolean addRes = AddData(dr, db);
								if (addRes)
									lCommentAdded++;
								else
								{
									db.Disconnect();
									return false;
								}
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
					System.out.println("Articles processed: " + lArticlesProcessed);
					System.out.println("Articles skipped: " + lArticlesSkipped);
					System.out.println("Comments processed: " + lCommentAdded);
					System.out.println("Comments skipped: " + lCommentSkipped);
				}
				
			}
			else
			{
				lPagesSkipped++;
			}
			lPagesProcessed++;
			
		}
		db.Disconnect();
		return true;
	}

}
