package multiThreadedWebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class webCrawler implements Runnable {
	
	//input variables
	private static final int MAX_DEPTH=3;              //Maximum Dept the webcrawler can go
	private Thread thread;                            
	private String first_link;                        
	private ArrayList<String> visitedLinks = new ArrayList<String>();  //To keep track of the links we visited
	private int ID;                                   
	
	//create constructor
	public webCrawler(String link, int num) {
		System.out.print("WebCrawler Created");
		first_link = link;
		ID = num;
		
		//creating thread for it to run
		thread = new Thread (this);
		thread.start();
	}
	
	@Override
	public void run() {
		crawl(1,first_link);
	}
	
	private void crawl (int level, String url) {
		if(level <= MAX_DEPTH) {
			Document doc = request(url);
			
			if(doc != null) {
				for(Element link : doc.select("a[href]")) {
				    String next_link = link.absUrl("href");
				    if(visitedLinks.contains(next_link)== false) {
				    	crawl(level++, next_link);
				 }
			   }
			}
		}
	}
	
	private Document request(String url) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			
			if(con.response().statusCode() == 200) {
				System.out.println("\n**Bot ID: " + ID + " Received Webpage at  " + url);
				
				String title = doc.title();   //stores the title
				System.out.println(title);
				visitedLinks.add(url);
				
				return doc;
			}
			   return null;
		} 
		catch(IOException e) {
			return null;
		}
	}
	 
	public Thread getThread() {
		return thread;
	}
}
