package javameowmeow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Reddit {
	String sub; //subreddit
	String next = "null"; //unique_id for next page
	int pagesper; //pages per download
public Reddit(String sub, int pages)
{
	this.sub = sub;
	this.pagesper = pages;
}
public JSONObject DownloadJSON() throws MalformedURLException, IOException, ParseException
{
	String url = "https://www.reddit.com/r/" + this.sub + "/new/?count=" + String.valueOf(pagesper) + "&after=" + this.next + ".json";
	InputStream is = new URL(url).openStream();
	JSONObject json;
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONParser parser = new JSONParser();
      json = (JSONObject) parser.parse(jsonText);
    }
    finally {
      is.close();
    }
    return json;
}
private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

}
