package multiThreadedWebCrawler;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<webCrawler> bots = new ArrayList<>();
		
		bots.add(new webCrawler("https://abcnews.go.com" , 1));
		bots.add(new webCrawler("https://www.wikipedia.org" , 2));
		bots.add(new webCrawler("https://www.nytimes.com" , 3));
		
		for(webCrawler w :bots) {
			try {
				w.getThread().join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
