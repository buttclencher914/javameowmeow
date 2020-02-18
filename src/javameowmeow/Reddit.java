package javameowmeow;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Reddit {
	private String sub; //subreddit
	private String next = "null"; //unique_id for next page
	private int pagesper; //pages per download
public Reddit(String sub, int pages)
{
	this.sub = sub;
	this.pagesper = pages;
}
public void SetNext(String next)
{
	this.next = next;
}
public String GetNext()
{
	return this.next;
}
public void SetSub(String subreddit)
{
	this.sub = subreddit;
}
public String GetSub()
{
	return this.sub;
}
public JSONObject DownloadPage()
{
	String url = "https://www.reddit.com/r/" + this.sub + "/new/.json?limit=" + String.valueOf(pagesper) + "&after=" + this.next;
	String t = DownloadJSONString(url);
	if (t != null)
	{
		try {
			return (JSONObject)new JSONParser().parse(t);
		} catch (ParseException e) {
			return new JSONObject();
		}
	}
	else
	{
		return new JSONObject();
	}
}
public JSONArray DownloadArticle(String article)
{
	String url = "https://www.reddit.com/" + article + ".json";
	String t = DownloadJSONString(url);
	if (t != null)
	{
		try {
			return (JSONArray)new JSONParser().parse(t);
		} catch (ParseException e) {
			return new JSONArray();
		}
	}
	else
	{
		return new JSONArray();
	}
}
private String DownloadJSONString(String url)
{
	HttpGet request = new HttpGet(url);
	int timeout = 5;
	String result = null;
	RequestConfig config = RequestConfig.custom()
	  .setConnectTimeout(timeout * 1000)
	  .setConnectionRequestTimeout(timeout * 1000)
	  .setSocketTimeout(timeout * 1000).build();
	
	try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setUserAgent("javameowmeow").build();
            CloseableHttpResponse response = httpClient.execute(request)) {

           HttpEntity entity = response.getEntity();
           if (entity != null) {
               // return it as a String
               result = EntityUtils.toString(entity);
           }

       } catch (Exception e) {
	}
    return result;
}


}
